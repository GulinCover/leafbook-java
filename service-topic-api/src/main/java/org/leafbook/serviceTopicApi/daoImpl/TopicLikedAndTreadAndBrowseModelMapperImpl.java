package org.leafbook.serviceTopicApi.daoImpl;

import net.sf.jsqlparser.statement.select.Top;
import org.leafbook.api.modelApi.topicInfo.TopicLikedAndTreadAndBrowseModel;
import org.leafbook.serviceTopicApi.dao.TopicLikedAndTreadAndBrowseModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

@Service
public class TopicLikedAndTreadAndBrowseModelMapperImpl {
    @Autowired
    private TopicLikedAndTreadAndBrowseModelMapper topicLikedAndTreadAndBrowseModelMapper;

    /**
     * 获取点赞排行总数量
     *
     * @return
     */
    public Long selectAllStarRankPageAmount() {
        return topicLikedAndTreadAndBrowseModelMapper.selectAllStarRankPageAmount();
    }
    /**
     * 获取
     * @param topicId
     * @return
     */
    public TopicLikedAndTreadAndBrowseModel selectSingleByTopicId(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapper.selectByTopicId(topicId);
    }

    public Long selectTopicStarAmountByTopicId(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapper.selectByTopicId(topicId).getLikedAmount();
    }
    public Long selectTopicTreadAmountByTopicId(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapper.selectByTopicId(topicId).getTreadAmount();
    }
    public Long selectTopicBrowseAmountByTopicId(Long topicId) {
        return topicLikedAndTreadAndBrowseModelMapper.selectByTopicId(topicId).getBrowseAmount();
    }

    /**
     * 获取点赞排行著述id,每页20条
     * @param page
     * @return
     */
    public List<Long> selectStarRank(Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return topicLikedAndTreadAndBrowseModelMapper.selectStarRank(start,end);
    }


    /**
     * 更改著述点赞数量
     * @param topicId
     * @return
     */
    public int updateStarAmountByTopicId(Long topicId) {
        TopicLikedAndTreadAndBrowseModel model = topicLikedAndTreadAndBrowseModelMapper.selectByTopicId(topicId);
        model.setLikedAmount(model.getLikedAmount() + 1);
        return topicLikedAndTreadAndBrowseModelMapper.updateById(model);
    }
    /**
     * 更改著述点踩数量
     * @param topicId
     * @return
     */
    public int updateTreadAmountByTopicId(Long topicId) {
        TopicLikedAndTreadAndBrowseModel model = topicLikedAndTreadAndBrowseModelMapper.selectByTopicId(topicId);
        model.setTreadAmount(model.getTreadAmount() + 1);
        return topicLikedAndTreadAndBrowseModelMapper.updateById(model);
    }

    public int updateByModel(TopicLikedAndTreadAndBrowseModel model) {
        return topicLikedAndTreadAndBrowseModelMapper.updateById(model);
    }

    /**
     * 创建赞踩表
     * @param topicId
     * @return
     */
    public int insertByTopicId(Long topicId) {
        TopicLikedAndTreadAndBrowseModel model = new TopicLikedAndTreadAndBrowseModel();
        model.setTreadAmount(0L);
        model.setLikedAmount(0L);
        model.setBrowseAmount(0L);
        model.setTopicId(topicId);
        model.setIsBlack(0);
        model.setVersion(1);
        Long time = new Date().getTime();
        model.setCreateTime(time);
        model.setUpdateTime(time);
        return topicLikedAndTreadAndBrowseModelMapper.insert(model);
    }
}
