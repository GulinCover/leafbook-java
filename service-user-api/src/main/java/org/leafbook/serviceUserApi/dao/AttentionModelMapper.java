package org.leafbook.serviceUserApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.userInfo.AttentionModel;

import java.util.List;

@Mapper
public interface AttentionModelMapper extends BaseMapper<AttentionModel> {
    List<Long> selectMultiAttentionUserIds(
            @Param("userId")Long userId,
            @Param("start")Long start,
            @Param("end")Long end);

    Integer selectDetectIsExistAttention(
            @Param("userId")Long userId,
            @Param("attentionId")Long attentionId
    );

    int deleteByLogic(
            @Param("userId")Long userId,
            @Param("attentionId")Long attentionUserId);
}
