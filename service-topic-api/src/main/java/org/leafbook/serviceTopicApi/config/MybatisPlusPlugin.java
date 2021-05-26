package org.leafbook.serviceTopicApi.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MybatisPlusPlugin implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        fillStrategy(metaObject, "createTime", new Date().getTime());
        strictInsertFill(metaObject, "updateTime",Long.class, new Date().getTime());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, "updateTime",Long.class, new Date().getTime());
    }
}
