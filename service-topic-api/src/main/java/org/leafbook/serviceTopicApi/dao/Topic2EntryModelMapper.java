package org.leafbook.serviceTopicApi.dao;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class Topic2EntryModelMapper {

    public int insertById(Long userId, Long topicId, Long entryId) {
        return new Random().nextInt(100);
    }
}
