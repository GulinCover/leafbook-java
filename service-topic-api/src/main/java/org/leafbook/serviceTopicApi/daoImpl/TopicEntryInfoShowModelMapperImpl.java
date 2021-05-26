package org.leafbook.serviceTopicApi.daoImpl;

import org.leafbook.api.annotation.RemainingProblem;
import org.leafbook.api.annotation.ToBeOptimized;
import org.leafbook.api.modelApi.topicInfo.TopicEntryInfoShowModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.serviceTopicApi.dao.TopicEntryInfoShowModelMapper;
import org.leafbook.serviceTopicApi.dao.TopicModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopicEntryInfoShowModelMapperImpl {
    @Autowired
    private TopicEntryInfoShowModelMapper topicEntryInfoShowModelMapper;
    @Autowired
    private TopicModelMapper topicModelMapper;
    /**
     * 根据topicIds组查entryIds
     * @param topicIds
     * @return
     */
    public List<Long> selectMultiByTopicIds(List<Long> topicIds) {
        return topicEntryInfoShowModelMapper.selectMultiEntryIdsByTopicIds(topicIds);
    }
    /**
     * 根据entryIds获取著述数量
     * @param entryIds
     * @return
     */
    @ToBeOptimized
    public Map<Long,Long> selectTopicAmountByEntryIds(List<Long> entryIds) {
        Map<Long,Long> map = new HashMap<>();
        for (Long entryId:entryIds) {
            Long topicAmount = topicEntryInfoShowModelMapper.selectTopicAmountByEntryId(entryId);
            map.put(entryId, topicAmount);
        }
        return map;
    }
    /**
     * 查询著述词条数量大于50的进行展示
     * @param topicId
     * @return entryIds
     */
    public List<Long> selectMultiEntryIdsById(Long topicId) {
        return topicEntryInfoShowModelMapper.selectMultiEntryIdsByTopicId(topicId);
    }
    /**
     * 获取某词条下的著述数量
     * @param entryId
     * @return
     */
    public Long selectTopicInfoAmountByEntryId(Long entryId) {
        return topicEntryInfoShowModelMapper.selectTopicAmountByEntryId(entryId);
    }

    /**
     * 根据entryId随机获取topicIds
     * @param entryId
     * @param number
     * @return
     */
    public List<Long> selectRandomTopicIdsByEntryId(Long entryId,Long number) {
        return topicEntryInfoShowModelMapper.selectRandomTopicIdsByEntryId(entryId,number);
    }
    /**
     * 获取某词条下的所有著述
     * @param entryId
     * @return
     */
    @RemainingProblem
    public List<TopicModel> joinSelectMultiTopicInfoByEntryId(Long entryId, Long page) {
        Long end = page * 20;
        Long start = end - 20;
        List<Long> topicIds = topicEntryInfoShowModelMapper.selectMultiTopicIdsByEntryId(entryId,start,end);
        return topicModelMapper.selectMultiTopicInfoByTopicIds(topicIds);
    }

    /**
     * 创建著述时必插入的
     * @param topicId
     * @param entryIds
     * @return topicId
     */
    public int insertByIds(Long topicId,List<Long> entryIds) {
        List<TopicEntryInfoShowModel> models = new LinkedList<>();
        Long time = new Date().getTime();
        for (Long entryId:entryIds) {
            TopicEntryInfoShowModel model = new TopicEntryInfoShowModel();
            model.setTopicEntryInfoShowModelId(IdGeneratorTools.nextId());
            model.setTopicId(topicId);
            model.setIsBlack(0);
            model.setVersion(1);
            model.setEntryId(entryId);
            model.setCreateTime(time);
            model.setUpdateTime(time);

            models.add(model);
        }

        return topicEntryInfoShowModelMapper.insertBatchByModels(models);
    }
}
