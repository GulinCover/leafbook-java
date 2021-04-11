package org.leafbook.serviceTopicApi.dao;

import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopicEntryInfoShowModelMapper {
    /**
     * 根据topicIds组查entryIds
     * @param topicIds
     * @return
     */
    public List<Long> selectMultiByTopicIds(List<Long> topicIds) {
        List<Long> entryIds = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(5)+2; ++i) {
            entryIds.add((long) i);
        }
        return entryIds;
    }
    /**
     * 根据entryIds获取著述数量
     * @param entryIds
     * @return
     */
    public Map<Long,Long> selectTopicAmountByEntryIds(List<Long> entryIds) {
        Map<Long,Long> map = new HashMap<>();
        for (int i = 0; i < new Random().nextInt(5) + 5; i++) {
            map.put((long)i, (long)new Random().nextInt(5000));
        }

        return map;
    }

    public List<Long> selectMultiById(Long topicId) {
        List<Long> entryIds = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(5)+2; ++i) {
            entryIds.add((long) i);
        }
        return entryIds;
    }

    public Long selectTopicInfoAmountByEntryId(Long entryId) {
        return (long) new Random().nextInt(4561);
    }

    public List<TopicModel> selectMultiTopicInfoByEntryId(Long entryId, Long page) {
        List<TopicModel> topicModelList = new LinkedList<>();
        for (int i = 0; i < 10; ++i) {
            TopicModel topicModel = new TopicModel();
            topicModel.setTopicDesc(TestModel.randomWord());
            topicModel.setTopicId((long) i);
            topicModel.setTopicTitle(TestModel.randomWord());
            topicModel.setUserId((long) i);
            topicModel.setType(1);

            topicModelList.add(topicModel);
        }

        return topicModelList;
    }

    public int insertByIds(Long topicId,List<Long> entryIds) {
        return new Random().nextInt(100);
    }
}
