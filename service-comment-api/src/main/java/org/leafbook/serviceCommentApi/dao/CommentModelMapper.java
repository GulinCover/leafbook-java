package org.leafbook.serviceCommentApi.dao;

import org.leafbook.api.modelApi.commentInfo.CommentModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class CommentModelMapper {
    //===========================

    //单查

    //===========================
    public CommentModel selectSingleByTalkId(Long talkId) {
        CommentModel commentModel = new CommentModel();
        commentModel.setTopicId(1L);
        commentModel.setTalkId(1L);
        commentModel.setTalkTitle(TestModel.randomWord());
        commentModel.setTalkDesc(TestModel.randomString().toString());
        commentModel.setUserId(312L);
        return commentModel;
    }
    public CommentModel selectSingleByComment1Id(Long comment1Id) {
        CommentModel commentModel = new CommentModel();
        commentModel.setTopicId(1L);
        commentModel.setComment1Id(1L);
        commentModel.setContent(TestModel.randomString().toString());
        commentModel.setUserId(312L);
        return commentModel;
    }
    public CommentModel selectSingleByComment2Id(Long comment2Id) {
        CommentModel commentModel = new CommentModel();
        commentModel.setTopicId(1L);
        commentModel.setComment1Id(1L);
        commentModel.setComment2Id(1L);
        commentModel.setContent(TestModel.randomString().toString());
        commentModel.setUserId(312L);
        return commentModel;
    }
    public CommentModel selectSingleByTalkComment1Id(Long talkComment1Id) {
        CommentModel commentModel = new CommentModel();
        commentModel.setTopicId(1L);
        commentModel.setTalkId(1L);
        commentModel.setTalkTitle(TestModel.randomWord());
        commentModel.setTalkDesc(TestModel.randomWord());
        commentModel.setTalkComment1Id(1L);
        commentModel.setContent(TestModel.randomString().toString());
        commentModel.setUserId(312L);
        return commentModel;
    }
    public CommentModel selectSingleByTalkComment2Id(Long talkComment2Id) {
        CommentModel commentModel = new CommentModel();
        commentModel.setTopicId(1L);
        commentModel.setTalkId(1L);
        commentModel.setTalkTitle(TestModel.randomWord());
        commentModel.setTalkDesc(TestModel.randomWord());
        commentModel.setTalkComment1Id(1L);
        commentModel.setTalkComment2Id(1L);
        commentModel.setContent(TestModel.randomString().toString());
        commentModel.setUserId(312L);
        return commentModel;
    }

    //===========================

    //多查

    //===========================
    public List<CommentModel> selectMultiTalkInfoByUserId(Long userId) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByTalkId(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiComment1InfoByUserId(Long userId) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByComment1Id(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiComment2InfoByUserId(Long userId) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByComment2Id(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiTalkComment1InfoByUserId(Long userId) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByTalkComment1Id(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiTalkComment2InfoByUserId(Long userId) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByTalkComment2Id(1L));
        }

        return commentModelList;
    }

    //=========================

    public List<CommentModel> selectMultiTalkInfoByTopicId(Long TopicId) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByTalkId(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiComment1InfoByTopicId(Long TopicId,Long page) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByComment1Id(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiComment2InfoByTopicId(Long TopicId) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByComment2Id(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiTalkComment1InfoByTopicId(Long TopicId) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByTalkComment1Id(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiTalkComment2InfoByTopicId(Long TopicId) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByTalkComment2Id(1L));
        }

        return commentModelList;
    }

    //=====================

    public List<CommentModel> selectMultiComment2InfoByComment1Id(Long comment1Id,Long page) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByComment2Id(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiTalkComment2InfoByTalkComment1Id(Long talkComment1Id) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByTalkComment2Id(1L));
        }

        return commentModelList;
    }

    //==========================

    public List<CommentModel> selectMultiRandomTalkInfoByTopicId(Long TopicId,Integer random) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByTalkId(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiRandomComment1InfoByTopicId(Long TopicId,Integer random) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByComment1Id(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiRandomComment2InfoByTopicId(Long TopicId,Integer random) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByComment2Id(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiRandomTalkComment1InfoByTopicId(Long TopicId,Integer random) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByTalkComment1Id(1L));
        }

        return commentModelList;
    }
    public List<CommentModel> selectMultiRandomTalkComment2InfoByTopicId(Long TopicId,Integer random) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByTalkComment2Id(1L));
        }

        return commentModelList;
    }

    public List<CommentModel> selectMultiRandomTalkComment1InfoByTalkId(Long talkId,Integer random) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByTalkComment1Id(1L));
        }

        return commentModelList;
    }

    //==========================

    public List<CommentModel> selectMultiTalkComment1InfoByTalkId(Long talkId) {
        List<CommentModel> commentModelList = new LinkedList<>();
        commentModelList.addAll(selectMultiTalkComment1InfoByUserId(1L));
        return commentModelList;
    }

    public List<CommentModel> selectMultiTalkComment2InfoByTalkId(Long talkId) {
        List<CommentModel> commentModelList = new LinkedList<>();
        commentModelList.addAll(selectMultiTalkComment2InfoByUserId(1L));
        return commentModelList;
    }

    public List<CommentModel> selectAllTalkCommentInfoByTalkId(Long talkId) {
        List<CommentModel> commentModelList = new LinkedList<>();
        commentModelList.addAll(selectMultiTalkComment1InfoByUserId(1L));
        commentModelList.addAll(selectMultiTalkComment2InfoByUserId(1L));
        return commentModelList;
    }

    //===========================
    /**
     * 获取著述最新的几条评论,不分类型
     * @param topicId
     * @param number
     * @return
     */
    public List<CommentModel> selectMultiLastAllCommentInfo(Long topicId,Integer number) {
        List<CommentModel> commentModelList = new LinkedList<>();
        for (int i = 0; i < new Random().nextInt(10) + 2; i++) {
            commentModelList.add(selectSingleByTalkComment2Id(1L));
        }

        return commentModelList;
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
        List<CommentModel> commentModelList = new LinkedList<>();
        commentModelList.addAll(selectMultiComment1InfoByUserId(1L));
        commentModelList.addAll(selectMultiComment2InfoByUserId(1L));
        commentModelList.addAll(selectMultiTalkComment1InfoByUserId(1L));
        commentModelList.addAll(selectMultiTalkComment2InfoByUserId(1L));
        return commentModelList;
    }

    //===========================

    //总查

    //===========================
    public List<CommentModel> selectMultiByUserId(Long userId) {
        List<CommentModel> commentModelList = new LinkedList<>();

        commentModelList.addAll(selectMultiTalkInfoByUserId(1L));
        commentModelList.addAll(selectMultiComment1InfoByUserId(1L));
        commentModelList.addAll(selectMultiComment2InfoByUserId(1L));
        commentModelList.addAll(selectMultiTalkComment1InfoByUserId(1L));
        commentModelList.addAll(selectMultiTalkComment2InfoByUserId(1L));
        return commentModelList;
    }

    public List<CommentModel> selectMultiByTopicId(Long topicId) {
        List<CommentModel> commentModelList = new LinkedList<>();

        commentModelList.addAll(selectMultiTalkInfoByUserId(1L));
        commentModelList.addAll(selectMultiComment1InfoByUserId(1L));
        commentModelList.addAll(selectMultiComment2InfoByUserId(1L));
        commentModelList.addAll(selectMultiTalkComment1InfoByUserId(1L));
        commentModelList.addAll(selectMultiTalkComment2InfoByUserId(1L));
        return commentModelList;
    }
    public List<CommentModel> selectMultiByTalkId(Long talkId) {
        List<CommentModel> commentModelList = new LinkedList<>();

        commentModelList.addAll(selectMultiTalkInfoByUserId(1L));
        commentModelList.addAll(selectMultiComment1InfoByUserId(1L));
        commentModelList.addAll(selectMultiComment2InfoByUserId(1L));
        commentModelList.addAll(selectMultiTalkComment1InfoByUserId(1L));
        commentModelList.addAll(selectMultiTalkComment2InfoByUserId(1L));
        return commentModelList;
    }

    //===========================

    //数量条数查询

    //===========================

    public Long selectMultiTalkInfoPageAmountByUserId(Long userId) {
        return (long)new Random().nextInt(500);
    }
    public Long selectMultiPageComment1InfoAmountByUserId(Long userId) {
        return (long)new Random().nextInt(500);
    }
    public Long selectMultiPageComment2InfoAmountByUserId(Long userId) {
        return (long)new Random().nextInt(500);
    }
    public Long selectMultiPageTalkComment1InfoAmountByUserId(Long userId) {
        return (long)new Random().nextInt(500);
    }
    public Long selectMultiPageTalkComment2InfoAmountByUserId(Long userId) {
        return (long)new Random().nextInt(500);
    }

    public Long selectMultiPageAllCommentInfoAmountByUserId(Long userId) {
        return (long)new Random().nextInt(500);
    }
    public Long selectMultiPageTalkCommentInfoAmountByUserId(Long userId) {
        return (long)new Random().nextInt(500);
    }
    public Long selectMultiPageCommentInfoAmountByUserId(Long userId) {
        return (long)new Random().nextInt(500);
    }
    public Long selectMultiPageAllCommentInfoAmountByTopicId(Long topicId) {
        return (long)new Random().nextInt(500);
    }
    public Long selectMultiPageCommentInfoAmountByTopicId(Long topicId) {
        return (long)new Random().nextInt(500);
    }
    public Long selectMultiPageTalkCommentInfoAmountByTopicId(Long topicId) {
        return (long)new Random().nextInt(500);
    }
    public Long selectMultiPageAllTalkCommentInfoAmountByTalkId(Long talkId) {
        return (long)new Random().nextInt(500);
    }

    //===========================

    public Long selectMultiPageComment1InfoAmountByTopicId(Long topic) {
        return (long)new Random().nextInt(500);
    }
    public Long selectMultiPageComment2InfoAmountByComment1Id(Long comment1Id) {
        return (long)new Random().nextInt(500);
    }

    //==========================
    /**
     * 判断一级评论是否点过赞
     * @param userId
     * @param comment1Id
     * @return
     */
    public int selectIsStarForComment1Info(Long userId,Long comment1Id) {
        return new Random().nextInt(2);
    }
    /**
     * 判断一级评论是否点过踩
     * @param userId
     * @param comment1Id
     * @return
     */
    public int selectIsTreadForComment1Info(Long userId,Long comment1Id) {
        return new Random().nextInt(2);
    }

    //===========================

    //检查合法性

    //===========================
    public int selectDetectLegalityForTalkId(Long talkId) {
        return 1;
    }
    public int selectDetectLegalityForComment1Id(Long comment1Id) {
        return 1;
    }
    public int selectDetectLegalityForComment12d(Long comment2Id) {
        return 1;
    }
    public int selectDetectLegalityForTalkComment1Id(Long talkComment1Id) {
        return 1;
    }
    public int selectDetectLegalityForTalkComment2Id(Long talkComment2Id) {
        return 1;
    }

    //===========================

    //插入

    //===========================
    public Long insertTalkInfoByModel(CommentModel commentModel) {
        return 1L;
    }
    public Long insertComment2InfoByModel(CommentModel commentModel) {
        return 1L;
    }
    public Long insertComment1InfoByModel(CommentModel commentModel) {
        return 1L;
    }
    public Long insertTalkComment1InfoByModel(CommentModel commentModel) {
        return 1L;
    }
    public Long insertTalkComment2InfoByModel(CommentModel commentModel) {
        return 1L;
    }
}




















