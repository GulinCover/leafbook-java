package org.leafbook.serviceUserApi.dao;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

@Service
public class AttentionModeMapper {

    public List<Long> selectMultiAttentionUserInfoByUserId(Long userId) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(5,10).forEach((it)->{
            lst.add(it);
        });
        return lst;
    }

    public List<Long> selectMultiFollowedUserInfoByUserId(Long userId) {
        final List<Long> lst = new LinkedList<>();
        LongStream.range(5,10).forEach((it)->{
            lst.add(it);
        });
        return lst;
    }

    public int selectIsExistAttention(Long userId,Long attentionId) {
        return new Random().nextInt(100);
    }
}











