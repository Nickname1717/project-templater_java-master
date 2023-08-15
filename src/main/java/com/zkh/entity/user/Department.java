package com.zkh.entity.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName(value = "HT_HOTEL_DEPARTMENT")
@Accessors(chain = true)
public class Department {
    @TableId(value = "ID")
    private String id="";
    @TableField(value = "DEPT_NAME")
    private String deptName="";
    @TableField(value = "COMPANY_ID")
    private Integer companyId;
    @TableField(value = "ORDERNUMBER")
    private Integer orderNumber;
    @TableField(value = "WERKS")
    private String werks="";
}
