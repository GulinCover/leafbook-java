package org.leafbook.serviceMarketplaceApi.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class MybatisPlusPlugin implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject,"createTime", Long.class, new Date().getTime());
        strictInsertFill(metaObject,"updateTime", Long.class, new Date().getTime());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject,"updateTime", Long.class, new Date().getTime());
    }
}
