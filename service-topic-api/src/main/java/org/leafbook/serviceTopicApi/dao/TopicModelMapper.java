package org.leafbook.serviceTopicApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.topicInfo.TopicModel;

import java.util.List;

@Mapper
public interface TopicModelMapper extends BaseMapper<TopicModel> {
    Long selectAllTopicInfoPageAmount();

    Integer selectIsExistTopicId(
            @Param("topicId") Long topicId
    );

    List<TopicModel> selectMultiTopicInfoByUserId(
            @Param("userId") Long userId,
            @Param("start") Long start,
            @Param("end") Long end);

    Long selectMultiTopicInfoAmountByUserId(
            @Param("userId") Long userId
    );

    List<TopicModel> selectSearchMeMultiTopicInfo(
            @Param("userId")Long userId,
            @Param("blurry")String blurry,
            @Param("start")Long start,
            @Param("end")Long end);

    Long selectSingleForOwnerId(
            @Param("topicId")Long topicId);

    Integer selectDecideForTopicIdByUserId(
            @Param("userId")Long userId,
            @Param("topicId")Long topicId);

    TopicModel selectByTopicId(
            @Param("topicId")Long topicId);

    List<TopicModel> selectMultiTopicInfoByTopicIds(
            @Param("topicIds")List<Long> topicIds);

    List<TopicModel> selectMultiMeLastTopicInfo(
            @Param("userId")Long userId,
            @Param("number")Integer number);

    List<TopicModel> selectSearchMultiTopicInfo(
            @Param("content") String content,
            @Param("startTime") Long startTime,
            @Param("endTime") Long endTime,
            @Param("start") Long start,
            @Param("end") Long end
    );

    Long selectSearchMultiTopicInfoAmount(
            @Param("content") String content,
            @Param("startTime") Long startTime,
            @Param("endTime") Long endTime
    );

    int updateSingleTopicInfoOwner(
            @Param("userId")Long userId,
            @Param("topicId")Long topicId);

    int updateSingleTopicDesc(
            @Param("topicId") Long topicId,
            @Param("desc") String desc);

    int updateSingleTopicCover(
            @Param("topicId") Long topicId,
            @Param("cover") String cover);


    List<Long> joinSelectMultiTopic(
            @Param("entryIds") List<Long> entryIds,
            @Param("content") String content,
            @Param("startTime") Long startTime,
            @Param("endTime") Long endTime,
            @Param("start") Long start,
            @Param("end") Long end
    );

    Long joinSelectMultiTopicAmount(
            @Param("entryIds") List<Long> entryIds,
            @Param("content") String content,
            @Param("startTime") Long startTime,
            @Param("endTime") Long endTime
    );

    List<TopicModel> jointTopicLikedAndTreadModelTableSelectSearchMultiTopicInfo(
            @Param("start") Long start,
            @Param("end") Long end,
            @Param("blurry") String blurry,
            @Param("entry") Long entry,
            @Param("startTime") Long startTime,
            @Param("endTime") Long endTime
    );

    Long jointTopicLikedAndTreadModelTableSelectSearchMultiTopicInfoPageAmount(
            @Param("start") Long start,
            @Param("end") Long end,
            @Param("blurry") String blurry,
            @Param("entry") Long entry,
            @Param("startTime") Long startTime,
            @Param("endTime") Long endTime
    );

    Long joinSelectTopicAmountByEntryId(@Param("entryId")Long entryId);

    List<Long> joinSelectRandomTopicIdsByEntryIdForAuction(
            @Param("entryId")Long entryId,
            @Param("start")Long start,
            @Param("end")Long end
    );

}













