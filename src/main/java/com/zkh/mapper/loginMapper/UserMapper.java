package com.zkh.mapper.loginMapper;




import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zkh.entity.user.User;
import com.zkh.entity.user.vo.UserAuthenrizeVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    //通过loginId查找用户信息
    @Select("select PUID , LOGIN_ID , PASSWORD ,USER_NAME from HT_HOTEL_USER where STATUS ='1' and LOGIN_ID=#{loginId}")
    User selectUserByLoginId(String loginId);

   //通过puid查找用户权限信息
    @Select("select u.PUID as puid, u.LOGIN_ID as loginId, r.ID as roleId , r.ROLE_NAME as roleName ,a.PUID as authPuid, a.AUTH_NAME as authName,a.AUTH_CODE as authCode, m.PUID as modulePuid, m.MODULE_NAME as moduleName, m.MODULE_CODE as moduleCode " +
            "from HT_HOTEL_USER u left join HT_HOTEL_USER_ROLE ur on u.PUID = ur.USER_PUID " +
            "left join HT_HOTEL_ROLE r on ur.ROLE_PUID = r.ID " +
            "left join HT_HOTEL_ROLE_AUTH ra on r.ID = ra.ROLE_ID " +
            "left join HT_HOTEL_MODULE_AUTH ma on ra.MODULE_AUTH_ID = ma.PUID " +
            "left join HT_HOTEL_AUTH a on a.PUID = ma.AUTH_ID " +
            "left join HT_HOTEL_MODULE m on m.PUID = ma.MODULE_ID " +
            "where u.PUID = #{puid} "
             + "and u.STATUS='1' "
             + "and ur.STATUS='1' "
           + "and ra.STATUS='1' "
           + "and a.STATUS = '1' "
           + "and ma.STATUS='1' "
           + "and m.STATUS='1'"
            )
    List<UserAuthenrizeVo> selectAuthById(String puid);

    @Select("select * from HT_HOTEL_USER where PUID=#{userPuid}")
    User selectOneUser(String userPuid);

    @Select("select PUID from HT_HOTEL_USER where LOGIN_ID=#{loginId}")
    List<User> selectByLoginId(String loginId);
}
