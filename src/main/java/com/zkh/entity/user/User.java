package com.zkh.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName(value = "HT_HOTEL_USER")
@Accessors(chain = true)
public class User {
    @TableId(value = "PUID",type = IdType.ASSIGN_ID)
    private String puid = "";
    @TableField(value = "LOGIN_ID")
    private String loginid="";
    @TableField(value = "PASSWORD")
    private String password = "";
    @TableField(value = "USER_NAME")
    private String userName = "";
    @TableField(value = "DEPT_ID")
    private String deptId = "";
    @TableField(value = "LAST_LOGIN")
    private String lastLogin = "";
    @TableField("BAK")
    private String bak="";
    @TableField("IP")
    private String ip = "";
    @TableField("SORT")
    private String sort="";
    @TableField("STATUS")
    private String status="";
    @TableField("CREATE_TIME")
    private String createTime ;
    @TableField("CREATOR")
    private String creator="";
    @TableField("UPDATE_TIME")
    private String updateTime;
    @TableField("UPDATER")
    private String updater = "";

    //以下是补充字段
    @TableField(exist = false)
    private String roleCnName="";
    @TableField(exist = false)
    private String id="";

}
