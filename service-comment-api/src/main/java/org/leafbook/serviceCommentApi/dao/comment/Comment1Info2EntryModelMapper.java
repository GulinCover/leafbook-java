package org.leafbook.serviceCommentApi.dao.comment;

import org.leafbook.api.modelApi.commentInfo.Comment1Info2EntryModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Comment1Info2EntryModelMapper {
    public int insertByModel(Comment1Info2EntryModel model) {
        return new Random().nextInt(100);
    }
}
