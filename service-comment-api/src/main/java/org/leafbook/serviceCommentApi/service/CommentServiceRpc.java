package org.leafbook.serviceCommentApi.service;

import org.leafbook.api.modelApi.commentInfo.Comment2EntryModel;
import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.api.modelApi.commentInfo.CommentStarAndTreadModel;
import org.leafbook.serviceCommentApi.daoImpl.Comment2EntryModelMapperImpl;
import org.leafbook.serviceCommentApi.daoImpl.Comment2EntryShowModelMapperImpl;
import org.leafbook.serviceCommentApi.daoImpl.Comment2StarAndTreadModelMapperImpl;
import org.leafbook.serviceCommentApi.daoImpl.CommentModelMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Transactional
@Service
public class CommentServiceRpc {
    @Autowired
    private Comment2StarAndTreadModelMapperImpl comment2StarAndTreadModelMapperImpl;
    @Autowired
    private CommentModelMapperImpl commentModelMapperImpl;
    @Autowired
    private Comment2EntryModelMapperImpl comment2EntryModelMapperImpl;
    @Autowired
    private Comment2EntryShowModelMapperImpl comment2EntryShowModelMapperImpl;

    /**
     * 获取所有一级评论
     *
     * @param topicId
     * @return
     */
    public List<CommentModel> getSelectMultiComment1Info(Long topicId, Long page) {
        return commentModelMapperImpl.selectMultiComment1InfoByTopicId(topicId, page);
    }

    /**
     * 发布一级评论
     *
     * @param userId
     * @param topicId
     * @param comment1Content
     * @return
     */
    public int postPublicComment1Info(Long userId, Long topicId, String comment1Content) {
        CommentModel commentModel = new CommentModel();
        commentModel.setUserId(userId);
        commentModel.setTopicId(topicId);
        commentModel.setContent(comment1Content);
        Long comment1Id = commentModelMapperImpl.insertComment1InfoByModel(commentModel);
        if (comment1Id != 0) return 1;
        return 0;
    }

    /**
     * 添加评论词条（定时任务获取前10进行展示）
     *
     * @param userId
     * @param comment1Id
     * @param entryId
     * @return
     */
    public int postAddEntryInfoForComment1Info(Long userId, Long comment1Id, Long entryId) {
        Comment2EntryModel model = new Comment2EntryModel();
        model.setComment1Id(comment1Id);
        model.setEntryId(entryId);
        model.setUserId(userId);
        return comment2EntryModelMapperImpl.insertByModel(model);
    }

    /**
     * 获取评论词条
     *
     * @param comment1Id
     * @return
     */
    public List<Long> getSelectEntryInfoForComment1Info(Long comment1Id) {
        return comment2EntryShowModelMapperImpl.selectMultiEntryIdsByComment1Id(comment1Id);
    }

    /**
     * 根据一级评论id获取二级评论
     *
     * @param comment1Id
     * @return
     */
    public List<CommentModel> getSelectMultiComment2Info(Long comment1Id, Long page) {
        return commentModelMapperImpl.selectMultiComment2InfoByComment1Id(comment1Id, page);
    }

