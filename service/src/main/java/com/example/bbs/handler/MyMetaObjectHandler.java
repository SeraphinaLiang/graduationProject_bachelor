package com.example.bbs.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/*
* 创建/更新时自动更新日期
*
* */

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {


    //插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        System.out.println("insert/update fill start");
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    //更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        System.out.println("update fill start");
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}

