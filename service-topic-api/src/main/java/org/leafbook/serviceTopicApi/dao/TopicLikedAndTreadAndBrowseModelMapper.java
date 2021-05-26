package org.leafbook.serviceTopicApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.topicInfo.TopicLikedAndTreadAndBrowseModel;

import java.util.List;

@Mapper
public interface TopicLikedAndTreadAndBrowseModelMapper extends BaseMapper<TopicLikedAndTreadAndBrowseModel> {
    Long selectAllStarRankPageAmount();

    TopicLikedAndTreadAndBrowseModel selectByTopicId(
            @Param("topicId")Long topicId);

    List<Long> selectStarRank(
            @Param("start")Long start,
            @Param("end")Long end);
}
