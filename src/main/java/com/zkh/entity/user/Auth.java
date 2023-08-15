package com.zkh.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "HT_HOTEL_AUTH")
public class Auth {
    @TableId(value = "PUID",type = IdType.ASSIGN_UUID)
    private String puid="";
    @TableField(value = "AUTH_NAME")
    private String authName="";
    @TableField(value = "AUTH_CODE")
    private String authCode="";
    @TableField(value = "SORT")
    private String sort="";
    @TableField(value = "BAK")
    private String bak="";
    @TableField(value = "STATUS")
    private String status="";
    @TableField(value = "CREATE_TIME")
    private String createTime="";
    @TableField(value = "CREATOR")
    private String creator="";
    @TableField(value = "UPDATE_TIME")
    private String updateTime="";
    @TableField(value = "UPDATER")
    private String updater="";
}
