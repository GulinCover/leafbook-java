package org.leafbook.serviceTopicApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.topicInfo.ContributorModel;

import java.util.List;

@Mapper
public interface ContributorModelMapper extends BaseMapper<ContributorModel> {
    List<ContributorModel> selectMultiRandomContributorInfoByTopicId(@Param("topicId")Long topicId,@Param("randomNumber")Integer randomNumber);

    Long selectUserIdAmount(@Param("topicId")Long topicId);

    List<Long> selectMultiUserIds(
            @Param("topicId")Long topicId,
            @Param("start")Long start,
            @Param("end")Long end
    );
}
