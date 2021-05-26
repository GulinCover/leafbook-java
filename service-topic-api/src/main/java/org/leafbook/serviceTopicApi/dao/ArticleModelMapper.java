package org.leafbook.serviceTopicApi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;

import java.util.List;

@Mapper
public interface ArticleModelMapper extends BaseMapper<ArticleModel> {
    Long selectMainArticleAmount(@Param("topicId") Long topicId);

    List<ArticleModel> selectMultiMainArticleByTopicId(
            @Param("topicId") Long topicId,
            @Param("start") Long start,
            @Param("end") Long end
    );

    List<ArticleModel> selectMultiBranchByMainNumber(
            @Param("topicId") Long topicId,
            @Param("mainNumber") Long mainNumber
    );

    Integer selectDetectIsExistArticleByNumber(
            @Param("topicId")Long topicId,
            @Param("mainNumber")Long mainNumber,
            @Param("branchNumber")Long branchNumber
    );

    Long selectAllArticleAmountByTopicId(@Param("topicId")Long topicId);

    ArticleModel selectSingleArticleByNumber(
            @Param("topicId")Long topicId,
            @Param("mainNumber")Long mainNumber,
            @Param("branchNumber")Long branchNumber
    );

    Integer selectDetectAuthorityForTopicIdWithArticleId(
            @Param("topicId")Long topicId,
            @Param("articleId")Long articleId
    );

    List<ArticleModel> selectMultiRandomArticleByTopicId(
            @Param("topicId")Long topicId,
            @Param("randomNumber")Integer randomNumber
    );

    ArticleModel selectSingleLatestArticleByTopicId(@Param("topicId")Long topicId);

    Integer selectDetectIsExistMainNumber(@Param("topicId")Long topicId,@Param("mainNumber")Long mainNumber);

    Long selectSingleMaxMainNumberByTopicId(@Param("topicId")Long topicId);

    Long selectSingleMaxBranchNumberByMainNumber(
            @Param("topicId")Long topicId,
            @Param("mainNumber")Long mainNumber
    );

    ArticleModel selectSingleArticleById(@Param("articleId")Long articleId);

    int updateArticleNextId(
            @Param("articleId")Long articleId,
            @Param("nextArticleId")Long nextArticleId,
            @Param("updateTime")Long updateTime
    );

    int updateArticleContent(
            @Param("userId")Long userId,
            @Param("articleId")Long articleId,
            @Param("content")String content,
            @Param("updateTime")Long updateTime
    );
}