    /**
     * 发布二级评论
     *
     * @param userId
     * @param topicId
     * @param comment1Id
     * @param commentUserId
     * @param comment2Content
     * @return
     */
    public int postPublicComment2Info(Long userId, Long topicId, Long comment1Id, Long commentUserId, String comment2Content) {
        CommentModel commentModel = commentModelMapperImpl.selectSingleByComment1Id(comment1Id);
        if (Objects.nonNull(commentModel) && commentModel.getTopicId().equals(topicId)) {
            commentModel.setUserId(userId);
            commentModel.setContent(comment2Content);
            commentModel.setCommentedUserId(commentUserId);
            Long comment2Id = commentModelMapperImpl.insertComment2InfoByModel(commentModel);
            if (comment2Id != 0) return 1;
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * 一级评论点赞
     *
     * @param comment1Id
     * @return
     */
    public int postTouchStarComment1Info(Long comment1Id) {
        CommentStarAndTreadModel model = comment2StarAndTreadModelMapperImpl.selectSingleByComment1Id(comment1Id);
        if (Objects.isNull(model)) {
            CommentStarAndTreadModel retModel = new CommentStarAndTreadModel();
            retModel.setComment1Id(comment1Id);
            retModel.setTreadAmount(0L);
            retModel.setStarAmount(1L);
            return comment2StarAndTreadModelMapperImpl.insertByModel(retModel);
        } else {
            final Long starAmount = model.getStarAmount();
            model.setStarAmount(starAmount + 1);
            return comment2StarAndTreadModelMapperImpl.updateByModel(model);
        }
    }

    /**
     * 一级评论点踩
     *
     * @param comment1Id
     * @return
     */
    public int postTouchTreadComment1Info(Long comment1Id) {
        CommentStarAndTreadModel model = comment2StarAndTreadModelMapperImpl.selectSingleByComment1Id(comment1Id);
        if (Objects.isNull(model)) {
            CommentStarAndTreadModel retModel = new CommentStarAndTreadModel();
            retModel.setComment1Id(comment1Id);
            retModel.setTreadAmount(1L);
            retModel.setStarAmount(0L);
            return comment2StarAndTreadModelMapperImpl.insertByModel(retModel);
        } else {
            final Long starAmount = model.getStarAmount();
            model.setStarAmount(starAmount + 1);
            return comment2StarAndTreadModelMapperImpl.updateByModel(model);
        }
    }

    /**
     * 随机获取多篇评论
     *
     * @param topicId
     * @param randomNumber
     * @return
     */
    public List<CommentModel> getSelectRandomComment1Info(Long topicId, Long randomNumber) {
        return commentModelMapperImpl.selectMultiRandomComment1InfoByTopicId(topicId, randomNumber);
    }

    /**
     * 获取评论词条
     *
     * @param comment1Id
     * @return
     */
    public List<Long> getSelectMultiEntryIdsByComment1Id(Long comment1Id) {
        return comment2EntryShowModelMapperImpl.selectMultiEntryIdsByComment1Id(comment1Id);
    }

    /**
     * 获取一级普通评论
     *
     * @param comment1Id
     * @return
     */
    public CommentModel postSelectSingleComment1Info(Long comment1Id) {
        return commentModelMapperImpl.selectSingleByComment1Id(comment1Id);
    }

    /**
     * 更新普通评论的点赞数
     *
     * @param comment1Id
     * @return
     */
    public int postUpdateComment1InfoTouchStarAmount(Long comment1Id) {
        return comment2StarAndTreadModelMapperImpl.updateTouchStarAmountByComment1Id(comment1Id);
    }

    /**
     * 更新普通评论的点踩数
     *
     * @param comment1Id
     * @return
     */
    public int postUpdateComment1InfoTouchTreadAmount(Long comment1Id) {
        return comment2StarAndTreadModelMapperImpl.updateTouchTreadAmountByComment1Id(comment1Id);
    }

    /**
     * 获取议论信息
     *
     * @param topicId
     * @return
     */
    public List<CommentModel> getSelectMultiTalkInfo(Long topicId, Long page) {
        return commentModelMapperImpl.selectMultiTalkInfoByTopicId(topicId, page);
    }

    /**
     * 获取议论一级评论
     *
     * @param talkId
     * @return
     */
    public List<CommentModel> getSelectMultiTalkComment1Info(Long talkId, Long page) {
        return commentModelMapperImpl.selectMultiTalkComment1InfoByTalkId(talkId, page);
    }

    /**
     * 获取议论二级评论
     *
     * @param talkComment1Id
     * @return
     */
    public List<CommentModel> getSelectMultiTalkComment2Info(Long talkComment1Id, Long page) {
        return commentModelMapperImpl.selectMultiTalkComment2InfoByTalkComment1Id(talkComment1Id, page);
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
    public Long postPublicTalkInfo(Long topicId, Long userId, String talkTitle, String talkDesc) {

        CommentModel talkModel = new CommentModel();
        talkModel.setUserId(userId);
        talkModel.setTalkTitle(talkTitle);
        talkModel.setTopicId(topicId);
        talkModel.setTalkDesc(talkDesc);
        return commentModelMapperImpl.insertTalkInfoByModel(talkModel);
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
        CommentModel talkInfo = commentModelMapperImpl.selectSingleByTalkId(talkId);
        if (Objects.nonNull(talkInfo)) {
            talkInfo.setUserId(userId);
            talkInfo.setContent(talkCommentContent);
            final Long talkComment1Id = commentModelMapperImpl.insertTalkComment1InfoByModel(talkInfo);
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
        CommentModel talkComment1Info = commentModelMapperImpl.selectSingleByTalkComment1Id(talkComment1Id);
        if (Objects.nonNull(talkComment1Info)) {
            talkComment1Info.setUserId(userId);
            talkComment1Info.setContent(talkComment2Content);
            final Long talkComment2Id = commentModelMapperImpl.insertTalkComment2InfoByModel(talkComment1Info);
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
        CommentStarAndTreadModel model = comment2StarAndTreadModelMapperImpl.selectSingleByTalkId(talkId);
        final Long starAmount = model.getStarAmount();
        model.setStarAmount(starAmount + 1);
        return comment2StarAndTreadModelMapperImpl.updateByModel(model);
    }

    /**
     * talkInfo点踩
     *
     * @param talkId
     * @return
     */
    public int postTouchTreadTalkInfoRpc(Long talkId) {
        CommentStarAndTreadModel model = comment2StarAndTreadModelMapperImpl.selectSingleByTalkId(talkId);
        final Long treadAmount = model.getTreadAmount();
        model.setTreadAmount(treadAmount + 1);
        return comment2StarAndTreadModelMapperImpl.updateByModel(model);
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
        Comment2EntryModel talk2EntryModel = new Comment2EntryModel();
        talk2EntryModel.setEntryId(entryId);
        talk2EntryModel.setTalkId(talkId);
        talk2EntryModel.setUserId(userId);
        return comment2EntryModelMapperImpl.insertByModel(talk2EntryModel);
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
        Comment2EntryModel model = new Comment2EntryModel();
        model.setEntryId(entryId);
        model.setTalkComment1Id(talkComment1Id);
        model.setUserId(userId);
        return comment2EntryModelMapperImpl.insertByModel(model);
    }


    /**
     * 获取talkInfo的词条
     *
     * @param talkId
     * @return
     */
    public List<Long> getSelectTalkInfoForEntryInfo(Long talkId) {
        return comment2EntryShowModelMapperImpl.selectMultiEntryIdsByTalkId(talkId);
    }

    /**
     * 获取talkInfo的一级评论的词条
     *
     * @param talkComment1Id
     * @return
     */
    public List<Long> getSelectTalkComment1InfoForEntryInfo(Long talkComment1Id) {
        return comment2EntryShowModelMapperImpl.selectMultiEntryIdsByTalkComment1Id(talkComment1Id);
    }

    /**
     * 判断talk是否存在
     *
     * @param talkId
     * @return
     */
    public int postIsExistForTalkInfo(Long talkId) {
        return commentModelMapperImpl.selectDetectLegalityForTalkId(talkId);
    }

    /**
     * 判断talkComment1是否存在
     *
     * @param talkComment1Id
     * @return
     */
    public int postIsExistForTalkComment1Info(Long talkComment1Id) {
        return commentModelMapperImpl.selectDetectLegalityForTalkComment1Id(talkComment1Id);
    }

    /**
     * 随机获取一篇议论
     *
     * @param topicId
     * @param randomNumber
     * @return
     */
    public List<CommentModel> getSelectRandomTalkInfo(Long topicId, Long randomNumber) {
        return commentModelMapperImpl.selectMultiRandomComment1InfoByTopicId(topicId, randomNumber);
    }

    /**
     * 随机获取一篇议论的评论
     *
     * @param talkId
     * @param randomNumber
     * @return
     */
    public List<CommentModel> getSelectRandomTalkComment1Info(Long talkId, Long randomNumber) {
        return commentModelMapperImpl.selectMultiRandomTalkComment1InfoByTalkId(talkId, randomNumber);
    }

    /**
     * 获取议论
     *
     * @param talkId
     * @return
     */
    public CommentModel postSelectSingleTalkInfoRpc(Long talkId) {
        return commentModelMapperImpl.selectSingleByTalkId(talkId);
    }

    /**
     * 获取一级议论评论
     *
     * @param talkComment1Id
     * @return
     */
    public CommentModel postSelectSingleTalkComment1Info(Long talkComment1Id) {
        return commentModelMapperImpl.selectSingleByTalkComment1Id(talkComment1Id);
    }

    /**
     * 更新talk点赞数量
     *
     * @param talkId
     * @return
     */
    public int postUpdateTalkInfoTouchStarAmount(Long talkId) {
        return comment2StarAndTreadModelMapperImpl.updateTalkInfoTouchStarAmountByTalkId(talkId);
    }

    /**
     * 更新talk点踩数量
     *
     * @param talkId
     * @return
     */
    public int postUpdateTalkInfoTouchTreadAmount(Long talkId) {
        return comment2StarAndTreadModelMapperImpl.updateTalkInfoTouchTreadAmountByTalkId(talkId);

    }

    /**
     * 更新talk评论点赞数量
     *
     * @param talkComment1Id
     * @return
     */
    public int postUpdateTalkComment1InfoTouchStarAmount(Long talkComment1Id) {
        return comment2StarAndTreadModelMapperImpl.updateTalkComment1InfoTouchStarAmountByTalkComment1Id(talkComment1Id);

    }

    /**
     * 更新talk评论点踩数量
     *
     * @param talkComment1Id
     * @return
     */
    public int postUpdateTalkComment1InfoTouchTreadAmount(Long talkComment1Id) {
        return comment2StarAndTreadModelMapperImpl.updateTalkComment1InfoTouchTreadAmountByTalkComment1Id(talkComment1Id);

    }

    /**
     * 查询用户所有评论
     *
     * @param userId
     * @param page
     * @return
     */
    public List<CommentModel> getSelectMultiUserPublicedCommentInfo(Long userId, Long page) {
        return commentModelMapperImpl.selectMultiCommentInfoByUserId(userId, page);
    }

    /**
     * 获取用户发布所有评论数量
     *
     * @return
     */
    public Long getSelectMultiUserPublicedCommentInfoPage(Long userId) {
        return commentModelMapperImpl.selectMultiPageAllCommentInfoAmountByUserId(userId);
    }

    /**
     * 获取一级评论点赞量
     *
     * @param comment1Id
     * @return
     */
    public Long getSelectComment1InfoStarAmount(Long comment1Id) {
        CommentStarAndTreadModel model = comment2StarAndTreadModelMapperImpl.selectSingleByComment1Id(comment1Id);
        return model.getStarAmount();
    }

    /**
     * 获取一级议论评论点赞量
     *
     * @param talkComment1Id
     * @return
     */
    public Long getSelectTalkComment1InfoStarAmount(Long talkComment1Id) {
        CommentStarAndTreadModel model = comment2StarAndTreadModelMapperImpl.selectSingleByTalkComment1Id(talkComment1Id);
        return model.getStarAmount();
    }

    /**
     * 获取著述最新的几条评论,不分类型
     *
     * @param topicId
     * @param number
     * @return
     */
    public List<CommentModel> postSelectMultiAllCommentInfo(Long topicId, Integer number) {
        return commentModelMapperImpl.selectMultiLatestAllCommentInfo(topicId, number);
    }

    /**
     * 获取一级评判下的二级评论数量
     *
     * @param comment1Id
     * @return
     */
    public Long postSelectComment2InfoAmountByComment1Id(Long comment1Id) {
        return commentModelMapperImpl.selectMultiPageComment2InfoAmountByComment1Id(comment1Id);
    }

    /**
     * 获取著述下所有普通评论的总数量
     *
     * @param topicId
     * @return
     */
    public Long postSelectCommentInfoAmountByTopicId(Long topicId) {
        return commentModelMapperImpl.selectMultiPageCommentInfoAmountByTopicId(topicId);
    }

    /**
     * 获取著述下所有普通1级评论的总数量
     *
     * @param topicId
     * @return
     */
    public Long postSelectComment1InfoAmountByTopicId(Long topicId) {
        return commentModelMapperImpl.selectMultiPageComment1InfoAmountByTopicId(topicId);
    }

    /**
     * 获取talk下所有评论数量
     *
     * @param topicId
     * @param talkId
     * @return
     */
    public Long getSelectSingleTalkForTalkCommentAmount(Long topicId, Long talkId) {
        return commentModelMapperImpl.selectMultiPageAllTalkCommentInfoAmountByTalkId(talkId);
    }

    /**
     * 获取talk点赞数量
     *
     * @param talkId
     * @return
     */
    public Long getSelectSingleTalkForTalkStarAmount(Long talkId) {
        return commentModelMapperImpl.selectTalkStarAmountByTalkId(talkId);
    }

    /**
     * 获取著述下所有talk评论数量
     *
     * @param topicId
     * @return
     */
    public Long getSelectTalkCommentNumberByTopicId(Long topicId) {
        return commentModelMapperImpl.selectMultiPageTalkCommentInfoAmountByTopicId(topicId);
    }

    /**
     * 获取topic下所有talk评论
     *
     * @param topicId
     * @return
     */
    public Long getSelectAllTalkCommentAmountByTopicId(Long topicId) {
        return commentModelMapperImpl.selectMultiPageTalkCommentInfoAmountByTopicId(topicId);
    }

    /**
     * 获取talk下一级评论的数量
     *
     * @param talkId
     * @return
     */
    public Long getSelectTalkComment1InfoAmountByTalkId(Long talkId) {
        return commentModelMapperImpl.selectMultiPageTalkComment1InfoAmountByTalkId(talkId);
    }

    /**
     * 获取talk一级评论下二级评论的数量
     *
     * @param talkComment1Id
     * @return
     */
    public Long getSelectTalkComment2InfoAmountByTalkComment1Id(Long talkComment1Id) {
        return commentModelMapperImpl.selectMultiPageTalkComment2InfoAmountByTalkComment1Id(talkComment1Id);
    }

    /**
     * 获取著述所有评论数量
     *
     * @param topicId
     * @return
     */
    public Long getSelectAllCommentAmount(Long topicId) {
        return commentModelMapperImpl.selectMultiPageAllCommentInfoAmountByTopicId(topicId);
    }

}












