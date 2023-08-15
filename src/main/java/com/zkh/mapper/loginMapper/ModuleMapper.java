package com.zkh.mapper.loginMapper;





import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zkh.entity.user.Module;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface ModuleMapper extends BaseMapper<Module> {
    @Select("SELECT MODULE_NAME FROM HT_HOTEL_USER u JOIN HT_HOTEL_USER_ROLE ur ON " +
            "u.PUID = ur.USER_PUID JOIN HT_HOTEL_ROLE r ON " +
            "ur.ROLE_PUID = r.ID JOIN HT_HOTEL_ROLE_AUTH ra ON " +
            "ra.ROLE_ID = r.ID JOIN HT_HOTEL_MODULE_AUTH ma ON " +
            "ma.PUID = ra.MODULE_AUTH_ID JOIN HT_HOTEL_MODULE m ON " +
            "m.PUID =ma.MODULE_ID " +
            "WHERE u.PUID=#{puid} ")
    List<Module> selectModuleById(String puid);

    @Select("select PUID,PARENT_PUID,MODULE_NAME,MODULE_CODE,MODULE_URL,HASCHILD from " +
            "HT_ZFY_MODULE where STATUS = '1'")
    List<Module> selectAll();


    List<Module> selectURLByPuid(String puid);

}
