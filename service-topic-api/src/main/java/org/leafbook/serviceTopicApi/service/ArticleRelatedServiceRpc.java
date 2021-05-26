package org.leafbook.serviceTopicApi.service;

import org.leafbook.api.modelApi.topicInfo.DirectoryModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.Article2EntryModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleLikedAndTreadAndBrowseModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.serviceTopicApi.daoImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class ArticleRelatedServiceRpc {
    @Autowired
    private ArticleModelMapperImpl articleModelMapperImpl;

    @Autowired
    private Article2EntryModelMapperImpl article2EntryModelMapperImpl;

    @Autowired
    private ArticleLikedAndTreadAndBrowseModelMapperImpl articleLikedAndTreadAndBrowseModelMapperImpl;

    @Autowired
    private DirectoryModelMapper directoryModelMapper;

    @Autowired
    private Article2EntryShowModelMapperImpl article2EntryShowModelMapperImpl;
    /**
     * 文章权限检测
     * @param userId
     * @param articleId
     * @return
     */
    public int postArticleAuthorityDecide(Long userId,Long articleId) {
        return articleModelMapperImpl.selectDecideByUserIdAndArticleId(userId,articleId);
    }
    /**
     * 获取文章
     * @param articleId
     * @return
     */
    public ArticleModel getSelectSingleArticleInfo(Long articleId) {
        return articleModelMapperImpl.selectByArticleId(articleId);
    }

    /**
     * 著述拥有者提交文章
     * @param articleModel
     * mainNumber必须为空
     * @return map:articleId,mainNumber
     */
    public Map<String,Long> postPublicMainArticleByOtherReturnMainNumber(ArticleModel articleModel) {
        Map<String,Long> map = new HashMap<>();
        ArticleModel article = new ArticleModel();
        article.setArticleTitle(articleModel.getArticleTitle());
        article.setArticleDesc(articleModel.getArticleDesc());
        article.setArticleContent(articleModel.getArticleContent());
        article.setTopicId(articleModel.getTopicId());
        article.setUserId(articleModel.getUserId());

        articleModel.setPreArticleId(0L);
        articleModel.setNextArticleId(0L);

        Long maxMainNumber = articleModelMapperImpl.selectSingleMaxMainNumberByTopicId(articleModel.getTopicId());
        Long articleId = articleModelMapperImpl.insertByModelForArticleId(article);

        DirectoryModel directoryModel = new DirectoryModel();
        directoryModel.setArticleId(articleId);
        directoryModel.setPageNumber(maxMainNumber+1);
        directoryModel.setTopicId(articleModel.getTopicId());
        directoryModelMapper.insertByModel(directoryModel);

        map.put("mainNumber",maxMainNumber+1);
        map.put("articleId",articleId);
        return map;

    }
    /**
     * 非著述拥有者提交文章
     * @param articleModel
     * 必须提交:mainNumber,content,entryIds,title,desc,topicId
     * @return map:articleId,branchNumber
     */
    public Map<String,Long> postPublicBranchArticleByOtherReturnBranchNumber(ArticleModel articleModel) {
        Map<String,Long> map = new HashMap<>();

        //查找著述是否存在mainNumber,查看参数合法性
        int mainNumber = articleModelMapperImpl.selectByMainNumber(articleModel.getTopicId(),articleModel.getMainNumber());
        if (mainNumber == 0) return map;

        ArticleModel article = new ArticleModel();
        article.setArticleTitle(articleModel.getArticleTitle());
        article.setArticleDesc(articleModel.getArticleDesc());
        article.setArticleContent(articleModel.getArticleContent());
        article.setTopicId(articleModel.getTopicId());
        article.setUserId(articleModel.getUserId());

        article.setPreArticleId(0L);
        article.setNextArticleId(0L);

        Long maxBranchNumber = articleModelMapperImpl.selectMaxBranchNumberByMainNumber(articleModel.getTopicId(),articleModel.getMainNumber());

        article.setBranchNumber(maxBranchNumber+1);
        ArticleModel ret = articleModelMapperImpl.insert(article);
        map.put("articleId",ret.getArticleId());
        map.put("branchNumber",ret.getBranchNumber());

        return map;
    }
    /**
     * 添加链接下一篇文章
     * @param userId
     * @param articleId
     * @param nextArticleId
     * @return
     */
    public int postInsertLinkNextArticleInfo(Long userId,Long articleId,Long nextArticleId) {
        int ret = articleModelMapperImpl.selectDecideByUserIdAndArticleId(userId,articleId);
        if (ret == 0) return 0;

        return articleModelMapperImpl.updateForNextArticleId(articleId,nextArticleId);
    }
    /**
     * 更改下一篇链接文章
     * @param userId
     * @param articleId
     * @param nextArticleId
     * @return
     */
    public int postUpdateLinkNextArticleInfo(Long userId,Long articleId,Long nextArticleId) {
        int ret = articleModelMapperImpl.selectDecideByUserIdAndArticleId(userId,articleId);
        if (ret == 0) return 0;

        return articleModelMapperImpl.updateForNextArticleId(articleId,nextArticleId);
    }
    /**
     * 删除下一篇链接文章
     * @param userId
     * @param articleId
     * @return
     */
    public int postDeleteLinkNextArticleInfo(Long userId,Long articleId) {
        //验证是否是拥有者进行更改
        int ret = articleModelMapperImpl.selectDecideByUserIdAndArticleId(userId,articleId);
        if (ret == 0) return 0;

        return articleModelMapperImpl.updateForNextArticleId(articleId,0L);
    }

    /**
     * 文章添加词条
     * 按固定时间统计，词条数量超过一定量的就添加到Article2EntryShowModel里面进行展示
     * @param articleId
     * @param entryId
     * @return
     */
    public int postAddEntryInfoForArticle(Long articleId,Long entryId) {
        Article2EntryModel article2EntryModel = new Article2EntryModel();
        article2EntryModel.setArticleId(articleId);
        article2EntryModel.setEntryId(entryId);
        return article2EntryModelMapperImpl.insert(article2EntryModel);
    }

    /**
     * 浏览点赞，点赞量加1
     * @param articleId
     * @return
     */
    public int postInsertTouchArticleStar(Long articleId) {
        ArticleLikedAndTreadAndBrowseModel model = articleLikedAndTreadAndBrowseModelMapperImpl.selectSingleByArticleId(articleId);
        Long browseAmount = model.getBrowseAmount()+ 1;
        model.setBrowseAmount(browseAmount);
        return articleLikedAndTreadAndBrowseModelMapperImpl.updateByModel(model);
    }

    /**
     * 文章点踩，点踩量加1
     * @param articleId
     * @return
     */
    public int postInsertTouchArticleTread(Long articleId) {
        ArticleLikedAndTreadAndBrowseModel model = articleLikedAndTreadAndBrowseModelMapperImpl.selectSingleByArticleId(articleId);
        Long browseAmount = model.getBrowseAmount()+ 1;
        model.setBrowseAmount(browseAmount);
        return articleLikedAndTreadAndBrowseModelMapperImpl.updateByModel(model);
    }
    /**
     * 浏览文章，浏览量加1
     * @param articleId
     * @return
     */
    public int postInsertTouchArticleBrowse(Long articleId) {
        ArticleLikedAndTreadAndBrowseModel model = articleLikedAndTreadAndBrowseModelMapperImpl.selectSingleByArticleId(articleId);
        Long browseAmount = model.getBrowseAmount()+ 1;
        model.setBrowseAmount(browseAmount);
        return articleLikedAndTreadAndBrowseModelMapperImpl.updateByModel(model);
    }
    /**
     * 随机获取x篇文章
     * @param topicId
     * @return
     */
    public List<ArticleModel> getSelectRandomArticleInfo(Long topicId,Integer randomNumber) {
        return articleModelMapperImpl.selectRandomSingleArticleInfoByTopicId(topicId, randomNumber);
    }
    /**
     * 获取文章展示词条
     * @param articleId
     * @return
     */
    public List<Long> getSelectMultiEntryIdsByArticleId(Long articleId) {
        return article2EntryShowModelMapperImpl.selectMultiEntryIdByArticleId(articleId);
    }

    /**
     * 获取最近一条文章
     * @param topicId
     * @return
     */
    public ArticleModel getSelectLastTimeArticleInfoByTopicId(Long topicId) {
        return articleModelMapperImpl.selectLastArticleByTopicId(topicId);
    }

    /**
     * 更新文章点赞数量
     *
     * @param articleId
     * @return
     */
    public int postUpdateArticleStarAmount(Long articleId) {
        return articleLikedAndTreadAndBrowseModelMapperImpl.updateArticleInfoStarAmountByArticleId(articleId);
    }
    /**
     * 更新文章点踩数量
     *
     * @param articleId
     * @return
     */
    public int postUpdateArticleTreadAmount(Long articleId) {
        return articleLikedAndTreadAndBrowseModelMapperImpl.updateArticleInfoTreadAmountByArticleId(articleId);
    }
    /**
     * 根据mainNumber,branchNumber,topicId获取article
     * @param topicId
     * @param mainNumber
     * @param branchNumber
     * @return
     */
    public ArticleModel getSelectSingleArticleByTopicIdAndNumber(
            Long topicId,
            Long mainNumber,
            Long branchNumber
    ) {
        return articleModelMapperImpl.selectByTopicIdAndNumber(topicId,mainNumber,branchNumber);
    }
    /**
     * 获取著述所有文章数量
     * @param topicId
     * @return
     */
    public Long getSelectArticleAmountByTopicId(Long topicId) {
        return articleModelMapperImpl.selectArticleAmountByTopicId(topicId);
    }
    /**
     * 检测文章是否存在
     * @param topicId
     * @param mainNumber
     * @param branchNumber
     * @return
     */
    public int getSelectIsExistTopicArticleInfo(
            Long topicId,
            Long mainNumber,
            Long branchNumber
    ) {
        return articleModelMapperImpl.selectDetectIsExistTopicArticleInfo(topicId,mainNumber,branchNumber);
    }

    /**
     * 获取当前主线下所有分支
     * @param topicId
     * @param mainNumber
     * @return
     */
    public List<ArticleModel> getSelectAllBranchInfoByMainNumber(Long topicId,Long mainNumber) {
        return articleModelMapperImpl.selectBranchArticleByMainNumber(topicId,mainNumber);
    }
    /**
     * 获取著述下所有主线文章
     * @param topicId
     * @param page
     * @return
     */
    public List<ArticleModel> getSelectMultiMainInfos(Long topicId,Long page) {
        return articleModelMapperImpl.selectMultiMainArticleInfo(topicId,page);
    }
    /**
     * 获取著述下所有主线文章数量
     * @param topicId
     * @return
     */
    public Long getSelectMultiMainInfoAmount(Long topicId) {
        return articleModelMapperImpl.selectMainArticleInfoAmountForTopic(topicId);
    }
    /**
     * 发布文章时组添加词条
     * @param articleId
     * @param entryIds
     * @return
     */
    public int postAddMultiArticleEntryIds(Long articleId,List<Long> entryIds) {
        return article2EntryModelMapperImpl.insertMulti(articleId,entryIds);
    }
    /**
     * 更新文章
     * @param userId
     * @param articleId
     * @param content
     * @return
     */
    public int postUpdateArticleInfo(Long userId,Long articleId,String content) {
        return articleModelMapperImpl.updateByParams(userId,articleId,content);
    }
}











