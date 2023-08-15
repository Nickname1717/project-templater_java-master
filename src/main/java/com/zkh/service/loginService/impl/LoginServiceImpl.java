package com.zkh.service.loginService.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zkh.base.ServiceException;
import com.zkh.entity.user.LoginUser;
import com.zkh.entity.user.Module;
import com.zkh.entity.user.User;
import com.zkh.mapper.loginMapper.ModuleMapper;
import com.zkh.mapper.loginMapper.UserMapper;
import com.zkh.service.loginService.LoginService;
import com.zkh.util.TokenCache;
import com.zkh.util.TokenUtil;
import com.zkh.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper,User> implements LoginService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ModuleMapper moduleMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String,Object> login(User user) throws ServiceException {
        //AuthenticationManager authenticate进行用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getLoginid(),user.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        //如果认证没通过给出提示
        if(authentication==null){
            throw new ServiceException("登陆失败");
        }
        //如果认证通过，生成jwt
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        String roleName = loginUser.getRoleName();
        String token = TokenUtil.getToken(loginUser.getUser());

        //把完整的信息存入缓存,如要求不严格可不用缓存直接将user信息保存在token中
        TokenCache.setToken(loginUser.getUser().getPuid(),loginUser);
        //查询出用户中有查询功能的模块
        String puid = loginUser.getUser().getPuid();
        List<Module> moduleList = moduleMapper.selectURLByPuid(puid);
        List<String> menuList = new ArrayList<>();
        for(Module module:moduleList) {
            menuList.add(module.getModuleUrl());
        }
        //查出用户中的权限
        Map<String,Object> map = new HashMap<>();
        map.put("roleName",roleName);
        map.put("token",token);
        map.put("permissionList",loginUser.getPermissions());
        String username = loginUser.getUser().getUserName();
        map.put("username",username);
        map.put("permissionCheckKeys",new ArrayList<>());
        map.put("menuList",menuList);
        return map;
    }

    @Override
    public void logout() {
        //获取SecurityContextHolder的用户id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String puid = loginUser.getUser().getPuid();
        //删除缓存中的值
        TokenCache.delete(puid);
    }

    @Override
    public String modificationPassword(User user, HttpServletRequest request) {
        LoginUser loginUser = UserUtil.getUser(request);
        String password = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        password = encoder.encode(password);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getPuid,loginUser.getUser().getPuid());
        userMapper.update(loginUser.getUser().setPassword(password).setStatus("1"),queryWrapper);
        return "修改成功,请重新登录";
    }
}
