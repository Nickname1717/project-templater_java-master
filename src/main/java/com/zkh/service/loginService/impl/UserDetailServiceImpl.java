package com.zkh.service.loginService.impl;


import com.zkh.entity.user.LoginUser;
import com.zkh.entity.user.User;
import com.zkh.entity.user.vo.UserAuthenrizeVo;
import com.zkh.mapper.loginMapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         User user = userMapper.selectUserByLoginId(username);
         if(user==null){
             throw  new RuntimeException("用户名或密码错误");
         }
        //权限信息
         List<UserAuthenrizeVo> authList = userMapper.selectAuthById(user.getPuid());
         String roleName = "";
         List<String> permissions = new ArrayList<>();
         for(UserAuthenrizeVo authenrizeVo:authList){
             permissions.add(authenrizeVo.getPermission());
             permissions.add(authenrizeVo.getRoleName());
             roleName = authenrizeVo.getRoleName();
         }

         permissions = findBandRole(permissions);
         //把数据封装成UserDetail类
         LoginUser loginUser = new LoginUser(user,permissions,roleName);
         return loginUser;
    }

    public List<String> findBandRole(List<String> permissions){
        HashSet<String> danList = new HashSet<>();
        List<String> deal = new ArrayList<>();
        if(permissions.size()!=0){
            for(String str:permissions){
                danList.add(str);
            }

            for (String str2:danList){
                deal.add(str2);
            }
        }
        return deal;

    }
}

