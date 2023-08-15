package com.zkh.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.zkh.entity.user.LoginUser;


/**·
 *  缓存管理token
 */
public class TokenCache {

    private static final String TOKEN_KEY = "token_";
    private static Cache<String,Object> cache = CacheBuilder.newBuilder().build();

    /**
     * 保存
     * @param loginUser
     */
    public static void setToken(String username, LoginUser loginUser) {

        cache.put(TOKEN_KEY+username,loginUser);
    }

    /**
     * 取
     * @return
     */
    public static LoginUser getTokenFromCache(String username){
        return (LoginUser) cache.getIfPresent(TOKEN_KEY+username);
    }
    /**
     * 删
     */
    public static void delete(String username){
        cache.invalidate(TOKEN_KEY+username);
    }

}