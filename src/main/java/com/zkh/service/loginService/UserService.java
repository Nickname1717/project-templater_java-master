package com.zkh.service.loginService;


import com.zkh.entity.user.User;
import com.zkh.entity.user.UserRole;

import java.util.List;

public interface UserService {


    void saveUser(User user, UserRole userRole) throws Exception;

    List<User> selectUser(String puid) throws Exception;

    void disable (User user,UserRole userRole) throws Exception;

    void delete(String puid) throws Exception;

    User selectOneUser(String userPuid);

    List<User> selectByLoginId(String loginId);
}
