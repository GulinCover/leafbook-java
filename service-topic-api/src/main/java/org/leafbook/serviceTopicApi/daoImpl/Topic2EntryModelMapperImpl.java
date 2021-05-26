package org.leafbook.serviceTopicApi.daoImpl;

import org.leafbook.api.modelApi.topicInfo.Topic2EntryModel;
import org.leafbook.serviceTopicApi.dao.Topic2EntryModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Topic2EntryModelMapperImpl {
    @Autowired
    private Topic2EntryModelMapper topic2EntryModelMapper;

    public int insertById(Long userId, Long topicId, Long entryId) {
        Topic2EntryModel model = new Topic2EntryModel();
        model.setEntryId(entryId);
        model.setTopicId(topicId);
        model.setUserId(userId);
        model.setIsBlack(0);
        model.setVersion(1);
        return topic2EntryModelMapper.insert(model);
    }
}
