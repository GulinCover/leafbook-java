package org.leafbook.serviceapi.serviceRpc.topicService;

import org.leafbook.api.dto.topicService.ArticleAbs;
import org.leafbook.api.modelApi.topicInfo.ContributorModel;
import org.leafbook.api.modelApi.topicInfo.DirectoryModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.serviceapi.openfeinFallback.topicService.TopicServiceRpcFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        value = "service-topic-api",
        fallbackFactory = TopicServiceRpcFallback.class
)
public interface TopicServiceRpc {
    /**
     * 获取著述拥有者id
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/get/select/topic/ownerId/{topicId}")
    Long getSelectTopicOwnerRpc(@PathVariable("topicId") Long topicId);

    /**
     * 获取所有著述管理者id
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/get/select/topic/manager/{topicId}")
    List<Long> getSelectTopicManagerRpc(@PathVariable("topicId") Long topicId);

    /**
     * 添加管理者
     *
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/add/topic/manager")
    int postAddTopicManagerRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId,
            @RequestParam("managerId") Long managerId);

    /**
     * 删除管理者
     *
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/delete/topic/manager")
    int postDeleteTopicManagerRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId,
            @RequestParam("managerId") Long managerId);

    /**
     * 著述拥有者权限检测
     *
     * @param userId
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/topic/owner/authority/decide")
    int postTopicOwnerAuthorityDecideRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId);

    /**
     * 著述管理者权限检测
     *
     * @param userId
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/topic/manager/authority/decide")
    int postTopicManagerAuthorityDecideRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId);

    /**
     * topicId查询
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/get/select/single/topicInfo/{topicId}")
    TopicModel getSelectSingleTopicInfoRpc(@PathVariable("topicId") Long topicId);

    /**
     * topicIds组查询
     *
     * @param topicIds
     * @return
     */
    @GetMapping("/rpc/get/select/multi/topicInfo")
    List<TopicModel> getSelectMultiTopicInfoRpc(
            @RequestParam("topicIds") List<Long> topicIds);

