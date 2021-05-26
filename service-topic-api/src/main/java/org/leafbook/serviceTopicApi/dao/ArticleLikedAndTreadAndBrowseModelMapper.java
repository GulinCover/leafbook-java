package org.leafbook.serviceTopicApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleLikedAndTreadAndBrowseModel;

@Mapper
public interface ArticleLikedAndTreadAndBrowseModelMapper extends BaseMapper<ArticleLikedAndTreadAndBrowseModel> {
    ArticleLikedAndTreadAndBrowseModel selectByArticleId(@Param("articleId")Long articleId);

    int updateArticleStarAmountByArticleId(@Param("articleId")Long articleId);
    int updateArticleTreadAmountByArticleId(@Param("articleId")Long articleId);
    int updateArticleBrowseAmountByArticleId(@Param("articleId")Long articleId);
}
