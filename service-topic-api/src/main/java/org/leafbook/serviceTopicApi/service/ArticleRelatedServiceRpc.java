package org.leafbook.serviceTopicApi.service;

import org.leafbook.api.dto.topicService.ArticleAbs;
import org.leafbook.api.modelApi.topicInfo.DirectoryModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.Article2EntryModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleLikedAndTreadAndBrowseModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.serviceTopicApi.dao.Article2EntryModelMapper;
import org.leafbook.serviceTopicApi.dao.ArticleLikedAndTreadAndBrowseModelMapper;
import org.leafbook.serviceTopicApi.dao.ArticleModelMapper;
import org.leafbook.serviceTopicApi.dao.DirectoryModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleRelatedServiceRpc {
    @Autowired
    private ArticleModelMapper articleModelMapper;

    @Autowired
    private Article2EntryModelMapper article2EntryModelMapper;

    @Autowired
    private ArticleLikedAndTreadAndBrowseModelMapper articleLikedAndTreadAndBrowseModelMapper;

    @Autowired
    private DirectoryModelMapper directoryModelMapper;
    /**
     * 文章权限检测
     * @param userId
     * @param articleId
     * @return
     */
    public int postArticleAuthorityDecide(Long userId,Long articleId) {
        return articleModelMapper.selectDecideByUserIdAndArticleId(userId,articleId);
    }
    /**
     * 获取文章
     * @param articleId
     * @return
     */
    public ArticleModel getSelectSingleArticleInfo(Long articleId) {
        return articleModelMapper.selectByArticleId(articleId);
    }

    /**
     * 著述拥有者提交文章
     * @param articleAbs
     * mainNumber必须为空
     * @return code
     */
    public int postPublicArticleByOwner(ArticleAbs articleAbs) {
        ArticleModel articleModel = new ArticleModel();
        articleModel.setArticleTitle(articleModel.getArticleTitle());
        articleModel.setArticleDesc(articleAbs.getArticleDesc());
        articleModel.setArticleContent(articleAbs.getArticleContent());
        articleModel.setTopicId(articleAbs.getTopicId());
        articleModel.setUserId(articleAbs.getUserId());

        articleModel.setPreArticleId(0L);
        articleModel.setNextArticleId(0L);

        Long maxMainNumber = articleModelMapper.selectSingleMaxMainNumberByTopicId(articleAbs.getTopicId());
        Long articleId = articleModelMapper.insertByModelForArticleId(articleModel);

        DirectoryModel directoryModel = new DirectoryModel();
        directoryModel.setArticleId(articleId);
        directoryModel.setPageNumber(maxMainNumber+1);
        directoryModel.setTopicId(articleAbs.getTopicId());
        return directoryModelMapper.insertByModel(directoryModel);

    }
    /**
     * 非著述拥有者提交文章
     * @param articleAbs
     * 必须提交mainNumber
     * @return code
     */
    public int postPublicArticleByOther(ArticleAbs articleAbs) {

        //查找著述是否存在mainNumber,查看参数合法性
        int mainNumber = articleModelMapper.selectByMainNumber(articleAbs.getTopicId(),articleAbs.getMainNumber());
        if (mainNumber == 0) return 4000;

        ArticleModel articleModel = new ArticleModel();
        articleModel.setArticleTitle(articleModel.getArticleTitle());
        articleModel.setArticleDesc(articleAbs.getArticleDesc());
        articleModel.setArticleContent(articleAbs.getArticleContent());
        articleModel.setTopicId(articleAbs.getTopicId());
        articleModel.setUserId(articleAbs.getUserId());

        articleModel.setPreArticleId(0L);
        articleModel.setNextArticleId(0L);

        Long maxBranchNumber = articleModelMapper.selectMaxBranchNumberByMainNumber(articleAbs.getMainNumber());

        articleModel.setBranchNumber(maxBranchNumber+1);
        return articleModelMapper.insert(articleModel);
    }
    /**
     * 添加链接下一篇文章
     * @param userId
     * @param articleId
     * @param nextArticleId
     * @return
     */
    public int postInsertLinkNextArticleInfo(Long userId,Long articleId,Long nextArticleId) {
        int ret = articleModelMapper.selectDecideByUserIdAndArticleId(userId,articleId);
        if (ret == 0) return 0;

        return articleModelMapper.updateForNextArticleId(articleId,nextArticleId);
    }
    /**
     * 更改下一篇链接文章
     * @param userId
     * @param articleId
     * @param nextArticleId
     * @return
     */
    public int postUpdateLinkNextArticleInfo(Long userId,Long articleId,Long nextArticleId) {
        int ret = articleModelMapper.selectDecideByUserIdAndArticleId(userId,articleId);
        if (ret == 0) return 0;

        return articleModelMapper.updateForNextArticleId(articleId,nextArticleId);
    }
    /**
     * 删除下一篇链接文章
     * @param userId
     * @param articleId
     * @return
     */
    public int postDeleteLinkNextArticleInfo(Long userId,Long articleId) {
        //验证是否是拥有者进行更改
        int ret = articleModelMapper.selectDecideByUserIdAndArticleId(userId,articleId);
        if (ret == 0) return 0;

        return articleModelMapper.updateForNextArticleId(articleId,0L);
    }

    /**
     * 文章添加词条
     * 按固定时间统计，词条数量超过一定量的就添加到ArticleEntryInfoShowModel里面进行展示
     * @param articleId
     * @param entryId
     * @return
     */
    public int postAddEntryInfoForArticle(Long articleId,Long entryId) {
        Article2EntryModel article2EntryModel = new Article2EntryModel();
        article2EntryModel.setArticleId(articleId);
        article2EntryModel.setEntryId(entryId);
        return article2EntryModelMapper.insert(article2EntryModel);
    }

    /**
     * 浏览点赞，点赞量加1
     * @param articleId
     * @return
     */
    public int postInsertTouchArticleStar(Long articleId) {
        ArticleLikedAndTreadAndBrowseModel model = articleLikedAndTreadAndBrowseModelMapper.selectSingleByArticleId(articleId);
        Long browseAmount = model.getBrowseAmount()+ 1;
        model.setBrowseAmount(browseAmount);
        return articleLikedAndTreadAndBrowseModelMapper.updateByModel(model);
    }

    /**
     * 文章点踩，点踩量加1
     * @param articleId
     * @return
     */
    public int postInsertTouchArticleTread(Long articleId) {
        ArticleLikedAndTreadAndBrowseModel model = articleLikedAndTreadAndBrowseModelMapper.selectSingleByArticleId(articleId);
        Long browseAmount = model.getBrowseAmount()+ 1;
        model.setBrowseAmount(browseAmount);
        return articleLikedAndTreadAndBrowseModelMapper.updateByModel(model);
    }
    /**
     * 浏览文章，浏览量加1
     * @param articleId
     * @return
     */
    public int postInsertTouchArticleBrowse(Long articleId) {
        ArticleLikedAndTreadAndBrowseModel model = articleLikedAndTreadAndBrowseModelMapper.selectSingleByArticleId(articleId);
        Long browseAmount = model.getBrowseAmount()+ 1;
        model.setBrowseAmount(browseAmount);
        return articleLikedAndTreadAndBrowseModelMapper.updateByModel(model);
    }
    /**
     * 随机获取x篇文章
     * @param topicId
     * @return
     */
    public List<ArticleModel> getSelectRandomArticleInfo(Long topicId,Integer randomNumber) {
        return articleModelMapper.selectRandomSingleArticleInfoByTopicId(topicId, randomNumber);
    }
    /**
     * 获取文章展示词条
     * @param articleId
     * @return
     */
    public List<Long> getSelectMultiEntryIdsByArticleId(Long articleId) {
        return articleModelMapper.selectMultiEntryIdByArticleId(articleId);
    }

    /**
     * 获取最近一条文章
     * @param topicId
     * @return
     */
    public ArticleModel getSelectLastTimeArticleInfoByTopicId(Long topicId) {
        return articleModelMapper.selectLastArticleByTopicId(topicId);
    }
}
