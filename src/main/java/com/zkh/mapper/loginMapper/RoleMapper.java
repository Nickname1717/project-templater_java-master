package com.zkh.mapper.loginMapper;




import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zkh.entity.user.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface RoleMapper extends BaseMapper<Role> {
    @Select("SELECT ROLE_NAME FROM HT_HOTEL_USER u JOIN HT_HOTEL_USER_ROLE ur ON " +
            "u.PUID = ur.USER_PUID JOIN HT_HOTEL_ROLE r ON " +
            "ur.ROLE_PUID = r.ID " +
            "WHERE u.PUID=#{puid}")
    List<Role> selectRoleById(String puid);

    @Select("select * from HT_ZFY_ROLE ")
    List<Role> selectAll();
}
