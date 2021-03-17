package org.leafboot.serviceCommonApi.dao;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TouchStarModelMapper {
    public int insertTouchStar(Long userId, Long objectId, String type) {
        return new Random().nextInt(100);
    }
    public int selectTouchStar(Long userId, Long objectId, String type) {
        return new Random().nextInt(2);
    }
}
