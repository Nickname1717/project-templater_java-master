package com.zkh.controller.login;





import com.zkh.base.BaseApiService;
import com.zkh.base.BaseResponse;
import com.zkh.entity.user.User;
import com.zkh.service.loginService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController extends BaseApiService {
    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public BaseResponse login(@RequestBody User user)throws Exception{
         //登录
        return setResultSuccess(loginService.login(user));

    }
    @PostMapping("/logout")
    public  BaseResponse logout(){
        loginService.logout();
        return setResultSuccess("登出成功");
    }

    @PostMapping("/modification")
    public BaseResponse modification(@RequestBody User user, HttpServletRequest request){
        return setResultSuccess(loginService.modificationPassword(user,request));
    }
}