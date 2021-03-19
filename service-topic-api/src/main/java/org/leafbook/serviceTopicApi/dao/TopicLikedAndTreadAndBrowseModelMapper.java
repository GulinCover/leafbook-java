package org.leafbook.serviceTopicApi.dao;

import org.leafbook.api.modelApi.topicInfo.TopicLikedAndTreadAndBrowseModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TopicLikedAndTreadAndBrowseModelMapper {

    public TopicLikedAndTreadAndBrowseModel selectSingleByTopicId(Long topicId) {
        TopicLikedAndTreadAndBrowseModel model = new TopicLikedAndTreadAndBrowseModel();
        model.setLikedAmount((long)new Random().nextInt(1200));
        model.setTreadAmount((long)new Random().nextInt(1200));
        model.setBrowseAmount((long)new Random().nextInt(1200));
        return model;
    }

    public int updateByModel(TopicLikedAndTreadAndBrowseModel model) {
        return new Random().nextInt(100);
    }
}
