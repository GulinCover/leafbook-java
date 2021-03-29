package org.leafboot.serviceCommonApi.service;

import org.leafboot.serviceCommonApi.dao.TouchStarModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouchStarServiceRpc {
    @Autowired
    private TouchStarModelMapper touchStarModelMapper;
    /**
     * 点赞
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    public int postInsertTouchStar(Long userId,Long objectId,String type) {
        if ("topic".equals(type)  || "article".equals(type) || "comment".equals(type) || "entry".equals(type) || "talk".equals(type) || "talkComment".equals(type)) {
            return touchStarModelMapper.insertTouchStar(userId, objectId, type);
        } else {
            return 0;
        }
    }
    /**
     * 查看是否点过赞
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    public int postSelectTouchedStar(Long userId,Long objectId,String type) {
        if ("topic".equals(type)  || "article".equals(type) || "comment".equals(type) || "entry".equals(type) || "talk".equals(type) || "talkComment".equals(type)) {
            return touchStarModelMapper.selectTouchStar(userId, objectId, type);
        } else {
            return 0;
        }
    }
}
