package org.leafbook.serviceTopicApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.dto.topicService.ArticleAbs;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.serviceTopicApi.service.ArticleRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@Api("ArticleRelatedControllerRpc")
@RestController
public class ArticleRelatedControllerRpc {
    @Autowired
    private ArticleRelatedServiceRpc articleRelatedServiceRpc;

    /**
     * 文章权限检测
     * @param userId
     * @param articleId
     * @return
     */
    @ApiOperation("/rpc/post/article/authority/decide")
    @PostMapping("/rpc/post/article/authority/decide")
    public int postArticleAuthorityDecideRpc(@RequestParam("userId") Long userId, @RequestParam("articleId") Long articleId) {
        return articleRelatedServiceRpc.postArticleAuthorityDecide(userId,articleId);
    }

    /**
     * 获取文章
     *
     * @param articleId
     * @return
     */
    @ApiOperation("/rpc/select/single/articleInfo/{articleId}")
    @GetMapping("/rpc/select/single/articleInfo/{articleId}")
    public ArticleModel getSelectSingleArticleInfoRpc(
            @PathVariable("articleId") Long articleId) {
        return articleRelatedServiceRpc.getSelectSingleArticleInfo(articleId);
    }

    /**
     * 著述拥有者提交文章
     *
     * @param articleModel
     * @return map:articleId,mainNumber
     */
    @PostMapping("/rpc/post/public/article/by/owner")
    @ApiOperation("/rpc/post/public/article/by/owner")
    public Map<String,Long> postPublicMainArticleByOtherReturnMainNumberRpc(@RequestBody ArticleModel articleModel) {
        return articleRelatedServiceRpc.postPublicMainArticleByOtherReturnMainNumber(articleModel);
    }

    /**
     * 非著述拥有者提交文章
     *
     * @param articleModel
     * @return map:articleId,branchNumber
     */
    @ApiOperation("/rpc/post/public/article/by/other")
    @PostMapping("/rpc/post/public/article/by/other")
    public Map<String,Long> postPublicBranchArticleByOtherReturnBranchNumberRpc(@RequestBody ArticleModel articleModel) {
        return articleRelatedServiceRpc.postPublicBranchArticleByOtherReturnBranchNumber(articleModel);
    }

    /**
     * 添加链接下一篇文章
     *
     * @param articleId
     * @param nextArticleId
     * @return
     */
    @ApiOperation("/rpc/post/insert/next/articleInfo")
    @PostMapping("/rpc/post/insert/next/articleInfo")
    public int postInsertLinkNextArticleInfoRpc(@RequestParam("userId") Long userId,@RequestParam("articleId") Long articleId, @RequestParam("nextArticleId") Long nextArticleId) {
        return articleRelatedServiceRpc.postInsertLinkNextArticleInfo(userId,articleId, nextArticleId);
    }

    /**
     * 更改下一篇链接文章
     *
     * @param articleId
     * @param nextArticleId
     * @return
     */
    @ApiOperation("/rpc/post/update/next/articleInfo")
    @PostMapping("/rpc/post/update/next/articleInfo")
    public int postUpdateLinkNextArticleInfoRpc(@RequestParam("userId") Long userId,@RequestParam("articleId") Long articleId, @RequestParam("nextArticleId") Long nextArticleId) {
        return articleRelatedServiceRpc.postUpdateLinkNextArticleInfo(userId,articleId, nextArticleId);
    }

    /**
     * 删除下一篇链接文章
     *
     * @param articleId
     * @return
     */
    @ApiOperation("/rpc/post/delete/next/articleInfo")
    @PostMapping("/rpc/post/delete/next/articleInfo")
    public int postDeleteLinkNextArticleInfoRpc(@RequestParam("userId") Long userId, @RequestParam("articleId") Long articleId) {
        return articleRelatedServiceRpc.postDeleteLinkNextArticleInfo(userId, articleId);
    }

