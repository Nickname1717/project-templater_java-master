package com.zkh.service.loginService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zkh.base.ServiceException;
import com.zkh.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface LoginService extends IService<User> {
    Map<String,Object> login(User user) throws ServiceException;

    void logout();

    String modificationPassword(User user, HttpServletRequest request);
}
