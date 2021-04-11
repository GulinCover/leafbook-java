package org.leafbook.serviceCommentApi.service;

import org.leafbook.api.modelApi.commentInfo.*;
import org.leafbook.api.modelApi.talkInfo.Talk2EntryModel;
import org.leafbook.api.modelApi.talkInfo.Talk2StarAndTreadModel;
import org.leafbook.api.modelApi.talkInfo.commentInfo.TalkComment1Info2EntryInfoModel;
import org.leafbook.serviceCommentApi.dao.Comment2StarAndTreadModelMapper;
import org.leafbook.serviceCommentApi.dao.CommentModelMapper;
import org.leafbook.serviceCommentApi.dao.comment.*;
import org.leafbook.serviceCommentApi.dao.talk.Talk2EntryModelMapper;
import org.leafbook.serviceCommentApi.dao.talk.Talk2EntryShowModelMapper;
import org.leafbook.serviceCommentApi.dao.talk.Talk2StarAndTreadModelMapper;
import org.leafbook.serviceCommentApi.dao.talk.comment.TalkComment1Info2EntryInfoModelMapper;
import org.leafbook.serviceCommentApi.dao.talk.comment.TalkComment1Info2EntryShowModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CommentRelatedServiceRpc {
    @Autowired
    private Comment2StarAndTreadModelMapper comment2StarAndTreadModelMapper;
    @Autowired
    private Comment1Info2EntryModelMapper comment1Info2EntryModelMapper;
    @Autowired
    private Comment1InfoEntryShowModelMapper comment1InfoEntryShowModelMapper;
    @Autowired
    private CommentModelMapper commentModelMapper;
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
     * 获取所有一级评论
     * @param topicId
     * @return
     */
    public List<CommentModel> getSelectMultiComment1Info(Long topicId) {
//        return commentModelMapper.selectMultiComment1InfoByTopicId(topicId);
        return null;
    }
    /**
     * 发布一级评论
     * @param userId
     * @param topicId
     * @param comment1Content
     * @return
     */
    public int postPublicComment1Info(Long userId,Long topicId,String comment1Content) {
        CommentModel commentModel = new CommentModel();
        commentModel.setUserId(userId);
        commentModel.setTopicId(topicId);
        commentModel.setContent(comment1Content);
        Long comment1Id = commentModelMapper.insertComment1InfoByModel(commentModel);
        if (comment1Id != 0) return 1;
        return 0;
    }
    /**
     * 添加评论词条（定时任务获取前10进行展示）
     * @param userId
     * @param comment1Id
     * @param entryId
     * @return
     */
    public int postAddEntryInfoForComment1Info(Long userId,Long comment1Id,Long entryId) {
        Comment1Info2EntryModel model = new Comment1Info2EntryModel();
        model.setComment1Id(comment1Id);
        model.setEntryId(entryId);
        model.setUserId(userId);
        return comment1Info2EntryModelMapper.insertByModel(model);
    }
    /**
     * 获取评论词条
     * @param comment1Id
     * @return
     */
    public List<Long> getSelectEntryInfoForComment1Info(Long comment1Id) {
        return comment1InfoEntryShowModelMapper.selectByComment1Id(comment1Id);
    }
    /**
     * 根据一级评论id获取所有二级评论
     * @param comment1Id
     * @return
     */
    public List<CommentModel> getSelectMultiComment2Info(Long comment1Id) {
//        return commentModelMapper.selectMultiComment2InfoByComment1Id(comment1Id);
        return null;
    }

    /**
     * 发布二级评论
     * @param userId
     * @param comment1Id
     * @param comment2Content
     * @return
     */
    public int postPublicComment2Info(Long userId,Long comment1Id,String comment2Content) {
        CommentModel commentModel = commentModelMapper.selectSingleByComment1Id(comment1Id);
        if (Objects.nonNull(commentModel)) {
            commentModel.setUserId(userId);
            commentModel.setContent(comment2Content);
            Long comment2Id = commentModelMapper.insertComment2InfoByModel(commentModel);
            if (comment2Id != 0) return 1;
            return 1;
        } else {
            return 0;
        }

    }
    /**
     * 一级评论点赞
     * @param comment1Id
     * @return
     */
    public int postTouchStarComment1Info(Long comment1Id) {
        CommentStarAndTreadModel model = comment2StarAndTreadModelMapper.selectSingleByComment1Id(comment1Id);
        final Long starAmount = model.getStarAmount();
        model.setStarAmount(starAmount+1);
        return comment2StarAndTreadModelMapper.updateByModel(model);
    }
    /**
     * 一级评论点踩
     * @param comment1Id
     * @return
     */
    public int postTouchTreadComment1Info(Long comment1Id) {
        CommentStarAndTreadModel model = comment2StarAndTreadModelMapper.selectSingleByComment1Id(comment1Id);
        final Long treadAmount = model.getTreadAmount();
        model.setTreadAmount(treadAmount+1);
        return comment2StarAndTreadModelMapper.updateByModel(model);
    }
    /**
     * 随机获取多篇评论
     *
     * @param topicId
     * @param randomNumber
     * @return
     */
    public List<CommentModel> getSelectRandomComment1Info(Long topicId,Integer randomNumber) {
        return commentModelMapper.selectMultiRandomComment1InfoByTopicId(topicId,randomNumber);
    }
    /**
     * 获取评论词条
     * @param comment1Id
     * @return
     */
    public List<Long> getSelectMultiEntryIdsByComment1Id(Long comment1Id) {
        return comment1InfoEntryShowModelMapper.selectEntryIdsByComment1Id(comment1Id);
    }

    /**
     * 获取一级普通评论
     * @param comment1Id
     * @return
     */
    public CommentModel postSelectSingleComment1Info(Long comment1Id) {
        return commentModelMapper.selectSingleByComment1Id(comment1Id);
    }
    /**
     * 更新普通评论的点赞数
     * @param comment1Id
     * @return
     */
    public int postUpdateComment1InfoTouchStarAmount(Long comment1Id) {
        return comment2StarAndTreadModelMapper.updateTouchStarAmountByComment1Id(comment1Id);
    }
    /**
     * 更新普通评论的点踩数
     * @param comment1Id
     * @return
     */
    public int postUpdateComment1InfoTouchTreadAmount(Long comment1Id) {
        return comment2StarAndTreadModelMapper.updateTouchTreadAmountByComment1Id(comment1Id);
    }

    /**
     * 获取议论信息
     *
     * @param topicId
     * @return
     */
    public List<CommentModel> getSelectMultiTalkInfo(Long topicId) {
        return commentModelMapper.selectMultiByTopicId(topicId);
    }

    /**
     * 获取议论一级评论
     *
     * @param talkId
     * @return
     */
    public List<CommentModel> getSelectMultiTalkComment1Info(Long talkId) {
        return commentModelMapper.selectMultiTalkComment1InfoByTalkId(talkId);
    }

    /**
     * 获取议论二级评论
     *
     * @param talkComment1Id
     * @return
     */
    public List<CommentModel> getSelectMultiTalkComment2Info(Long talkComment1Id) {
        return commentModelMapper.selectMultiTalkComment2InfoByTalkComment1Id(talkComment1Id);
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

        CommentModel talkModel = new CommentModel();
        talkModel.setUserId(userId);
        talkModel.setTalkTitle(talkTitle);
        talkModel.setTopicId(topicId);
        talkModel.setTalkDesc(talkDesc);
        final Long talkId = commentModelMapper.insertTalkInfoByModel(talkModel);
        if (talkId != 0) return 1;
        return 0;
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
        CommentModel talkInfo = commentModelMapper.selectSingleByTalkId(talkId);
        if (Objects.nonNull(talkInfo)) {
            talkInfo.setUserId(userId);
            talkInfo.setContent(talkCommentContent);
            final Long talkComment1Id = commentModelMapper.insertTalkComment1InfoByModel(talkInfo);
            if (talkComment1Id != 0) return 1;
            return 0;
        } else {
            return 0;
        }
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
        CommentModel talkComment1Info = commentModelMapper.selectSingleByTalkComment1Id(talkComment1Id);
        if (Objects.nonNull(talkComment1Info)) {
            talkComment1Info.setUserId(userId);
            talkComment1Info.setContent(talkComment2Content);
            final Long talkComment2Id = commentModelMapper.insertTalkComment2InfoByModel(talkComment1Info);
            if (talkComment2Id != 0) return 1;
            return 0;
        } else {
            return 0;
        }
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
        return commentModelMapper.selectDetectLegalityForTalkId(talkId);
    }

    /**
     * 判断talkComment1是否存在
     *
     * @param talkComment1Id
     * @return
     */
    public int postIsExistForTalkComment1Info(Long talkComment1Id) {
        return commentModelMapper.selectDetectLegalityForTalkComment1Id(talkComment1Id);
    }
    /**
     * 随机获取一篇议论
     * @param topicId
     * @param randomNumber
     * @return
     */
    public List<CommentModel> getSelectRandomTalkInfo(Long topicId,Integer randomNumber) {
        return commentModelMapper.selectMultiRandomComment1InfoByTopicId(topicId,randomNumber);
    }
    /**
     * 随机获取一篇议论的评论
     * @param talkId
     * @param randomNumber
     * @return
     */
    public List<CommentModel> getSelectRandomTalkComment1Info(Long talkId,Integer randomNumber) {
        return commentModelMapper.selectMultiRandomTalkComment1InfoByTalkId(talkId,randomNumber);
    }
    /**
     * 获取议论
     * @param talkId
     * @return
     */
    public CommentModel postSelectSingleTalkInfoRpc(Long talkId) {
        return commentModelMapper.selectSingleByTalkId(talkId);
    }
    /**
     * 获取一级议论评论
     * @param talkComment1Id
     * @return
     */
    public CommentModel postSelectSingleTalkComment1Info(Long talkComment1Id) {
        return commentModelMapper.selectSingleByTalkComment1Id(talkComment1Id);
    }
    /**
     * 更新talk点赞数量
     * @param talkId
     * @return
     */
    public int postUpdateTalkInfoTouchStarAmount(Long talkId) {
        return talk2StarAndTreadModelMapper.updateTalkInfoTouchStarAmountByTalkId(talkId);
    }
    /**
     * 更新talk点踩数量
     * @param talkId
     * @return
     */
    public int postUpdateTalkInfoTouchTreadAmount(Long talkId) {
        return talk2StarAndTreadModelMapper.updateTalkInfoTouchTreadAmountByTalkId(talkId);

    }
    /**
     * 更新talk评论点赞数量
     * @param talkComment1Id
     * @return
     */
    public int postUpdateTalkComment1InfoTouchStarAmount(Long talkComment1Id) {
        return talk2StarAndTreadModelMapper.updateTalkComment1InfoTouchStarAmountByTalkComment1Id(talkComment1Id);

    }
    /**
     * 更新talk评论点踩数量
     * @param talkComment1Id
     * @return
     */
    public int postUpdateTalkComment1InfoTouchTreadAmount(Long talkComment1Id) {
        return talk2StarAndTreadModelMapper.updateTalkComment1InfoTouchTreadAmountByTalkComment1Id(talkComment1Id);

    }

}
