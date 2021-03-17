package org.leafboot.serviceCommonApi.service;

import org.leafboot.serviceCommonApi.dao.TouchTreadModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouchTreadServiceRpc {
    @Autowired
    private TouchTreadModelMapper touchTreadModelMapper;

    public int postInsertTouchTread(Long userId,Long objectId,String type) {
        if ("topic".equals(type)  || "user".equals(type) || "comment".equals(type) || "entry".equals(type) || "talk".equals(type) || "talk_comment".equals(type)) {
            return _touchTreadSelectAndInsert(userId,objectId,type);
        } else {
            return 403;
        }
    }

    //工具方法
    private int _touchTreadSelectAndInsert(Long userId,Long objectId,String type) {
        int ret = touchTreadModelMapper.selectTouchTread(userId, objectId, type);
        if (ret == 1) {
            return 4001;
        } else {
            ret = touchTreadModelMapper.insertTouchTread(userId, objectId, type);
            if (ret == 1) {
                return 200;
            } else {
                return 500;
            }
        }
    }
}
