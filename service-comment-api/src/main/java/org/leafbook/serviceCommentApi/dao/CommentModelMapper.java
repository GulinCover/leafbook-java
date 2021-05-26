package org.leafbook.serviceCommentApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.commentInfo.CommentModel;

import java.util.List;

@Mapper
public interface CommentModelMapper extends BaseMapper<CommentModel> {
    //单查询
    CommentModel selectSingleByTalkId(@Param("talkId")Long talkId);
    CommentModel selectSingleByComment1Id(@Param("comment1Id")Long comment1Id);
    CommentModel selectSingleByComment2Id(@Param("comment2Id")Long comment2Id);
    CommentModel selectSingleByTalkComment1Id(@Param("talkComment1Id")Long talkComment1Id);
    CommentModel selectSingleByTalkComment2Id(@Param("talkComment2Id")Long talkComment2Id);

    //组查询
    List<CommentModel> selectMultiTalkInfoByUserId(@Param("userId")Long userId);
    List<CommentModel> selectMultiComment1InfoByUserId(@Param("userId")Long userId);
    List<CommentModel> selectMultiComment2InfoByUserId(@Param("userId")Long userId);
    List<CommentModel> selectMultiTalkComment1InfoByUserId(@Param("userId")Long userId);
    List<CommentModel> selectMultiTalkComment2InfoByUserId(@Param("userId")Long userId);

    //分页组查询
    List<CommentModel> selectMultiTalkInfoByTopicId(
            @Param("topicId")Long topicId,
            @Param("start")Long start,
            @Param("end")Long end
    );
    List<CommentModel> selectMultiComment1InfoByTopicId(
            @Param("topicId")Long topicId,
            @Param("start")Long start,
            @Param("end")Long end
    );
    List<CommentModel> selectMultiComment2InfoByTopicId(
            @Param("topicId")Long topicId,
            @Param("start")Long start,
            @Param("end")Long end
    );
    List<CommentModel> selectMultiTalkComment1InfoByTopicId(
            @Param("topicId")Long topicId,
            @Param("start")Long start,
            @Param("end")Long end
    );
    List<CommentModel> selectMultiTalkComment2InfoByTopicId(
            @Param("topicId")Long topicId,
            @Param("start")Long start,
            @Param("end")Long end
    );
    List<CommentModel> selectMultiComment2InfoByComment1Id(
            @Param("comment1Id")Long comment1Id,
            @Param("start")Long start,
            @Param("end")Long end
    );
    List<CommentModel> selectMultiTalkComment2InfoByTalkComment1Id(
            @Param("talkComment1Id")Long talkComment1Id,
            @Param("start")Long start,
            @Param("end")Long end
    );

    //随机获取
    List<CommentModel> selectMultiRandomTalkInfoByTopicId(
            @Param("topicId")Long topicId,
            @Param("random")Long random
    );
    List<CommentModel> selectMultiRandomComment1InfoByTopicId(
            @Param("topicId")Long topicId,
            @Param("random")Long random
    );
    List<CommentModel> selectMultiRandomComment2InfoByTopicId(
            @Param("topicId")Long topicId,
            @Param("random")Long random
    );
    List<CommentModel> selectMultiRandomTalkComment1InfoByTopicId(
            @Param("topicId")Long topicId,
            @Param("random")Long random
    );
    List<CommentModel> selectMultiRandomTalkComment2InfoByTopicId(
            @Param("topicId")Long topicId,
            @Param("random")Long random
    );
    List<CommentModel> selectMultiRandomTalkComment1InfoByTalkId(
            @Param("talkId")Long talkId,
            @Param("random")Long random
    );

    //根据talkId分页

    List<CommentModel> selectMultiTalkComment1InfoByTalkId(
            @Param("talkId")Long talkId,
            @Param("start")Long start,
            @Param("end")Long end
    );
    List<CommentModel> selectMultiTalkComment2InfoByTalkId(
            @Param("talkId")Long talkId,
            @Param("start")Long start,
            @Param("end")Long end
    );
    List<CommentModel> selectAllTalkCommentInfoByTalkId(
            @Param("talkId")Long talkId,
            @Param("start")Long start,
            @Param("end")Long end
    );

    //最新
    List<CommentModel> selectMultiLatestAllCommentInfo(
            @Param("topicId")Long topicId,
            @Param("number")Integer number
    );

    //用户所有评论
    List<CommentModel> selectMultiCommentInfoByUserId(
            @Param("userId")Long userId,
            @Param("start")Long start,
            @Param("end")Long end
    );

    //总查询
    List<CommentModel> selectAllInfoByUserId(
            @Param("userId")Long userId
    );
    List<CommentModel> selectAllInfoByTopicId(
            @Param("topicId")Long topicId
    );
    List<CommentModel> selectAllInfoByTalkId(
            @Param("talkId")Long talkId
    );

    //数量查询
    Long selectMultiTalkInfoAmountByUserId(
            @Param("userId")Long userId
    );
    Long selectMultiComment1InfoAmountByUserId(
            @Param("userId")Long userId
    );
    Long selectMultiComment2InfoAmountByUserId(
            @Param("userId")Long userId
    );
    Long selectMultiTalkComment1InfoAmountByUserId(
            @Param("userId")Long userId
    );
    Long selectMultiTalkComment2InfoAmountByUserId(
            @Param("userId")Long userId
    );

    Long selectAllTypeCommentInfoAmountByUserId(
            @Param("userId")Long userId
    );
    Long selectAllTalkCommentInfoAmountByUserId(
            @Param("userId")Long userId
    );
    Long selectAllCommentInfoAmountByUserId(
            @Param("userId")Long userId
    );

    Long selectAllTypeCommentInfoAmountByTopicId(
            @Param("topicId")Long topicId
    );
    Long selectAllCommentInfoAmountByTopicId(
            @Param("topicId")Long topicId
    );
    Long selectAllTalkCommentInfoAmountByTopicId(
            @Param("topicId")Long topicId
    );

    Long selectAllTalkCommentInfoAmountByTalkId(
            @Param("talkId")Long talkId
    );

    //单一数量
    Long selectAllComment1InfoAmountByTopicId(
            @Param("topic")Long topic
    );
    Long selectAllComment2InfoAmountByComment1Id(
            @Param("comment1Id")Long comment1Id
    );
    Long selectAllTalkComment1InfoAmountByTalkId(
            @Param("talkId")Long talkId
    );
    Long selectAllTalkComment2InfoAmountByTalkComment1Id(
            @Param("talkComment1Id")Long talkComment1Id
    );

    //检查合法性
    Integer selectDetectLegalityForTalkId(@Param("talkId")Long talkId);
    Integer selectDetectLegalityForComment1Id(@Param("talkId")Long comment1Id);
    Integer selectDetectLegalityForComment2Id(@Param("talkId")Long comment2Id);
    Integer selectDetectLegalityForTalkComment1Id(@Param("talkId")Long talkComment1Id);
    Integer selectDetectLegalityForTalkComment2Id(@Param("talkId")Long talkComment2Id);
}













