package org.leafbook.serviceCommentApi.dao.talk;

import org.leafbook.api.modelApi.talkInfo.Talk2EntryModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Talk2EntryModelMapper {
    public int insertByModel(Talk2EntryModel model) {
        return new Random().nextInt(100);
    }
}
