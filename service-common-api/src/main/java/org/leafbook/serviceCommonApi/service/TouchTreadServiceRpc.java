package org.leafbook.serviceCommonApi.service;

import org.leafbook.serviceCommonApi.daoImpl.TouchTreadModelMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TouchTreadServiceRpc {
    @Autowired
    private TouchTreadModelMapperImpl touchTreadModelMapperImpl;
    /**
     * 点踩
     * @param userId
     * @param objectId
     * @param type
     * @return code
     */
    public int postInsertTouchTread(Long userId,Long objectId,String type) {
        if ("topic".equals(type)  || "article".equals(type) || "comment".equals(type) || "entry".equals(type) || "talk".equals(type) || "talkComment".equals(type)) {
            return touchTreadModelMapperImpl.insertTouchTread(userId, objectId, type);
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
        if ("topic".equals(type)  || "article".equals(type) || "comment".equals(type) || "entry".equals(type) || "talk".equals(type) || "talkComment".equals(type)) {
            return touchTreadModelMapperImpl.selectTouchTread(userId, objectId, type);
        } else {
            return 0;
        }
    }
}
