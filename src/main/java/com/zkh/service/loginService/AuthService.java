package com.zkh.service.loginService;


import com.zkh.entity.user.Auth;

import java.util.List;

public interface AuthService {

    List<Auth> selectAuth();
    List<Auth> selectAuth(Auth auth)throws Exception;

    Integer insertAuth(Auth auth) throws Exception;

    Integer updateData(Auth auth)throws Exception;
}
