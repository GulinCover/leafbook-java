package org.leafbook.serviceCommonApi.dao;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TouchStarModelMapper {
    /**
     * 查询是否点过赞
     * @param userId
     * @param objectId
     * @param type
     * @return
     */
    public int selectTouchStar(Long userId, Long objectId, String type) {
        return 0;
    }


    /**
     * 插入点赞信息
     * @param userId
     * @param objectId
     * @param type
     * @return
     */
    public int insertTouchStar(Long userId, Long objectId, String type) {
        return 1;
    }
}
