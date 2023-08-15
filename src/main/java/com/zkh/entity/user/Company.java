package com.zkh.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName(value = "HT_HOTEL_COMPANY")
public class Company {
    @TableId(value = "ID",type = IdType.ASSIGN_UUID)
    private Integer id;
    @TableField(value = "COMPANY_NAME")
    private String companyName="";
    @TableField(value = "TAB")
    private String tab="";
}
