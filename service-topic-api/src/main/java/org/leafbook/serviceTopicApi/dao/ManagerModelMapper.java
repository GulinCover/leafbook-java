package org.leafbook.serviceTopicApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.topicInfo.ManagerModel;

import java.util.List;

@Mapper
public interface ManagerModelMapper extends BaseMapper<ManagerModel> {
    List<Long> selectMultiManagerIdsByTopicId(@Param("topicId")Long topicId);

    Long selectMultiManagerIdAmount(@Param("topicId")Long topicId);

    Integer selectDecideManagerIdWithTopicId(@Param("userId")Long userId,@Param("topicId")Long topicId);


    int deleteManagerId(@Param("userId") Long userId, @Param("topicId") Long topicId);
}
