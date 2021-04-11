package org.leafbook.serviceCommentApi.dao;

import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.api.modelApi.commentInfo.CommentStarAndTreadModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class Comment2StarAndTreadModelMapper {

    public CommentStarAndTreadModel selectSingleByComment1Id(Long commentId) {
        CommentStarAndTreadModel model = new CommentStarAndTreadModel();
        model.setStarAmount(100L);
        model.setTreadAmount(100L);
        model.setComment1Id(commentId);
        return model;
    }

    public CommentStarAndTreadModel selectSingleByTalkComment1Id(Long talkCommentId) {
        CommentStarAndTreadModel model = new CommentStarAndTreadModel();
        model.setStarAmount(100L);
        model.setTreadAmount(100L);
        model.setTalkComment1Id(talkCommentId);
        return model;
    }

    public CommentStarAndTreadModel selectSingleByTalkId(Long talkId) {
        CommentStarAndTreadModel model = new CommentStarAndTreadModel();
        model.setStarAmount(100L);
        model.setTreadAmount(100L);
        model.setTalkId(talkId);
        return model;
    }


    public int updateByModel(CommentStarAndTreadModel model) {
        return new Random().nextInt(100);
    }

    /**
     * 更新普通评论的点赞数
     * @param comment1Id
     * @return
     */
    public int updateTouchStarAmountByComment1Id(Long comment1Id) {
        return 1;
    }
    /**
     * 更新普通评论的点踩数
     * @param comment1Id
     * @return
     */
    public int updateTouchTreadAmountByComment1Id(Long comment1Id) {
        return 1;
    }

    public int updateTalkComment1InfoTouchTreadAmountByTalkComment1Id(Long talkComment1Id) {
        return 1;
    }

    public int updateTalkComment1InfoTouchStarAmountByTalkComment1Id(Long talkComment1Id) {
        return 1;
    }

    public int updateTalkInfoTouchTreadAmountByTalkId(Long talkId) {
        return 1;
    }
    public int updateTalkInfoTouchStarAmountByTalkId(Long talkId) {
        return 1;
    }

}
