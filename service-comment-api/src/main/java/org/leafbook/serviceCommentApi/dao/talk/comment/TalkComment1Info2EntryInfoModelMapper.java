package org.leafbook.serviceCommentApi.dao.talk.comment;

import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment1Info2EntryInfoModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TalkComment1Info2EntryInfoModelMapper {
    public int insertByModel(TalkComment1Info2EntryInfoModel model) {
        return new Random().nextInt(100);
    }
}
