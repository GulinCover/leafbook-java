package org.leafbook.serviceTopicApi.dao;

import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.IntStream;

@Service
public class ArticleModelMapper {
    /**
     * 权限检测
     *
     * @param topicId
     * @param article
     * @return
     */
    public int selectDecideByUserIdAndArticleId(Long topicId, Long article) {
        return new Random().nextInt(100);
    }

    public int selectByMainNumber(Long topicId, Long mainNumber) {
        return new Random().nextInt(100);
    }

    public Long selectSingleMaxMainNumberByTopicId(Long topicId) {
        return (long) new Random().nextInt(10000);

    }

    public Long selectMaxBranchNumberByMainNumber(Long mainNumber) {
        return (long) new Random().nextInt(15);
    }

    public int[] selectSingleForBranchNumberByMainNumber(Long mainNumber) {
        return IntStream.range(0, 10).toArray();
    }


    public ArticleModel selectByArticleId(Long articleId) {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setUserId(545L);
        articleModel.setTopicId(46464L);
        articleModel.setArticleContent(TestModel.randomString().toString());
        articleModel.setArticleTitle(TestModel.randomWord());
        articleModel.setArticleDesc(TestModel.randomWord());
        articleModel.setMainNumber(1L);
        articleModel.setBranchNumber(1L);
        return articleModel;
    }

    public Long insertByModelForArticleId(ArticleModel articleModel) {
        return (long) new Random().nextInt(10000);
    }

    public int insert(ArticleModel articleModel) {
        return new Random().nextInt(100);
    }

    public int updateForNextArticleId(Long articleId,Long nextArticleId) {
        return new Random().nextInt(100);
    }

    public int updateByModel(ArticleModel articleModel) {
        return new Random().nextInt(100);
    }


}
