package com.zkh.filter;


import com.zkh.entity.user.LoginUser;
import com.zkh.entity.user.User;
import com.zkh.util.TokenCache;
import com.zkh.util.TokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

;

/**
 * 认证过滤器
 */
@Component
public class JwtAuthenticationTokenFiler extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            //放行
            filterChain.doFilter(request,response);
            return;
        }
        String uri=request.getRequestURI();
        //todo 更改项目需对登录放行
        if(uri.equals("/projecttemplate/login")){
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        User user = TokenUtil.parseToken(token);
        //从缓存中获取用户信息
        LoginUser loginUser = TokenCache.getTokenFromCache(user.getPuid());
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
        //存入SecurityContextHolder
        //TODO 获取权限信息分装到Authentication中
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        //放行
        filterChain.doFilter(request,response);

        //        filterChain.doFilter(request,response);
    }
}
