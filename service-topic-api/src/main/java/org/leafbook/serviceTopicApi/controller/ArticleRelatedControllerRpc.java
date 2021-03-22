package org.leafbook.serviceTopicApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.dto.topicService.ArticleAbs;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.serviceTopicApi.service.ArticleRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @ApiOperation("/rpc/select/single/articleInfo/{articleId}/with/topicInfo/{topicId}")
    @GetMapping("/rpc/select/single/articleInfo/{articleId}/with/topicInfo/{topicId}")
    public ArticleModel getSelectSingleArticleInfoRpc(@PathVariable("articleId") Long articleId) {
        return articleRelatedServiceRpc.getSelectSingleArticleInfo(articleId);
    }

    /**
     * 著述拥有者提交文章
     *
     * @param articleAbs
     * @return code
     */
    @ApiOperation("/rpc/post/public/article/by/owner")
    @PostMapping("/rpc/post/public/article/by/owner")
    public int postPublicArticleByOwnerRpc(@RequestParam("articleAbs") ArticleAbs articleAbs) {
        return articleRelatedServiceRpc.postPublicArticleByOwner(articleAbs);
    }

    /**
     * 非著述拥有者提交文章
     *
     * @param articleAbs
     * @return code
     */
    @ApiOperation("/rpc/post/public/article/by/other")
    @PostMapping("/rpc/post/public/article/by/other")
    public int postPublicArticleByOtherRpc(@RequestParam("articleAbs") ArticleAbs articleAbs) {
        return articleRelatedServiceRpc.postPublicArticleByOther(articleAbs);
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

}
