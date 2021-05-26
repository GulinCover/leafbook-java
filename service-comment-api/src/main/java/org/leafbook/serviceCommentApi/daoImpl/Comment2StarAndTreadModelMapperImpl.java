package org.leafbook.serviceCommentApi.daoImpl;

import org.leafbook.api.modelApi.commentInfo.CommentStarAndTreadModel;
import org.leafbook.serviceCommentApi.dao.Comment2StarAndTreadModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Service
public class Comment2StarAndTreadModelMapperImpl {
    @Autowired
    private Comment2StarAndTreadModelMapper comment2StarAndTreadModelMapper;

    /**
     * 获取comment1赞踩信息
     *
     * @param commentId
     * @return
     */
    public CommentStarAndTreadModel selectSingleByComment1Id(Long commentId) {
        return comment2StarAndTreadModelMapper.selectSingleByComment1Id(commentId);
    }

    /**
     * 获取talkCommentId赞踩信息
     *
     * @param talkComment1Id
     * @return
     */
    public CommentStarAndTreadModel selectSingleByTalkComment1Id(Long talkComment1Id) {
        return comment2StarAndTreadModelMapper.selectSingleByTalkComment1Id(talkComment1Id);
    }

    /**
     * 获取talkId赞踩信息
     *
     * @param talkId
     * @return
     */
    public CommentStarAndTreadModel selectSingleByTalkId(Long talkId) {
        return comment2StarAndTreadModelMapper.selectSingleByTalkId(talkId);
    }


    public int insertByModel(CommentStarAndTreadModel model) {
        Long time = new Date().getTime();
        model.setTreadAmount(0L);
        model.setStarAmount(0L);
        model.setComment2StarAndTreadModelId(IdGeneratorTools.nextId());
        model.setCreateTime(time);
        model.setUpdateTime(time);
        return comment2StarAndTreadModelMapper.insert(model);
    }


    /**
     * 通用更新
     *
     * @param model
     * @return
     */
    public int updateByModel(CommentStarAndTreadModel model) {
        return comment2StarAndTreadModelMapper.updateById(model);
    }

    /**
     * 更新普通评论的点赞数
     *
     * @param comment1Id
     * @return
     */
    public int updateTouchStarAmountByComment1Id(Long comment1Id) {
        return comment2StarAndTreadModelMapper.updateTouchStarAmountByComment1Id(comment1Id, new Date().getTime());
    }

    /**
     * 更新普通评论的点踩数
     *
     * @param comment1Id
     * @return
     */
    public int updateTouchTreadAmountByComment1Id(Long comment1Id) {
        return comment2StarAndTreadModelMapper.updateTouchTreadAmountByComment1Id(comment1Id, new Date().getTime());
    }

    /**
     * 更新talkComment1Info的点踩数
     *
     * @param talkComment1Id
     * @return
     */
    public int updateTalkComment1InfoTouchTreadAmountByTalkComment1Id(Long talkComment1Id) {
        return comment2StarAndTreadModelMapper.updateTouchTreadAmountByTalkComment1Id(talkComment1Id, new Date().getTime());
    }

    /**
     * 更新talkComment1Info的点赞数
     *
     * @param talkComment1Id
     * @return
     */
    public int updateTalkComment1InfoTouchStarAmountByTalkComment1Id(Long talkComment1Id) {
        return comment2StarAndTreadModelMapper.updateTouchStarAmountByTalkComment1Id(talkComment1Id, new Date().getTime());
    }

    /**
     * 更新talkInfo的点踩数
     *
     * @param talkId
     * @return
     */
    public int updateTalkInfoTouchTreadAmountByTalkId(Long talkId) {
        return comment2StarAndTreadModelMapper.updateTouchTreadAmountByTalkId(talkId, new Date().getTime());
    }

    /**
     * 更新talkInfo的点踩数
     *
     * @param talkId
     * @return
     */
    public int updateTalkInfoTouchStarAmountByTalkId(Long talkId) {
        return comment2StarAndTreadModelMapper.updateTouchStarAmountByTalkId(talkId, new Date().getTime());
    }

}