    /**
     * 文章添加词条
     * 按固定时间统计，词条数量超过一定量的就添加到ArticleEntryInfoShowModel里面进行展示
     *
     * @param articleId
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/post/add/entry/with/article")
    @PostMapping("/rpc/post/add/entry/with/article")
    public int postAddEntryInfoWithArticleRpc(@RequestParam("articleId") Long articleId, @RequestParam("entryId") Long entryId) {
        return articleRelatedServiceRpc.postAddEntryInfoForArticle(articleId, entryId);
    }

    /**
     * 浏览点赞，点赞量加1
     *
     * @param articleId
     * @return
     */
    @ApiOperation("/rpc/post/insert/touch/article/star")
    @PostMapping("/rpc/post/insert/touch/article/star")
    public int postInsertTouchArticleStarRpc(@RequestParam("articleId") Long articleId) {
        return articleRelatedServiceRpc.postInsertTouchArticleStar(articleId);
    }

    /**
     * 文章点踩，点踩量加1
     *
     * @param articleId
     * @return
     */
    @ApiOperation("/rpc/post/insert/touch/article/tread")
    @PostMapping("/rpc/post/insert/touch/article/tread")
    public int postInsertTouchArticleTreadRpc(@RequestParam("articleId") Long articleId) {
        return articleRelatedServiceRpc.postInsertTouchArticleTread(articleId);
    }

    /**
     * 浏览文章，浏览量加1
     *
     * @param articleId
     * @return
     */
    @ApiOperation("/rpc/post/insert/touch/article/browse")
    @PostMapping("/rpc/post/insert/touch/article/browse")
    public int postInsertTouchArticleBrowseRpc(@RequestParam("articleId") Long articleId) {
        return articleRelatedServiceRpc.postInsertTouchArticleBrowse(articleId);
    }

    /**
     * 随机获取一篇文章
     * @param topicId
     * @param randomNumber: 随机数量
     * @return
     */
    @ApiOperation("/rpc/get/select/random/articleInfo/by/topicId/{topicId}")
    @GetMapping("/rpc/get/select/random/articleInfo/by/topicId/{topicId}")
    public List<ArticleModel> getSelectRandomArticleInfoRpc(@PathVariable("topicId")Long topicId,Integer randomNumber) {
        return articleRelatedServiceRpc.getSelectRandomArticleInfo(topicId, randomNumber);
    }

    /**
     * 获取文章展示词条
     * @param articleId
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/entryId/by/articleId/{articleId}")
    @GetMapping("/rpc/get/select/multi/entryId/by/articleId/{articleId}")
    public List<Long> getSelectMultiEntryIdsByArticleIdRpc(
            @PathVariable("articleId") Long articleId,
            Integer randomNumber) {
        return articleRelatedServiceRpc.getSelectMultiEntryIdsByArticleId(articleId);
    }


    /**
     * 获取最近一条文章
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/select/lastTime/articleInfo/by/topicId/{topicId}")
    @GetMapping("/rpc/select/lastTime/articleInfo/by/topicId/{topicId}")
    public ArticleModel getSelectLastTimeArticleInfoByTopicIdRpc(@PathVariable("topicId") Long topicId) {
        return articleRelatedServiceRpc.getSelectLastTimeArticleInfoByTopicId(topicId);
    }

    /**
     * 更新文章点赞数量
     *
     * @param articleId
     * @return
     */
    @ApiOperation("/rpc/post/update/article/star/amount")
    @PostMapping("/rpc/post/update/article/star/amount")
    public int postUpdateArticleStarAmountRpc(@RequestParam("articleId") Long articleId) {
        return articleRelatedServiceRpc.postUpdateArticleStarAmount(articleId);
    }

    /**
     * 更新文章点踩数量
     *
     * @param articleId
     * @return
     */
    @ApiOperation("/rpc/post/update/article/tread/amount")
    @PostMapping("/rpc/post/update/article/tread/amount")
    public int postUpdateArticleTreadAmountRpc(@RequestParam("articleId") Long articleId) {
        return articleRelatedServiceRpc.postUpdateArticleTreadAmount(articleId);
    }

