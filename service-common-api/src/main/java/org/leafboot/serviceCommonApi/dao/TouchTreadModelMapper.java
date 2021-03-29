package org.leafboot.serviceCommonApi.dao;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TouchTreadModelMapper {
    public int insertTouchTread(Long userId, Long objectId, String type) {
        return 1;
    }
    public int selectTouchTread(Long userId, Long objectId, String type) {
        return 0;
    }
}
