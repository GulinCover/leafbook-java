package org.leafbook.serviceTopicApi.service;

import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.serviceTopicApi.dao.Topic2EntryModelMapper;
import org.leafbook.serviceTopicApi.dao.TopicEntryInfoShowModelMapper;
import org.leafbook.serviceTopicApi.dao.TopicModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TopicRelatedServiceRpc {
    @Autowired
    private TopicModelMapper topicModelMapper;

    @Autowired
    private Topic2EntryModelMapper topic2EntryModelMapper;

    @Autowired
    private TopicEntryInfoShowModelMapper topicEntryInfoShowModelMapper;

    /**
     * 单查询
     * @param topicId
     * @return
     */
    public TopicModel getSelectSingleTopicInfo(Long topicId) {
        return topicModelMapper.selectById(topicId);
    }

    /**
     * 组查询
     * @param topicIds
     * @return
     */
    public List<TopicModel> getSelectMultiTopicInfo(List<Long> topicIds) {
        return topicModelMapper.selectByIds(topicIds);
    }
    /**
     * 创建著述
     * @param userId
     * @param topicTitle
     * @param topicDesc
     * @param entryIds
     * @return topicId
     */
    public Long postCreateTopicInfo(Long userId,String topicTitle,String topicDesc,List<Long> entryIds) {
        TopicModel topicModel = new TopicModel();
        topicModel.setTopicTitle(topicTitle);
        topicModel.setTopicDesc(topicDesc);
        topicModel.setUserId(userId);
        int ret = topic2EntryModelMapper.insertByIds(userId ,topicModel.getTopicId(),entryIds);
        if (ret == 0) return 0L;

        return topicModelMapper.insert(topicModel);
    }

    /**
     * 更改著述描述
     * @param userId
     * @param topicId
     * @param topicDesc
     * @return code
     */
    public int postUpdateTopicInfoDesc(Long userId,Long topicId,String topicDesc) {
        TopicModel topicModel = topicModelMapper.selectById(topicId);
        if (Objects.equals(topicModel.getUserId(), userId)) {
            return topicModelMapper.updateForDescByTopicId(topicId,topicDesc);
        }
        return 403;
    }
    /**
     * 添加词条
     * @param userId
     * @param topicId
     * @param entryId
     * @return code
     */
    public int postAddTopicInfoEntry(Long userId,Long topicId,Long entryId) {
        return topic2EntryModelMapper.insertById(userId,topicId,entryId);
    }

    /**
     * 查询著述词条数量大于50的进行展示
     * @param topicId
     * @return entryIds
     */
    public List<Long> getSelectSingleTopicInfoForEntryIds(Long topicId) {
        return topicEntryInfoShowModelMapper.selectMultiById(topicId);
    }
}
