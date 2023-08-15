package com.zkh.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName(value = "HT_HOTEL_USER_ROLE")
@Accessors(chain = true)
public class UserRole {
    @TableId(value = "PUID",type = IdType.ASSIGN_UUID)
    private String puid;
    @TableField(value = "USER_PUID")
    private String userPuid;
    @TableField(value = "ROLE_PUID")
    private String rolePuid;
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
