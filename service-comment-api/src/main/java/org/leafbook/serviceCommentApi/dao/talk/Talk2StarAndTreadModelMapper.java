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
    /**
     * 更新talk点赞数量
     * @param talkId
     * @return
     */
    public int updateTalkInfoTouchStarAmountByTalkId(Long talkId) {
        return 1;
    }
    /**
     * 更新talk点踩数量
     * @param talkId
     * @return
     */
    public int updateTalkInfoTouchTreadAmountByTalkId(Long talkId) {
        return 1;
    }
    /**
     * 更新talk评论点赞数量
     * @param talkComment1Id
     * @return
     */
    public int updateTalkComment1InfoTouchStarAmountByTalkComment1Id(Long talkComment1Id) {
        return 1;
    }
    /**
     * 更新talk评论点踩数量
     * @param talkComment1Id
     * @return
     */
    public int updateTalkComment1InfoTouchTreadAmountByTalkComment1Id(Long talkComment1Id) {
        return 1;
    }
}













