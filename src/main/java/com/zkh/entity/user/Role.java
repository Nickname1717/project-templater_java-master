package com.zkh.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName(value = "HT_HOTEL_ROLE")
@Accessors(chain = true)
public class Role {
    @TableId(value = "ID",type = IdType.ASSIGN_UUID)
    private String id = "";
    @TableField(value = "ROLE_NAME")
    private String roleName="";
    @TableField(value = "RIGHTS")
    private String rights="";
    @TableField(value = "ORDERNUMBER")
    private Integer orderNumber;
    private String roleCnName="";
}
