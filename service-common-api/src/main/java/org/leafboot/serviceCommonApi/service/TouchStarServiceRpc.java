package org.leafboot.serviceCommonApi.service;

import org.leafboot.serviceCommonApi.dao.TouchStarModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouchStarServiceRpc {
    @Autowired
    private TouchStarModelMapper touchStarModelMapper;

    public int postInsertTouchStar(Long userId,Long objectId,String type) {
        if ("topic".equals(type)  || "user".equals(type) || "comment".equals(type) || "entry".equals(type) || "talk".equals(type) || "talk_comment".equals(type)) {
            return _touchStarSelectAndInsert(userId,objectId,type);
        } else {
            return 403;
        }
    }

    //工具方法
    private int _touchStarSelectAndInsert(Long userId,Long objectId,String type) {
        int ret = touchStarModelMapper.selectTouchStar(userId, objectId, type);
        if (ret == 1) {
            return 4001;
        } else {
            ret = touchStarModelMapper.insertTouchStar(userId, objectId, type);
            if (ret == 1) {
                return 200;
            } else {
                return 500;
            }
        }
    }
}
