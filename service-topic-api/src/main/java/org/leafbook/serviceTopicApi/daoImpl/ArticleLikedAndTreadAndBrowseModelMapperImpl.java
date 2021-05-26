package org.leafbook.serviceTopicApi.daoImpl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleLikedAndTreadAndBrowseModel;
import org.leafbook.serviceTopicApi.dao.ArticleLikedAndTreadAndBrowseModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.LongStream;

@Service
public class ArticleLikedAndTreadAndBrowseModelMapperImpl {
    @Autowired
    private ArticleLikedAndTreadAndBrowseModelMapper articleLikedAndTreadAndBrowseModelMapper;
    /**
     * 单查询
     * @param articleId
     * @return
     */
    public ArticleLikedAndTreadAndBrowseModel selectSingleByArticleId(Long articleId) {
        return articleLikedAndTreadAndBrowseModelMapper.selectByArticleId(articleId);
    }

    /**
     *
     * @param model
     * @return
     */
    public int updateByModel(ArticleLikedAndTreadAndBrowseModel model) {
        UpdateWrapper<ArticleLikedAndTreadAndBrowseModel> wrapper =new UpdateWrapper<>();
        wrapper.eq("article_id",model.getArticleId());
        return articleLikedAndTreadAndBrowseModelMapper.update(model,wrapper);
    }
    /**
     * 更新文章点赞数量
     *
     * @param articleId
     * @return
     */
    public int updateArticleInfoStarAmountByArticleId(Long articleId) {
        return articleLikedAndTreadAndBrowseModelMapper.updateArticleStarAmountByArticleId(articleId);
    }
    /**
     * 更新文章点踩数量
     *
     * @param articleId
     * @return
     */
    public int updateArticleInfoTreadAmountByArticleId(Long articleId) {
        return articleLikedAndTreadAndBrowseModelMapper.updateArticleTreadAmountByArticleId(articleId);
    }

}
