package org.leafbook.serviceTopicApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.topicInfo.TopicEntryInfoShowModel;

import java.util.List;

@Mapper
public interface TopicEntryInfoShowModelMapper extends BaseMapper<TopicEntryInfoShowModel> {
    List<Long> selectMultiEntryIdsByTopicIds(@Param("topicIds") List<Long> topicIds);
    Long selectTopicAmountByEntryId(@Param("entryId") Long entryId);
    List<Long> selectMultiEntryIdsByTopicId(@Param("topicId") Long topicId);
    List<Long> selectMultiTopicIdsByEntryId(
            @Param("entryId")Long entryId,
            @Param("start")Long start,
            @Param("end")Long end
    );

    List<Long> selectRandomTopicIdsByEntryId(
            @Param("entryId")Long entryId,
            @Param("number")Long number
    );

    int insertBatchByModels(
            @Param("models")List<TopicEntryInfoShowModel> models
    );


}
