package org.leafbook.serviceTopicApi.dao;

import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class TopicModelMapper {

    public TopicModel selectById(Long topicId) {
        TopicModel topicModel = new TopicModel();
        topicModel.setUserId(1111L);
        topicModel.setTopicId(topicId);
        topicModel.setTopicDesc(TestModel.randomString().toString());
        topicModel.setTopicTitle(TestModel.randomWord());
        return topicModel;
    }

    public List<TopicModel> selectByIds(List<Long> topicIds) {
        List<TopicModel> topicModelList = new LinkedList<>();
        for (Long i: topicIds) {
            topicModelList.add(selectById(i));
        }

        return topicModelList;
    }

    public Long insert(TopicModel topicModel) {
        return (long)new Random().nextInt(1000)+1000;
    }

    public int updateForDescByTopicId(Long topicId,String topicDesc) {
        return new Random().nextInt(100);
    }

    public int updateForEntryIdsByTopicId(Long topicId,String entryIds) {
        return new Random().nextInt(100);
    }

    public int updateByModel(TopicModel topicModel) {
        return new Random().nextInt(100);
    }

}
