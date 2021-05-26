package org.leafbook.serviceCommentApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.commentInfo.CommentStarAndTreadModel;

@Mapper
public interface Comment2StarAndTreadModelMapper extends BaseMapper<CommentStarAndTreadModel> {
    CommentStarAndTreadModel selectSingleByComment1Id(@Param("comment1Id")Long comment1Id);
    CommentStarAndTreadModel selectSingleByTalkComment1Id(@Param("talkComment1Id")Long talkComment1Id);
    CommentStarAndTreadModel selectSingleByTalkId(@Param("talkId")Long talkId);

    int updateTouchStarAmountByComment1Id(
            @Param("comment1Id")Long comment1Id,
            @Param("time")Long time
            );
    int updateTouchTreadAmountByComment1Id(
            @Param("comment1Id")Long comment1Id,
            @Param("time")Long time
    );

    int updateTouchTreadAmountByTalkComment1Id(
            @Param("talkComment1Id")Long talkComment1Id,
            @Param("time")Long time
    );
    int updateTouchStarAmountByTalkComment1Id(
            @Param("talkComment1Id")Long talkComment1Id,
            @Param("time")Long time
    );

    int updateTouchTreadAmountByTalkId(
            @Param("talkId")Long talkId,
            @Param("time")Long time
    );
    int updateTouchStarAmountByTalkId(
            @Param("talkId")Long talkId,
            @Param("time")Long time
    );
}








