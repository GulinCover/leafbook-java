package org.leafbook.serviceTopicApi.dao;

import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleLikedAndTreadAndBrowseModel;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ArticleLikedAndTreadAndBrowseModelMapper {

    public ArticleLikedAndTreadAndBrowseModel selectSingleByArticleId(Long articleId) {
        ArticleLikedAndTreadAndBrowseModel model = new ArticleLikedAndTreadAndBrowseModel();
        model.setLikedAmount((long)new Random().nextInt(1200));
        model.setTreadAmount((long)new Random().nextInt(1200));
        model.setBrowseAmount((long)new Random().nextInt(1200));
        return model;
    }

    public int updateByModel(ArticleLikedAndTreadAndBrowseModel model) {
        return new Random().nextInt(100);
    }
    /**
     * 更新文章点赞数量
     *
     * @param articleId
     * @return
     */
    public int updateArticleInfoStarAmountByArticleId(Long articleId) {
        return 1;
    }
    /**
     * 更新文章点踩数量
     *
     * @param articleId
     * @return
     */
    public int updateArticleInfoTreadAmountByArticleId(Long articleId) {
        return 1;
    }
}
