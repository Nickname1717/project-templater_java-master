package com.zkh.util;//package com.wk.util;



import com.zkh.entity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * token生成
 * @author wk
 */
public class TokenUtil {
    //密钥
    public static String privateKey = "haitian888";

    /**
     * 加密token.
     */
    public static String getToken(User user) {
        //这个是放到负载payLoad 里面,魔法值可以使用常量类进行封装.
        String token = Jwts.builder()
                .claim("puid" ,user.getPuid())
                .claim("loginId" ,user.getLoginid())
                .claim("userName", user.getUserName())
                //.claim("roleId", user.getROLE_ID())
                .claim("deptId", user.getDeptId())
                //.claim("bizRights", user.getBIZ_RIGHTS())
                .claim("timeStamp", System.currentTimeMillis())
                .setExpiration(new Date(System.currentTimeMillis() + 365 * 24L * 60 * 60 * 1000))    // 设置过期时间
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 设置当前时间
                .signWith(SignatureAlgorithm.HS256, privateKey)     // 第一个参数为加密类型，第二个参数为加的字符串
                .compact();
        return token;
    }

    /**
     * 解析token.
     * (优化可以用常量固定魔法值+使用DTO在 mvc 之前传输数据，而不是 map,这里因为篇幅原因就不做了)
     */
    public static User parseToken(String token) {
        User user = new User();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(privateKey)
                    .parseClaimsJws(token)
                    .getBody();
            user.setPuid(claims.get("puid", String.class));
//            user.setLoginId(claims.get("loginId", String.class));
//            user.setUserName(claims.get("username", String.class));
//            //user.setROLE_ID(claims.get("roleId", String.class));
//            user.setDeptId(claims.get("deptId", String.class));
//            //user.setBIZ_RIGHTS(claims.get("bizRights", String.class));
        }catch (ExpiredJwtException e){
            throw new RuntimeException("登录已经过期，请重新登录");
        }catch (Exception e){
            throw new RuntimeException("登录解析异常，请联系管理员");
        }
        return user;
    }

    /**
     * 从token中检查过期时间
     */
    public static boolean isExpiration(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(privateKey)
                .parseClaimsJws(token)
                .getBody();
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    public static String verifyToken(String token) {
        String result = "";
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(privateKey)
                    .parseClaimsJws(token)
                    .getBody();
            result = "success";
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }


}
