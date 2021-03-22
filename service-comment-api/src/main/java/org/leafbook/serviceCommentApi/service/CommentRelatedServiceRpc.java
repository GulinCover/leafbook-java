package org.leafbook.serviceCommentApi.service;

import org.leafbook.api.modelApi.commentInfo.Comment1Info2EntryModel;
import org.leafbook.api.modelApi.commentInfo.Comment1Model;
import org.leafbook.api.modelApi.commentInfo.Comment2Model;
import org.leafbook.api.modelApi.commentInfo.CommentStarAndTreadModel;
import org.leafbook.serviceCommentApi.dao.comment.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CommentRelatedServiceRpc {
    @Autowired
    private Comment1ModelMapper comment1ModelMapper;
    @Autowired
    private Comment2ModelMapper comment2ModelMapper;
    @Autowired
    private Comment2StarAndTreadModelMapper comment2StarAndTreadModelMapper;
    @Autowired
    private Comment1Info2EntryModelMapper comment1Info2EntryModelMapper;
    @Autowired
    private Comment1InfoEntryShowModelMapper comment1InfoEntryShowModelMapper;
    /**
     * 获取所有一级评论
     * @param topicId
     * @return
     */
    public List<Comment1Model> getSelectMultiComment1Info(Long topicId) {
        return comment1ModelMapper.selectMultiByTopicId(topicId);
    }
    /**
     * 发布一级评论
     * @param userId
     * @param topicId
     * @param comment1Content
     * @return
     */
    public int postPublicComment1Info(Long userId,Long topicId,String comment1Content) {
        Comment1Model comment1Model = new Comment1Model();
        comment1Model.setUserId(userId);
        comment1Model.setTopicId(topicId);
        comment1Model.setCommentContent(comment1Content);
        return comment1ModelMapper.insertByModel(comment1Model);
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
    public List<Comment2Model> getSelectMultiComment2Info(Long comment1Id) {
        return comment2ModelMapper.selectMultiByComment1Id(comment1Id);
    }

    /**
     * 发布二级评论
     * @param userId
     * @param comment1Id
     * @param comment2Content
     * @return
     */
    public int postPublicComment2Info(Long userId,Long comment1Id,String comment2Content) {
        Comment2Model comment2Model = new Comment2Model();
        comment2Model.setUserId(userId);
        comment2Model.setTopicId(comment1Id);
        comment2Model.setCommentContent(comment2Content);
        return comment1ModelMapper.insertByModel(comment2Model);
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
    public List<Comment1Model> getSelectRandomComment1Info(Long topicId,Integer randomNumber) {
        return comment1ModelMapper.selectRandomComment1InfoByTopicId(topicId,randomNumber);
    }
    /**
     * 获取评论词条
     * @param comment1Id
     * @return
     */
    public List<Long> getSelectMultiEntryIdsByComment1Id(Long comment1Id) {
        return comment1InfoEntryShowModelMapper.selectEntryIdsByComment1Id(comment1Id);
    }

}
