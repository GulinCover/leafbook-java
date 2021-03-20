package org.leafbook.serviceCommentApi.dao.talk.comment;

import org.leafbook.api.modelApi.talkInfo.TalkModel;
import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment1Model;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class TalkComment1ModelMapper {

    public int selectIsExistByTalkComment1Id(Long talkComment1Id) {
        return new Random().nextInt(100);
    }

    public List<TalkComment1Model> selectMultiByTalkId(Long talkId) {
        List<TalkComment1Model> talkComment1ModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(15); i++) {
            TalkComment1Model talkComment1Model = new TalkComment1Model();
            talkComment1Model.setTalkId(talkId);
            talkComment1Model.setTalkCommentContent(TestModel.randomWord());
            talkComment1Model.setUserId((long) i);

            talkComment1ModelList.add(talkComment1Model);
        }

        return talkComment1ModelList;
    }

    public int insertByModel(TalkComment1Model talkComment1Model) {
        return new Random().nextInt(100);
    }
}
