package org.leafbook.serviceRecordApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.recordInfo.BrowseHistoryModel;

import java.util.List;

@Mapper
public interface BrowseHistoryModelMapper extends BaseMapper<BrowseHistoryModel> {
    Integer selectDetectIsExistArticleIdByUserId(@Param("userId")Long userId,@Param("articleId")Long articleId);
    Long selectTopicBrowseAmount(@Param("topicId")Long topicId);
    List<BrowseHistoryModel> selectMultiBrowseHistoryByUserId(
            @Param("userId")Long userId,
            @Param("start")Long start,
            @Param("end")Long end
            );


    int deleteSingleBrowseHistoryByUserId(
            @Param("userId")Long userId,
            @Param("browseHistoryId")Long browseHistoryId
    );

    int deleteMultiBrowseHistoryByUserId(
            @Param("userId")Long userId,
            @Param("browseHistoryIds")List<Long> browseHistoryIds
    );
}
