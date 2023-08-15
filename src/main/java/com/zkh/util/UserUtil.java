package com.zkh.util;



import com.zkh.entity.user.LoginUser;
import com.zkh.entity.user.User;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {
    public static LoginUser getUser(HttpServletRequest request){
       String token = request.getHeader("token");
       User user = TokenUtil.parseToken(token);
       LoginUser loginUser = TokenCache.getTokenFromCache(user.getPuid());
       return loginUser;
    }
}