    /**
     * 创建著述
     *
     * @param userId
     * @param topicTitle
     * @param topicDesc
     * @param entryIds
     * @return topicId
     */
    @PostMapping("/rpc/post/create/single/topicInfo")
    Long postCreateTopicInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicTitle") String topicTitle,
            @RequestParam("topicDesc") String topicDesc,
            @RequestParam("entryIds") List<Long> entryIds);

    /**
     * 更换著述封面
     *
     * @param userId
     * @param topicId
     * @param cover
     * @return
     */
    @PostMapping("/rpc/post/update/single/topicInfo/cover")
    int postUpdateSingleTopicInfoForCoverRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId,
            @RequestParam("cover") String cover);

    /**
     * 更改著述描述
     *
     * @param userId
     * @param topicId
     * @param topicDesc
     * @return code
     */
    @PostMapping("/rpc/post/update/single/topicInfo/desc")
    int postUpdateTopicInfoDescRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId,
            @RequestParam("topicDesc") String topicDesc);

    /**
     * 查询著述下默认文章目录顺序
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/get/select/directoryInfo/by/topicId/{topicId}")
    List<DirectoryModel> getSelectDirectoryInfoRpc(
            @PathVariable("topicId") Long topicId);

    /**
     * 修改著述默认文章顺序
     *
     * @param userId
     * @param topicId
     * @param pageId
     * @param articleId
     * @return code
     */
    @GetMapping("/rpc/post/update/directoryInfo/by/topicId/{topicId}")
    int postUpdateDirectoryInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId,
            @RequestParam("pageId") Long pageId,
            @RequestParam("articleId") Long articleId);

    /**
     * 添加词条
     *
     * @param userId
     * @param topicId
     * @param entryId
     * @return code
     */
    @PostMapping("/rpc/post/add/single/topicInfo/entryId")
    int postAddTopicInfoEntryRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId,
            @RequestParam("entryId") Long entryId);

    /**
     * 单查询著述下的词条，词条提交数大于50的进行展示
     * 定时任务每一小时统计一次
     *
     * @param topicId
     * @return entryIds
     */
    @GetMapping("/rpc/get/select/single/topicInfo/entryInfo/by/{topicId}")
    List<Long> getSelectSingleTopicInfoForEntryIdsRpc(
            @PathVariable("topicId") Long topicId);

    /**
     * 给著述点赞，著述赞数加1
     *
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/insert/touch/topic/star")
    int postInsertTouchTopicStarRpc(@RequestParam("topicId") Long topicId);

    /**
     * 给著述点踩，著述踩数加1
     *
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/insert/touch/topic/tread")
    int postInsertTouchTopicTreadRpc(@RequestParam("topicId") Long topicId);

    /**
     * 浏览著述，浏览量加1
     *
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/insert/touch/topic/browse")
    int postInsertTouchTopicBrowseRpc(@RequestParam("topicId") Long topicId);

    /**
     * 获取某词条下的著述数量
     *
     * @param entryId
     * @return
     */
    @GetMapping("/rpc/get/select/multi/topicInfoAmount/by/entryId/{entryId}")
    Long getSelectMultiTopicInfoAmountByEntryInfoRpc(@PathVariable("entryId") Long entryId);

    /**
     * 获取某词条下的著述
     *
     * @param entryId
     * @return
     */
    @GetMapping("/rpc/get/select/multi/topicInfo/by/entryId/{entryId}/page/{page}")
    List<TopicModel> getSelectMultiTopicInfoRpcByEntryIdRpc(
            @PathVariable("entryId") Long entryId,
            @PathVariable("page") Long page);

    /**
     * 获取著述赞数量
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/get/select/topic/star/amount/{topicId}")
    Long getSelectTopicStarAmountRpc(@PathVariable("topicId") Long topicId);

    /**
     * 获取著述赞数量
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/get/select/topic/tread/amount/{topicId}")
    Long getSelectTopicTreadAmountRpc(@PathVariable("topicId") Long topicId);

    /**
     * 获取著述赞数量
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/get/select/topic/browse/amount/{topicId}")
    Long getSelectTopicBrowseAmountRpc(@PathVariable("topicId") Long topicId);

    /**
     * 随机获取贡献者信息
     *
     * @param topicId
     * @param randomNumber
     * @return
     */
    @GetMapping("/rpc/get/select/multi/random/contributorInfo/by/topicId/{topicId}")
    List<ContributorModel> getSelectRandomContributorInfoRpc(
            @PathVariable("topicId") Long topicId,
            @RequestParam("randomNumber") Integer randomNumber);


    /**
     * 获取用户发布的著述
     *
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/me/topic")
    List<TopicModel> postSelectMeTopicInfoRpc(@RequestParam("userId") Long userId);

    //article service rpc


    /**
     * 文章权限检测
     *
     * @param userId
     * @param articleId
     * @return
     */
    @PostMapping("/rpc/post/article/authority/decide")
    int postArticleAuthorityDecideRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("articleId") Long articleId);

    /**
     * 获取文章
     *
     * @param articleId
     * @return
     */
    @GetMapping("/rpc/select/single/articleInfo/{articleId}/with/topicInfo/{topicId}")
    ArticleModel getSelectSingleArticleInfoRpc(@PathVariable("articleId") Long articleId);

    /**
     * 著述拥有者提交文章
     *
     * @param articleAbs
     * @return code
     */
    @PostMapping("/rpc/post/public/article/by/owner")
    int postPublicArticleByOwnerRpc(@RequestParam("articleAbs") ArticleAbs articleAbs);

    /**
     * 非著述拥有者提交文章
     *
     * @param articleAbs
     * @return code
     */
    @PostMapping("/rpc/post/public/article/by/other")
    int postPublicArticleByOtherRpc(@RequestParam("articleAbs") ArticleAbs articleAbs);

    /**
     * 添加链接下一篇文章
     *
     * @param articleId
     * @param nextArticleId
     * @return
     */
    @PostMapping("/rpc/post/insert/next/articleInfo")
    int postInsertLinkNextArticleInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("articleId") Long articleId,
            @RequestParam("nextArticleId") Long nextArticleId);

    /**
     * 更改下一篇链接文章
     *
     * @param articleId
     * @param nextArticleId
     * @return
     */
    @PostMapping("/rpc/post/update/next/articleInfo")
    int postUpdateLinkNextArticleInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("articleId") Long articleId,
            @RequestParam("nextArticleId") Long nextArticleId);

    /**
     * 删除下一篇链接文章
     *
     * @param articleId
     * @return
     */
    @PostMapping("/rpc/post/delete/next/articleInfo")
    int postDeleteLinkNextArticleInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("articleId") Long articleId);

    /**
     * 文章添加词条
     * 按固定时间统计，词条数量超过一定量的就添加到ArticleEntryInfoShowModel里面进行展示
     *
     * @param articleId
     * @param entryId
     * @return
     */
    @PostMapping("/rpc/post/add/entry/with/article")
    int postAddEntryInfoWithArticleRpc(
            @RequestParam("articleId") Long articleId,
            @RequestParam("entryId") Long entryId);

    /**
     * 浏览点赞，点赞量加1
     *
     * @param articleId
     * @return
     */
    @PostMapping("/rpc/post/insert/touch/article/star")
    int postInsertTouchArticleStarRpc(@RequestParam("articleId") Long articleId);

    /**
     * 文章点踩，点踩量加1
     *
     * @param articleId
     * @return
     */
    @PostMapping("/rpc/post/insert/touch/article/tread")
    int postInsertTouchArticleTreadRpc(@RequestParam("articleId") Long articleId);

    /**
     * 浏览文章，浏览量加1
     *
     * @param articleId
     * @return
     */
    @PostMapping("/rpc/post/insert/touch/article/browse")
    int postInsertTouchArticleBrowseRpc(@RequestParam("articleId") Long articleId);

    /**
     * 随机获取多篇文章
     *
     * @param topicId
     * @param randomNumber
     * @return
     */
    @GetMapping("/rpc/get/select/random/articleInfo/by/topicId/{topicId}")
    List<ArticleModel> getSelectRandomArticleInfoRpc(@PathVariable("topicId") Long topicId, @RequestParam("randomNumber") Integer randomNumber);

    /**
     * 获取文章展示词条
     *
     * @param articleId
     * @return
     */
    @GetMapping("/rpc/get/select/multi/entryId/by/articleId/{articleId}")
    List<Long> getSelectMultiEntryIdsByArticleIdRpc(@PathVariable("articleId") Long articleId);


}












