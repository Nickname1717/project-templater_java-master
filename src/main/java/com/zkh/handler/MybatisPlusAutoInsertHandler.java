package com.zkh.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zkh.util.DateUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Component
public class MybatisPlusAutoInsertHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", DateUtil.getTime(),metaObject);
        this.setFieldValByName("updateTime", DateUtil.getTime(),metaObject);
        this.setFieldValByName("status", "1",metaObject);
        this.setFieldValByName("writeOffStatus", "1",metaObject);
        this.setFieldValByName("modifyStatus","1",metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}
