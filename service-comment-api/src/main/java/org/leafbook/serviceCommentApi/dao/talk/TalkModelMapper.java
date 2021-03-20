package org.leafbook.serviceCommentApi.dao.talk;

import org.leafbook.api.modelApi.talkInfo.TalkModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class TalkModelMapper {

    public int selectIsExistByTalkId(Long talkId) {
        return new Random().nextInt(100);
    }

    public List<TalkModel> selectMultiByTopicId(Long topicId) {
        List<TalkModel> talkModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(15); i++) {
            TalkModel talkModel = new TalkModel();
            talkModel.setTalkId((long) i);
            talkModel.setTalkDesc(TestModel.randomWord());
            talkModel.setTalkTitle(TestModel.randomWord());
            talkModel.setUserId((long) i);

            talkModelList.add(talkModel);
        }

        return talkModelList;
    }

    public int insertByModel(TalkModel talkModel) {
        return new Random().nextInt(100);
    }
}
