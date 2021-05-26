package org.leafbook.serviceCommentApi.daoImpl;

import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.leafbook.serviceCommentApi.dao.CommentModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class CommentModelMapperImpl {
    @Autowired
    private CommentModelMapper commentModelMapper;
    //===========================

    //单查

    //===========================

    /**
     * 根据talkI单查询
     * @param talkId
     * @return
     */
    public CommentModel selectSingleByTalkId(Long talkId) {
        return commentModelMapper.selectSingleByTalkId(talkId);
    }
    /**
     * 根据comment1Id单查询
     * @param comment1Id
     * @return
     */
    public CommentModel selectSingleByComment1Id(Long comment1Id) {
        return commentModelMapper.selectSingleByComment1Id(comment1Id);
    }
    /**
     * 根据comment2Id单查询
     * @param comment2Id
     * @return
     */
    public CommentModel selectSingleByComment2Id(Long comment2Id) {
        return commentModelMapper.selectSingleByComment2Id(comment2Id);
    }
    /**
     * 根据talkComment1Id单查询
     * @param talkComment1Id
     * @return
     */
    public CommentModel selectSingleByTalkComment1Id(Long talkComment1Id) {
        return commentModelMapper.selectSingleByTalkComment1Id(talkComment1Id);
    }
    /**
     * 根据talkComment2Id单查询
     * @param talkComment2Id
     * @return
     */
    public CommentModel selectSingleByTalkComment2Id(Long talkComment2Id) {
        return commentModelMapper.selectSingleByTalkComment2Id(talkComment2Id);
    }

    //===========================

    //多查

    //===========================
    /**
     * 根据userId组查询talk
     * @param userId
     * @return
     */
    public List<CommentModel> selectMultiTalkInfoByUserId(Long userId) {
        return commentModelMapper.selectMultiTalkInfoByUserId(userId);
    }
    /**
     * 根据userId组查询comment1Info
     * @param userId
     * @return
     */
    public List<CommentModel> selectMultiComment1InfoByUserId(Long userId) {
        return commentModelMapper.selectMultiComment1InfoByUserId(userId);
    }
    /**
     * 根据userId组查询comment2Info
     * @param userId
     * @return
     */
    public List<CommentModel> selectMultiComment2InfoByUserId(Long userId) {
        return commentModelMapper.selectMultiComment2InfoByUserId(userId);
    }
    /**
     * 根据userId组查询talkComment1Info
     * @param userId
     * @return
     */
    public List<CommentModel> selectMultiTalkComment1InfoByUserId(Long userId) {
        return commentModelMapper.selectMultiTalkComment1InfoByUserId(userId);
    }
    /**
     * 根据userId组查询talkComment2Info
     * @param userId
     * @return
     */
    public List<CommentModel> selectMultiTalkComment2InfoByUserId(Long userId) {
        return commentModelMapper.selectMultiTalkComment2InfoByUserId(userId);
    }

    //=========================

    /**
     * 根据topicId分页组查询talkInfo
     * @param topicId
     * @param page
     * @return
     */
    public List<CommentModel> selectMultiTalkInfoByTopicId(Long topicId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return commentModelMapper.selectMultiTalkInfoByTopicId(topicId,start,end);
    }
    /**
     * 根据topicId分页组查询comment1Info
     * @param topicId
     * @param page
     * @return
     */
    public List<CommentModel> selectMultiComment1InfoByTopicId(Long topicId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return commentModelMapper.selectMultiComment1InfoByTopicId(topicId,start,end);
    }
    /**
     * 根据topicId分页组查询comment2Info
     * @param topicId
     * @param page
     * @return
     */
    public List<CommentModel> selectMultiComment2InfoByTopicId(Long topicId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return commentModelMapper.selectMultiComment2InfoByTopicId(topicId,start,end);
    }
    /**
     * 根据topicId分页组查询talkComment1Info
     * @param topicId
     * @param page
     * @return
     */
    public List<CommentModel> selectMultiTalkComment1InfoByTopicId(Long topicId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return commentModelMapper.selectMultiTalkComment1InfoByTopicId(topicId,start,end);
    }
    /**
     * 根据topicId分页组查询talkComment2Info
     * @param topicId
     * @param page
     * @return
     */
    public List<CommentModel> selectMultiTalkComment2InfoByTopicId(Long topicId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return commentModelMapper.selectMultiTalkComment2InfoByTopicId(topicId,start,end);
    }

    //=====================

    /**
     * 根据comment1Id分页组查询comment2Info
     * @param comment1Id
     * @param page
     * @return
     */
    public List<CommentModel> selectMultiComment2InfoByComment1Id(Long comment1Id,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return commentModelMapper.selectMultiComment2InfoByComment1Id(comment1Id,start,end);
    }
    /**
     * 根据talkComment1Id分页组查询talkComment2Info
     * @param talkComment1Id
     * @param page
     * @return
     */
    public List<CommentModel> selectMultiTalkComment2InfoByTalkComment1Id(Long talkComment1Id,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return commentModelMapper.selectMultiTalkComment2InfoByTalkComment1Id(talkComment1Id,start,end);
    }

    //==========================

    /**
     * 根据topicId随机获取talkInfo
     * @param TopicId
     * @param random
     * @return
     */
    public List<CommentModel> selectMultiRandomTalkInfoByTopicId(Long TopicId,Long random) {
        return commentModelMapper.selectMultiRandomTalkInfoByTopicId(TopicId,random);
    }
    /**
     * 根据topicId随机获取comment1Info
     * @param TopicId
     * @param random
     * @return
     */
    public List<CommentModel> selectMultiRandomComment1InfoByTopicId(Long TopicId,Long random) {
        return commentModelMapper.selectMultiRandomComment1InfoByTopicId(TopicId,random);
    }
    /**
     * 根据topicId随机获取comment2Info
     * @param TopicId
     * @param random
     * @return
     */
    public List<CommentModel> selectMultiRandomComment2InfoByTopicId(Long TopicId,Long random) {
        return commentModelMapper.selectMultiRandomComment2InfoByTopicId(TopicId,random);
    }
    /**
     * 根据topicId随机获取talkComment1Info
     * @param TopicId
     * @param random
     * @return
     */
    public List<CommentModel> selectMultiRandomTalkComment1InfoByTopicId(Long TopicId,Long random) {
        return commentModelMapper.selectMultiRandomTalkComment1InfoByTopicId(TopicId,random);
    }
    /**
     * 根据topicId随机获取talkComment2Info
     * @param TopicId
     * @param random
     * @return
     */
    public List<CommentModel> selectMultiRandomTalkComment2InfoByTopicId(Long TopicId,Long random) {
        return commentModelMapper.selectMultiRandomTalkComment2InfoByTopicId(TopicId,random);
    }
    /**
     * 根据talkId随机获取talkComment1Info
     * @param talkId
     * @param random
     * @return
     */
    public List<CommentModel> selectMultiRandomTalkComment1InfoByTalkId(Long talkId,Long random) {
        return commentModelMapper.selectMultiRandomTalkComment1InfoByTalkId(talkId,random);
    }

    //==========================

    /**
     * 根据talk分页查询talkComment1Info
     * @param talkId
     * @param page
     * @return
     */
    public List<CommentModel> selectMultiTalkComment1InfoByTalkId(Long talkId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return commentModelMapper.selectMultiTalkComment1InfoByTalkId(talkId,start,end);
    }
    /**
     * 根据talk分页查询talkComment2Info
     * @param talkId
     * @param page
     * @return
     */
    public List<CommentModel> selectMultiTalkComment2InfoByTalkId(Long talkId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return commentModelMapper.selectMultiTalkComment2InfoByTalkId(talkId,start,end);
    }
    /**
     * 根据talk分页查询allTalkCommentInfo
     * @param talkId
     * @param page
     * @return
     */
    public List<CommentModel> selectAllTalkCommentInfoByTalkId(Long talkId,Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return commentModelMapper.selectAllTalkCommentInfoByTalkId(talkId,start,end);
    }

    //===========================
    /**
     * 获取著述最新的几条评论,不分类型
     * @param topicId
     * @param number
     * @return
     */
    public List<CommentModel> selectMultiLatestAllCommentInfo(Long topicId,Integer number) {
        return commentModelMapper.selectMultiLatestAllCommentInfo(topicId,number);
    }

    //===========================

    /**
     * 查询用户所有评论
     *
     * @param userId
     * @param page
     * @return
     */
    public List<CommentModel> selectMultiCommentInfoByUserId(Long userId, Long page) {
        Long end = page * 20;
        Long start = end - 20;
        return commentModelMapper.selectMultiCommentInfoByUserId(userId,start,end);
    }

    //===========================

    //总查

    //===========================

    /**
     * 用户相关所有操作
     * @param userId
     * @return
     */
    public List<CommentModel> selectMultiByUserId(Long userId) {
        return commentModelMapper.selectAllInfoByUserId(userId);
    }

    /**
     * topicId下所有操作
     * @param topicId
     * @return
     */
    public List<CommentModel> selectMultiByTopicId(Long topicId) {
        return commentModelMapper.selectAllInfoByTopicId(topicId);
    }
    /**
     * talkId下所有操作
     * @param talkId
     * @return
     */
    public List<CommentModel> selectMultiByTalkId(Long talkId) {
        return commentModelMapper.selectAllInfoByTalkId(talkId);
    }

    //===========================

    //数量条数查询

    //===========================

    /**
     * 用户talkInfo数量
     * @param userId
     * @return
     */
    public Long selectMultiTalkInfoPageAmountByUserId(Long userId) {
        return commentModelMapper.selectMultiTalkInfoAmountByUserId(userId);
    }
    /**
     * 用户comment1Info数量
     * @param userId
     * @return
     */
    public Long selectMultiPageComment1InfoAmountByUserId(Long userId) {
        return commentModelMapper.selectMultiComment1InfoAmountByUserId(userId);
    }
    /**
     * 用户comment2Info数量
     * @param userId
     * @return
     */
    public Long selectMultiPageComment2InfoAmountByUserId(Long userId) {
        return commentModelMapper.selectMultiComment2InfoAmountByUserId(userId);
    }
    /**
     * 用户talkComment1Info数量
     * @param userId
     * @return
     */
    public Long selectMultiPageTalkComment1InfoAmountByUserId(Long userId) {
        return commentModelMapper.selectMultiTalkComment1InfoAmountByUserId(userId);
    }
    /**
     * 用户talkComment2Info数量
     * @param userId
     * @return
     */
    public Long selectMultiPageTalkComment2InfoAmountByUserId(Long userId) {
        return commentModelMapper.selectMultiTalkComment2InfoAmountByUserId(userId);
    }

    /**
     * 根据userId所有评论数量
     * @param userId
     * @return
     */
    public Long selectMultiPageAllCommentInfoAmountByUserId(Long userId) {
        return commentModelMapper.selectAllTypeCommentInfoAmountByUserId(userId);
    }
    /**
     * 根据userId所有talkComment数量
     * @param userId
     * @return
     */
    public Long selectMultiPageTalkCommentInfoAmountByUserId(Long userId) {
        return commentModelMapper.selectAllTalkCommentInfoAmountByUserId(userId);
    }
    /**
     * 根据userId所有commentInfo数量
     * @param userId
     * @return
     */
    public Long selectMultiPageCommentInfoAmountByUserId(Long userId) {
        return commentModelMapper.selectAllCommentInfoAmountByUserId(userId);
    }
    /**
     * 根据topicId所有评论数量
     * @param topicId
     * @return
     */
    public Long selectMultiPageAllCommentInfoAmountByTopicId(Long topicId) {
        return commentModelMapper.selectAllTypeCommentInfoAmountByTopicId(topicId);
    }
    /**
     * 根据topicId所有commentInfo数量
     * @param topicId
     * @return
     */
    public Long selectMultiPageCommentInfoAmountByTopicId(Long topicId) {
        return commentModelMapper.selectAllCommentInfoAmountByTopicId(topicId);
    }
    /**
     * 根据topicId所有talkCommentInfo数量
     * @param topicId
     * @return
     */
    public Long selectMultiPageTalkCommentInfoAmountByTopicId(Long topicId) {
        return commentModelMapper.selectAllTalkCommentInfoAmountByTopicId(topicId);
    }
    /**
     * 根据talkId所有talkCommentInfo数量
     * @param talkId
     * @return
     */
    public Long selectMultiPageAllTalkCommentInfoAmountByTalkId(Long talkId) {
        return commentModelMapper.selectAllTalkCommentInfoAmountByTalkId(talkId);
    }

    //===========================


    public Long selectTalkStarAmountByTalkId(Long talkId) {
        return (long)new Random().nextInt(500);
    }

    //===========================

    /**
     * 根据topicId获取comment1Info数量
     * @param topic
     * @return
     */
    public Long selectMultiPageComment1InfoAmountByTopicId(Long topic) {
        return commentModelMapper.selectAllComment1InfoAmountByTopicId(topic);
    }
    /**
     * 根据comment1Id获取comment2Info数量
     * @param comment1Id
     * @return
     */
    public Long selectMultiPageComment2InfoAmountByComment1Id(Long comment1Id) {
        return commentModelMapper.selectAllComment2InfoAmountByComment1Id(comment1Id);
    }
    /**
     * 根据talkId获取talkComment1Info数量
     * @param talkId
     * @return
     */
    public Long selectMultiPageTalkComment1InfoAmountByTalkId(Long talkId) {
        return commentModelMapper.selectAllTalkComment1InfoAmountByTalkId(talkId);
    }
    /**
     * 根据talkComment1Id获取talkComment2Info数量
     * @param talkComment1Id
     * @return
     */
    public Long selectMultiPageTalkComment2InfoAmountByTalkComment1Id(Long talkComment1Id) {
        return commentModelMapper.selectAllTalkComment2InfoAmountByTalkComment1Id(talkComment1Id);
    }


    //===========================

    //检查合法性

    //===========================

    /**
     * 检查talkId
     * @param talkId
     * @return
     */
    public int selectDetectLegalityForTalkId(Long talkId) {
        return commentModelMapper.selectDetectLegalityForTalkId(talkId);
    }
    /**
     * 检查talkId
     * @param comment1Id
     * @return
     */
    public int selectDetectLegalityForComment1Id(Long comment1Id) {
        return commentModelMapper.selectDetectLegalityForTalkId(comment1Id);
    }
    /**
     * 检查talkId
     * @param comment2Id
     * @return
     */
    public int selectDetectLegalityForComment2Id(Long comment2Id) {
        return commentModelMapper.selectDetectLegalityForTalkId(comment2Id);
    }
    /**
     * 检查talkId
     * @param talkComment1Id
     * @return
     */
    public int selectDetectLegalityForTalkComment1Id(Long talkComment1Id) {
        return commentModelMapper.selectDetectLegalityForTalkId(talkComment1Id);
    }
    /**
     * 检查talkId
     * @param talkComment2Id
     * @return
     */
    public int selectDetectLegalityForTalkComment2Id(Long talkComment2Id) {
        return commentModelMapper.selectDetectLegalityForTalkId(talkComment2Id);
    }

    //===========================

    //插入

    //===========================
    public Long insertTalkInfoByModel(CommentModel commentModel) {
        Long time = new Date().getTime();
        Long talkId = IdGeneratorTools.nextId();
        commentModel.setTalkId(talkId);

        commentModel.setVersion(1);
        commentModel.setCreateTime(time);
        commentModel.setUpdateTime(time);
        commentModel.setIsBlack(0);
        int ret = commentModelMapper.insert(commentModel);
        return ret == 0 ? 0 : talkId;
    }
    public Long insertComment2InfoByModel(CommentModel commentModel) {
        Long time = new Date().getTime();
        Long comment2Id = IdGeneratorTools.nextId();
        commentModel.setComment2Id(comment2Id);

        commentModel.setVersion(1);
        commentModel.setCreateTime(time);
        commentModel.setUpdateTime(time);
        commentModel.setIsBlack(0);
        int ret = commentModelMapper.insert(commentModel);
        return ret == 0 ? 0 : comment2Id;
    }
    public Long insertComment1InfoByModel(CommentModel commentModel) {
        Long time = new Date().getTime();
        Long comment1Id = IdGeneratorTools.nextId();
        commentModel.setComment1Id(comment1Id);

        commentModel.setVersion(1);
        commentModel.setCreateTime(time);
        commentModel.setUpdateTime(time);
        commentModel.setIsBlack(0);
        int ret = commentModelMapper.insert(commentModel);
        return ret == 0 ? 0 : comment1Id;
    }
    public Long insertTalkComment1InfoByModel(CommentModel commentModel) {
        Long time = new Date().getTime();
        Long talkComment1Id = IdGeneratorTools.nextId();
        commentModel.setComment2Id(talkComment1Id);

        commentModel.setVersion(1);
        commentModel.setCreateTime(time);
        commentModel.setUpdateTime(time);
        commentModel.setIsBlack(0);
        int ret = commentModelMapper.insert(commentModel);
        return ret == 0 ? 0 : talkComment1Id;
    }
    public Long insertTalkComment2InfoByModel(CommentModel commentModel) {
        Long time = new Date().getTime();
        Long talkComment2Id = IdGeneratorTools.nextId();
        commentModel.setComment2Id(talkComment2Id);

        commentModel.setVersion(1);
        commentModel.setCreateTime(time);
        commentModel.setUpdateTime(time);
        commentModel.setIsBlack(0);
        int ret = commentModelMapper.insert(commentModel);
        return ret == 0 ? 0 : talkComment2Id;
    }
}




















