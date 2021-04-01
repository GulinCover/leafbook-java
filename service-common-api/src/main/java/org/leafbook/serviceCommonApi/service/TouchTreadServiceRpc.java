package org.leafbook.serviceCommonApi.service;

import org.leafbook.serviceCommonApi.dao.TouchTreadModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TouchTreadServiceRpc {
    @Autowired
    private TouchTreadModelMapper touchTreadModelMapper;
    /**
     * 点踩
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    public int postInsertTouchTread(Long userId,Long objectId,String type) {
        if ("topic".equals(type)  || "article".equals(type) || "comment".equals(type) || "entry".equals(type) || "talk".equals(type) || "talk_comment".equals(type)) {
            return touchTreadModelMapper.insertTouchTread(userId, objectId, type);
        } else {
            return 0;
        }
    }
    /**
     * 查看是否点过踩
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    public int postSelectTouchedTread(Long userId,Long objectId,String type) {
        if ("topic".equals(type)  || "article".equals(type) || "comment".equals(type) || "entry".equals(type) || "talk".equals(type) || "talk_comment".equals(type)) {
            return touchTreadModelMapper.selectTouchTread(userId, objectId, type);
        } else {
            return 0;
        }
    }
}
