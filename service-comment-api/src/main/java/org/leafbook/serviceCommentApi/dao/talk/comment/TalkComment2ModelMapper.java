package org.leafbook.serviceCommentApi.dao.talk.comment;

import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment1Model;
import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment2Model;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class TalkComment2ModelMapper {
    public List<TalkComment2Model> selectMultiByTalkComment1Id(Long talkComment1Id) {
        List<TalkComment2Model> talkComment2ModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(15); i++) {
            TalkComment2Model talkComment1Model = new TalkComment2Model();
            talkComment1Model.setTalkComment1Id(talkComment1Id);
            talkComment1Model.setTalkCommentContent(TestModel.randomWord());
            talkComment1Model.setUserId((long) i);

            talkComment2ModelList.add(talkComment1Model);
        }

        return talkComment2ModelList;
    }

    public int insertByModel(TalkComment2Model talkComment2Model) {
        return new Random().nextInt(100);
    }
}