    /**
     * 根据mainNumber,branchNumber,topicId获取article
     * @param topicId
     * @param mainNumber
     * @param branchNumber
     * @return
     */
    @ApiOperation("/rpc/get/select/single/articleInfo/by/topicId/and/number")
    @GetMapping("/rpc/get/select/single/articleInfo/by/topicId/and/number")
    public ArticleModel getSelectSingleArticleByTopicIdAndNumberRpc(
            @RequestParam("topicId")Long topicId,
            @RequestParam("mainNumber")Long mainNumber,
            @RequestParam("branchNumber")Long branchNumber
    ) {
        return articleRelatedServiceRpc.getSelectSingleArticleByTopicIdAndNumber(topicId,mainNumber,branchNumber);
    }


    /**
     * 获取著述所有文章数量
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/articleAmount/by/topicId")
    @GetMapping("/rpc/get/select/articleAmount/by/topicId")
    public Long getSelectArticleAmountByTopicIdRpc(@RequestParam("topicId")Long topicId) {
        return articleRelatedServiceRpc.getSelectArticleAmountByTopicId(topicId);
    }

    /**
     * 检测文章是否存在
     * @param topicId
     * @param mainNumber
     * @param branchNumber
     * @return
     */
    @ApiOperation("/api/get/select/is/exist/topicArticleInfo")
    @GetMapping("/api/get/select/is/exist/topicArticleInfo")
    public int getSelectIsExistTopicArticleInfoRpc(
            @RequestParam("topicId")Long topicId,
            @RequestParam("mainNumber")Long mainNumber,
            @RequestParam("branchNumber")Long branchNumber
    ) {
        return articleRelatedServiceRpc.getSelectIsExistTopicArticleInfo(topicId,mainNumber,branchNumber);
    }

    /**
     * 获取当前主线下所有分支
     * @param topicId
     * @param mainNumber
     * @return
     */
    @ApiOperation("/rpc/get/select/all/branchInfo/by/mainNumber")
    @GetMapping("/rpc/get/select/all/branchInfo/by/mainNumber")
    public List<ArticleModel> getSelectAllBranchInfoByMainNumberRpc(
            @RequestParam("topicId")Long topicId,
            @RequestParam("mainNumber")Long mainNumber
    ) {
        return articleRelatedServiceRpc.getSelectAllBranchInfoByMainNumber(topicId,mainNumber);
    }


    /**
     * 获取著述下所有主线文章
     * @param topicId
     * @param page
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/mainInfos")
    @GetMapping("/rpc/get/select/multi/mainInfos")
    public List<ArticleModel> getSelectMultiMainInfosRpc(
            @RequestParam("topicId")Long topicId,
            @RequestParam("page")Long page
    ) {
        return articleRelatedServiceRpc.getSelectMultiMainInfos(topicId,page);
    }

    /**
     * 获取著述下所有主线文章数量
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/mainInfoAmount")
    @GetMapping("/rpc/get/select/multi/mainInfoAmount")
    public Long getSelectMultiMainInfoAmountRpc(@RequestParam("topicId")Long topicId) {
        return articleRelatedServiceRpc.getSelectMultiMainInfoAmount(topicId);
    }


    /**
     * 发布文章时组添加词条
     * @param articleId
     * @param entryIds
     * @return
     */
    @ApiOperation("/rpc/post/add/multi/articleEntryIds")
    @PostMapping("/rpc/post/add/multi/articleEntryIds")
    public int postAddMultiArticleEntryIdsRpc(
            @RequestParam("articleId")Long articleId,
            @RequestParam("entryIds")List<Long> entryIds
    ) {
        return articleRelatedServiceRpc.postAddMultiArticleEntryIds(articleId,entryIds);
    }

    /**
     * 更新文章
     * @param userId
     * @param articleId
     * @param content
     * @return
     */
    @ApiOperation("/rpc/post/update/articleInfo")
    @PostMapping("/rpc/post/update/articleInfo")
    public int postUpdateArticleInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("articleId")Long articleId,
            @RequestParam("content")String content
    ) {
        return articleRelatedServiceRpc.postUpdateArticleInfo(userId,articleId,content);
    }

}
