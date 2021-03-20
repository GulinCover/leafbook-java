package org.leafbook.serviceCommentApi.dao.comment;

import org.leafbook.api.modelApi.commentInfo.CommentStarAndTreadModel;
import org.springframework.stereotype.Service;

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

    public int updateByModel(CommentStarAndTreadModel model) {
        return new Random().nextInt(100);
    }
}
