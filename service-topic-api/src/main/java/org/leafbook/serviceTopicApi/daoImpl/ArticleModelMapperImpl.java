package org.leafbook.serviceTopicApi.daoImpl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.leafbook.api.annotation.NotUsedYet;
import org.leafbook.api.annotation.RemainingProblem;
import org.leafbook.api.annotation.ToBeOptimized;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.api.testModel.indexPage.TestModel;
import org.leafbook.serviceTopicApi.dao.ArticleModelMapper;
import org.leafbook.utils.tools.IdGeneratorTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class ArticleModelMapperImpl {
    @Autowired
    private ArticleModelMapper articleModelMapper;
    /**
     * 获取著述下所有主线文章数量
     * @param topicId
     * @return
     */
    public Long selectMainArticleInfoAmountForTopic(Long topicId) {
        return articleModelMapper.selectMainArticleAmount(topicId);
    }

    /**
     * 获取著述下所有主线文章
     * @param topicId
     * @param page
     * @return
     */
    public List<ArticleModel> selectMultiMainArticleInfo(Long topicId,Long page) {
        Long end = 20;
        Long start = page * 20;
        return articleModelMapper.selectMultiMainArticleByTopicId(topicId,start,end);
    }
    /**
     * 获取当前主线下所有分支
     * @param topicId
     * @param mainNumber
     * @return
     */
    @RemainingProblem
    public List<ArticleModel> selectBranchArticleByMainNumber(Long topicId,Long mainNumber) {
        return articleModelMapper.selectMultiBranchByMainNumber(topicId,mainNumber);
    }
    /**
     * 检测文章是否存在
     * @param topicId
     * @param mainNumber
     * @param branchNumber
     * @return
     */
    public int selectDetectIsExistTopicArticleInfo(Long topicId,Long mainNumber,Long branchNumber) {
        return articleModelMapper.selectDetectIsExistArticleByNumber(topicId,mainNumber,branchNumber);
    }
    /**
     * 获取著述所有文章数量
     * @param topicId
     * @return
     */
    public Long selectArticleAmountByTopicId(Long topicId) {
        return articleModelMapper.selectAllArticleAmountByTopicId(topicId);
    }
    /**
     * 根据mainNumber,branchNumber,topicId获取article
     * @param topicId
     * @param mainNumber
     * @param branchNumber
     * @return
     */
    public ArticleModel selectByTopicIdAndNumber(
            Long topicId,
            Long mainNumber,
            Long branchNumber) {
        return articleModelMapper.selectSingleArticleByNumber(topicId,mainNumber,branchNumber);
    }

    /**
     * 权限检测
     *
     * @param topicId
     * @param articleId
     * @return
     */
    public int selectDecideByUserIdAndArticleId(Long topicId, Long articleId) {
        return articleModelMapper.selectDetectAuthorityForTopicIdWithArticleId(topicId,articleId);
    }

    /**
     * 随机获取一篇文章
     *
     * @param topicId
     * @return
     */
    @ToBeOptimized
    public List<ArticleModel> selectRandomSingleArticleInfoByTopicId(Long topicId, Integer randomNumber) {
        return articleModelMapper.selectMultiRandomArticleByTopicId(topicId,randomNumber);
    }

    /**
     * 获取最近一条文章
     *
     * @param topicId
     * @return
     */
    public ArticleModel selectLastArticleByTopicId(Long topicId) {
        return articleModelMapper.selectSingleLatestArticleByTopicId(topicId);
    }

    /**
     * 检测合法性
     * @param topicId
     * @param mainNumber
     * @return
     */
    public int selectByMainNumber(Long topicId, Long mainNumber) {
        return articleModelMapper.selectDetectIsExistMainNumber(topicId,mainNumber);
    }

    /**
     * 查询当前著述主线最大值
     * @param topicId
     * @return
     */
    public Long selectSingleMaxMainNumberByTopicId(Long topicId) {
        return articleModelMapper.selectSingleMaxMainNumberByTopicId(topicId);

    }
    /**
     * 查询当前主线分支最大值
     * @param mainNumber
     * @return
     */
    public Long selectMaxBranchNumberByMainNumber(Long topicId,Long mainNumber) {
        return articleModelMapper.selectSingleMaxBranchNumberByMainNumber(topicId,mainNumber);
    }

    @NotUsedYet
    public int[] selectSingleForBranchNumberByMainNumber(Long topicId,Long mainNumber) {
        return IntStream.range(0, 10).toArray();
    }

    /**
     * 获取文章
     * @param articleId
     * @return
     */
    public ArticleModel selectByArticleId(Long articleId) {
        return articleModelMapper.selectSingleArticleById(articleId);
    }
    /**
     * 插入返回articleId
     * @param articleModel
     * @return
     */
    public Long insertByModelForArticleId(ArticleModel articleModel) {
        Long articleId = IdGeneratorTools.nextId();
        articleModel.setTopicId(articleId);
        int ret = articleModelMapper.insert(articleModel);
        return ret == 0 ? 0 : articleId;
    }

    /**
     * 插入返回articleInfo
     * @param articleModel
     * @return
     */
    public ArticleModel insert(ArticleModel articleModel) {
        Long articleId = IdGeneratorTools.nextId();
        articleModel.setTopicId(articleId);
        int ret = articleModelMapper.insert(articleModel);
        if (ret == 0) return new ArticleModel();
        return articleModelMapper.selectSingleArticleById(articleId);
    }
    /**
     * 插入返回成功条数
     * @param articleModel
     * @return
     */
    public int insertByModel(ArticleModel articleModel) {
        Long articleId = IdGeneratorTools.nextId();
        articleModel.setTopicId(articleId);
        return articleModelMapper.insert(articleModel);
    }

    /**
     * 更新文章的下一条文章链接
     * @param articleId
     * @param nextArticleId
     * @return
     */
    public int updateForNextArticleId(Long articleId, Long nextArticleId) {
        return articleModelMapper.updateArticleNextId(articleId,nextArticleId,new Date().getTime());
    }

    /**
     * 更新文章
     * @param articleModel
     * @return
     */
    public int updateByModel(ArticleModel articleModel) {
        return articleModelMapper.updateById(articleModel);
    }

    /**
     * 更新文章
     * @param userId
     * @param articleId
     * @param content
     * @return
     */
    public int updateByParams(Long userId,Long articleId,String content) {
        return articleModelMapper.updateArticleContent(userId,articleId,content,new Date().getTime());
    }


}
