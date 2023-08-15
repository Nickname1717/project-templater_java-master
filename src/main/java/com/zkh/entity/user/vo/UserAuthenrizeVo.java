package com.zkh.entity.user.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserAuthenrizeVo {
    private String puid;
    private String loginId;
    private String roleId;
    private String roleName;
    private String authPuid;
    private String authName;
    private String modulePuid;
    private String moduleName;
    private String moduleCode;
    private String authCode;
    public String getPermission (){
        return moduleCode+"."+authCode;
    }
    private List<String> permissionList = new ArrayList<>();
}
