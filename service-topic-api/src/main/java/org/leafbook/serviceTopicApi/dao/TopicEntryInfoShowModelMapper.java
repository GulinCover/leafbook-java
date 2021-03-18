package org.leafbook.serviceTopicApi.dao;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class TopicEntryInfoShowModelMapper {
    public List<Long> selectMultiById(Long topicId) {
        List<Long> entryIds = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(5)+2; ++i) {
            entryIds.add((long) i);
        }
        return entryIds;
    }
}
