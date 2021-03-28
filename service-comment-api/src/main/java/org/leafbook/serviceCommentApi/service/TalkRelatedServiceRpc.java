package org.leafbook.serviceCommentApi.service;

import org.leafbook.api.modelApi.talkInfo.Talk2EntryModel;
import org.leafbook.api.modelApi.talkInfo.Talk2StarAndTreadModel;
import org.leafbook.api.modelApi.talkInfo.TalkModel;
import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment1Info2EntryInfoModel;
import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment1Model;
import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment2Model;
import org.leafbook.serviceCommentApi.dao.talk.*;
import org.leafbook.serviceCommentApi.dao.talk.comment.TalkComment1Info2EntryInfoModelMapper;
import org.leafbook.serviceCommentApi.dao.talk.comment.TalkComment1Info2EntryShowModelMapper;
import org.leafbook.serviceCommentApi.dao.talk.comment.TalkComment1ModelMapper;
import org.leafbook.serviceCommentApi.dao.talk.comment.TalkComment2ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalkRelatedServiceRpc {
    @Autowired
    private TalkModelMapper talkModelMapper;
    @Autowired
    private TalkComment1ModelMapper talkComment1ModelMapper;
    @Autowired
    private TalkComment2ModelMapper talkComment2ModelMapper;
    @Autowired
    private Talk2StarAndTreadModelMapper talk2StarAndTreadModelMapper;
    @Autowired
    private Talk2EntryModelMapper talk2EntryModelMapper;
    @Autowired
    private Talk2EntryShowModelMapper talk2EntryShowModelMapper;
    @Autowired
    private TalkComment1Info2EntryInfoModelMapper talkComment1Info2EntryInfoModelMapper;
    @Autowired
    private TalkComment1Info2EntryShowModelMapper talkComment1Info2EntryShowModelMapper;

    /**
     * 获取议论信息
     *
     * @param topicId
     * @return
     */
    public List<TalkModel> getSelectMultiTalkInfo(Long topicId) {
        return talkModelMapper.selectMultiByTopicId(topicId);
    }

    /**
     * 获取议论一级评论
     *
     * @param talkId
     * @return
     */
    public List<TalkComment1Model> getSelectMultiTalkComment1Info(Long talkId) {
        return talkComment1ModelMapper.selectMultiByTalkId(talkId);
    }

    /**
     * 获取议论二级评论
     *
     * @param talkComment1Id
     * @return
     */
    public List<TalkComment2Model> getSelectMultiTalkComment2Info(Long talkComment1Id) {
        return talkComment2ModelMapper.selectMultiByTalkComment1Id(talkComment1Id);
    }

    /**
     * 发布talkInfo
     *
     * @param topicId
     * @param userId
     * @param talkTitle
     * @param talkDesc
     * @return
     */
    public int postPublicTalkInfo(Long topicId, Long userId, String talkTitle, String talkDesc) {
        TalkModel talkModel = new TalkModel();
        talkModel.setUserId(userId);
        talkModel.setTalkTitle(talkTitle);
        talkModel.setTopicId(topicId);
        talkModel.setTalkDesc(talkDesc);
        return talkModelMapper.insertByModel(talkModel);
    }

    /**
     * 发布talkComment1Info
     *
     * @param talkId
     * @param userId
     * @param talkCommentContent
     * @return
     */
    public int postPublicTalkComment1Info(Long talkId, Long userId, String talkCommentContent) {
        TalkComment1Model talkComment1Model = new TalkComment1Model();
        talkComment1Model.setUserId(userId);
        talkComment1Model.setTalkCommentContent(talkCommentContent);
        talkComment1Model.setTalkId(talkId);
        return talkComment1ModelMapper.insertByModel(talkComment1Model);
    }

    /**
     * 发布talkComment2Info
     *
     * @param talkComment1Id
     * @param userId
     * @param talkComment2Content
     * @return
     */
    public int postPublicTalkComment2Info(Long talkComment1Id, Long userId, String talkComment2Content) {
        TalkComment2Model talkComment2Model = new TalkComment2Model();
        talkComment2Model.setUserId(userId);
        talkComment2Model.setTalkCommentContent(talkComment2Content);
        talkComment2Model.setTalkComment1Id(talkComment1Id);
        return talkComment2ModelMapper.insertByModel(talkComment2Model);
    }

    /**
     * talkInfo点赞
     *
     * @param talkId
     * @return
     */
    public int postTouchStarTalkInfoRpc(Long talkId) {
        Talk2StarAndTreadModel model = talk2StarAndTreadModelMapper.selectByTalkId(talkId);
        final Long starAmount = model.getStarAmount();
        model.setStarAmount(starAmount + 1);
        return talk2StarAndTreadModelMapper.updateByModel(model);
    }

    /**
     * talkInfo点踩
     *
     * @param talkId
     * @return
     */
    public int postTouchTreadTalkInfoRpc(Long talkId) {
        Talk2StarAndTreadModel model = talk2StarAndTreadModelMapper.selectByTalkId(talkId);
        final Long starAmount = model.getTreadAmount();
        model.setTreadAmount(starAmount + 1);
        return talk2StarAndTreadModelMapper.updateByModel(model);
    }

    /**
     * 给talkInfo添加词条
     *
     * @param userId
     * @param talkId
     * @param entryId
     * @return
     */
    public int postAddTalkInfoForEntryInfo(Long userId, Long talkId, Long entryId) {
        Talk2EntryModel talk2EntryModel = new Talk2EntryModel();
        talk2EntryModel.setEntryId(entryId);
        talk2EntryModel.setTalkId(talkId);
        talk2EntryModel.setUserId(userId);
        return talk2EntryModelMapper.insertByModel(talk2EntryModel);
    }

    /**
     * 给talkInfo的一级评论添加词条
     *
     * @param userId
     * @param talkComment1Id
     * @param entryId
     * @return
     */
    public int postAddTalkComment1InfoForEntryInfo(Long userId, Long talkComment1Id, Long entryId) {
        TalkComment1Info2EntryInfoModel model = new TalkComment1Info2EntryInfoModel();
        model.setEntryId(entryId);
        model.setTalkComment1Id(talkComment1Id);
        model.setUserId(userId);
        return talkComment1Info2EntryInfoModelMapper.insertByModel(model);
    }


    /**
     * 获取talkInfo的词条
     *
     * @param talkId
     * @return
     */
    public List<Long> getSelectTalkInfoForEntryInfo(Long talkId) {
        return talk2EntryShowModelMapper.selectByTalkIdForMultiEntryInfo(talkId);
    }

    /**
     * 获取talkInfo的一级评论的词条
     *
     * @param talkComment1Id
     * @return
     */
    public List<Long> getSelectTalkComment1InfoForEntryInfo(Long talkComment1Id) {
        return talkComment1Info2EntryShowModelMapper.selectByTalkComment1IdForMultiEntryInfo(talkComment1Id);
    }

    /**
     * 判断talk是否存在
     *
     * @param talkId
     * @return
     */
    public int postIsExistForTalkInfo(Long talkId) {
        return talkModelMapper.selectIsExistByTalkId(talkId);
    }

    /**
     * 判断talkComment1是否存在
     *
     * @param talkComment1Id
     * @return
     */
    public int postIsExistForTalkComment1Info(Long talkComment1Id) {
        return talkComment1ModelMapper.selectIsExistByTalkComment1Id(talkComment1Id);
    }
    /**
     * 随机获取一篇议论
     * @param topicId
     * @param randomNumber
     * @return
     */
    public List<TalkModel> getSelectRandomTalkInfo(Long topicId,Integer randomNumber) {
        return talkComment1ModelMapper.selectRandomTalkInfoByTopicId(topicId,randomNumber);
    }
    /**
     * 随机获取一篇议论的评论
     * @param talkId
     * @param randomNumber
     * @return
     */
    public List<TalkComment1Model> getSelectRandomTalkComment1Info(Long talkId,Integer randomNumber) {
        return talkComment1ModelMapper.selectRandomTalkComment1InfoByTalkId(talkId,randomNumber);
    }
    /**
     * 获取议论
     * @param talkId
     * @return
     */
    public TalkModel postSelectSingleTalkInfoRpc(Long talkId) {
        return talkComment1ModelMapper.selectSingleTalkInfo(talkId);
    }
    /**
     * 获取一级议论评论
     * @param talkComment1Id
     * @return
     */
    public TalkComment1Model postSelectSingleTalkComment1Info(Long talkComment1Id) {
        return talkComment1ModelMapper.selectSingleTalkComment1Info(talkComment1Id);
    }

}
