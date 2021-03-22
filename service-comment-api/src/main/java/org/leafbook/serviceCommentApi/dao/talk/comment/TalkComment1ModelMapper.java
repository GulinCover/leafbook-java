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
    /**
     * 随机获取一篇议论
     * @param topicId
     * @param randomNumber
     * @return
     */
    public List<TalkModel> selectRandomTalkInfoByTopicId(Long topicId,Integer randomNumber) {
        List<TalkModel> talkModelList = new LinkedList<>();
        for (int i = 0; i < randomNumber; i++) {
            TalkModel talkModel = new TalkModel();
            talkModel.setTalkId((long) i);
            talkModel.setTalkTitle(TestModel.randomWord());
            talkModel.setTalkDesc(TestModel.randomWord());
            talkModel.setUserId((long) i);

            talkModelList.add(talkModel);
        }

        return talkModelList;
    }
    /**
     * 随机获取一篇议论的评论
     * @param talkId
     * @param randomNumber
     * @return
     */
    public List<TalkComment1Model> selectRandomTalkComment1InfoByTalkId(Long talkId,Integer randomNumber) {
        List<TalkComment1Model> talkComment1ModelList = new LinkedList<>();
        for (int i = 0; i < randomNumber; i++) {
            TalkComment1Model talkComment1Model = new TalkComment1Model();
            talkComment1Model.setTalkId(talkId);
            talkComment1Model.setTalkCommentContent(TestModel.randomWord());
            talkComment1Model.setUserId((long) i);
            talkComment1Model.setTalkComment1Id((long) i);

            talkComment1ModelList.add(talkComment1Model);
        }

        return talkComment1ModelList;
    }

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
