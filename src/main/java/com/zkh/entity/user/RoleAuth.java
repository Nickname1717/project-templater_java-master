package com.zkh.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@TableName(value = "HT_HOTEL_ROLE_AUTH")
@Accessors(chain = true)
public class RoleAuth {
    @TableId(value = "PUID",type = IdType.ASSIGN_UUID)
    private String puid;
    @TableField(value = "ROLE_ID")
    private String roleId;
    @TableField(value = "MODULE_AUTH_ID")
    private String moduleAuthId;
    @TableField(value = "SORT")
    private String sort;
    @TableField(value = "BAK")
    private String bak;
    @TableField(value = "STATUS")
    private String status;
    @TableField(value = "CREATE_TIME")
    private String createTime;
    @TableField(value = "CREATOR")
    private String creator;
    @TableField(value = "UPDATE_TIME")
    private String updateTime;
    @TableField(value = "UPDATER")
    private String updater;
}
