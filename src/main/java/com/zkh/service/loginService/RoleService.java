package com.zkh.service.loginService;


import com.zkh.entity.user.Role;

import java.util.List;

public interface RoleService  {
    List<Role> selectRole();

    List<Role> selectRoleByUserPuid(String puid) throws Exception;
}
