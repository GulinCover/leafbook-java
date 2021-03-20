package org.leafbook.serviceCommentApi.dao.talk;

import org.leafbook.api.modelApi.talkInfo.Talk2StarAndTreadModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Talk2StarAndTreadModelMapper {

    public Talk2StarAndTreadModel selectByTalkId(Long talkId) {
        Talk2StarAndTreadModel model = new Talk2StarAndTreadModel();
        model.setStarAmount(546L);
        model.setTalkId(talkId);
        model.setTreadAmount(5646L);
        return model;
    }

    public int updateByModel(Talk2StarAndTreadModel model) {
        return new Random().nextInt(100);
    }
}
