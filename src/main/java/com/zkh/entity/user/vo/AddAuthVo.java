package com.zkh.entity.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data //接受权限编辑类
public class AddAuthVo {
    private String userName;
    @JsonProperty("loginId")
    @NotBlank(message = "用户名不能为空")
    private String loginId;
    private String password;
    private String roleCnName;
    private String roleId;
    private String status;

}
