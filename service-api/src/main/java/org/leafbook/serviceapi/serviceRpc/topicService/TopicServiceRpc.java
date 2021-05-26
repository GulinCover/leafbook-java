package org.leafbook.serviceapi.serviceRpc.topicService;

import org.leafbook.api.modelApi.topicInfo.ContributorModel;
import org.leafbook.api.modelApi.topicInfo.DirectoryModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.api.modelApi.topicInfo.articleInfo.ArticleModel;
import org.leafbook.serviceapi.openfeinFallback.topicService.TopicServiceRpcFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    List<TopicModel> getSelectMultiTopicInfoByEntryIdRpc(
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
     * 获取著述踩数量
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
    @PostMapping("/rpc/post/select/me/topic/by/page")
    List<TopicModel> postSelectMeTopicInfoRpc(@RequestParam("userId") Long userId, @RequestParam("page") Long page);

    /**
     * 获取数量
     *
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/me/topic/page")
    Long postSelectMeTopicInfoPageRpc(@RequestParam("userId") Long userId);

    /**
     * 模糊搜索自己拥有的著述
     *
     * @param userId
     * @param blurry
     * @param page
     * @return
     */
    @PostMapping("/rpc/post/select/multi/topicInfo/By/userId")
    List<TopicModel> postSelectMultiTopicInfoByUserIdRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("blurry") String blurry,
            @RequestParam("page") Long page
    );


    /**
     * 获取点赞排行著述id,每页15条
     *
     * @param page
     * @return
     */
    @GetMapping("/rpc/get/select/multi/topicId/by/star/rank/page/{page}")
    List<Long> getSelectMultiTopicIdByStarRankRpc(@PathVariable("page") Long page);

    /**
     * 获取著述的贡献者数量
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/get/select/contributorAmount/by/topicId/{topicId}")
    Long getSelectContributorAmountByTopicIdRpc(@PathVariable("topicId") Long topicId);

    /**
     * 获取著述的管理者数量
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/get/select/managerAmount/by/topicId/{topicId}")
    Long getSelectManagerAmountByTopicIdRpc(@PathVariable("topicId") Long topicId);

    /**
     * 获取著述的贡献者id
     *
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/select/multi/contributorId/by/topicId")
    List<Long> postSelectMultiContributorIdByTopicIdRpc(
            @RequestParam("topicId") Long topicId,
            @RequestParam("page") Long page);

    /**
     * 获取著述的贡献者数量
     *
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/select/multi/contributorId/by/topicId/page")
    Long postSelectMultiContributorIdByTopicIdPageRpc(
            @RequestParam("topicId") Long topicId);

    /**
     * 根据词条随机查询5~8条著述
     *
     * @param entryId
     * @return
     */
    @GetMapping("/rpc/get/select/random/multi/topicInfo/by/entryId")
    List<TopicModel> getSelectRandomMultiTopicInfoByEntryIdRpc(@RequestParam("entryId") Long entryId);

    /**
     * 更改著述点赞数量
     *
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/update/topic/star/amount")
    int postUpdateTopicStarAmountRpc(@RequestParam("topicId") Long topicId);

    /**
     * 更改著述点踩数量
     *
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/update/topic/tread/amount")
    int postUpdateTopicTreadAmountRpc(@RequestParam("topicId") Long topicId);

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
    @GetMapping("/rpc/select/single/articleInfo/{articleId}")
    ArticleModel getSelectSingleArticleInfoRpc(@PathVariable("articleId") Long articleId);

    /**
     * 著述拥有者提交文章
     *
     * @param articleModel
     * @return map:articleId,mainNumber
     */
    @PostMapping("/rpc/post/public/article/by/owner")
    Map<String, Long> postPublicMainArticleByOtherReturnMainNumberRpc(@RequestBody ArticleModel articleModel);

    /**
     * 非著述拥有者提交文章
     *
     * @param articleModel
     * @return map:articleId,branchNumber
     */
    @PostMapping("/rpc/post/public/article/by/other")
    Map<String, Long> postPublicBranchArticleByOtherReturnBranchNumberRpc(@RequestBody ArticleModel articleModel);

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

    /**
     * 获取最近一条文章
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/select/lastTime/articleInfo/by/topicId/{topicId}")
    ArticleModel getSelectLastTimeArticleInfoByTopicIdRpc(@PathVariable("topicId") Long topicId);

    /**
     * 更新文章点赞数量
     *
     * @param articleId
     * @return
     */
    @PostMapping("/rpc/post/update/article/star/amount")
    int postUpdateArticleStarAmountRpc(@RequestParam("articleId") Long articleId);

    /**
     * 更新文章点踩数量
     *
     * @param articleId
     * @return
     */
    @PostMapping("/rpc/post/update/article/tread/amount")
    int postUpdateArticleTreadAmountRpc(@RequestParam("articleId") Long articleId);

    /**
     * 搜索指定著述
     *
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param entryIds
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    @GetMapping("/rpc/get/select/search/multi/topicInfo")
    List<TopicModel> getSelectSearchMultiTopicInfoRpc(
            @RequestParam("status") Integer status,
            @RequestParam("entryIds") List<Long> entryIds,
            @RequestParam("content") String content,
            @RequestParam("startTime") Long startTime,
            @RequestParam("endTime") Long endTime,
            @RequestParam("page") Long page
    );

    /**
     * 获取搜索条数
     *
     * @param status
     * @param entryIds
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    @GetMapping("/rpc/get/select/search/multi/topicInfo/page")
    Long getSelectSearchMultiTopicInfoPageRpc(
            @RequestParam("status") Integer status,
            @RequestParam("entryIds") List<Long> entryIds,
            @RequestParam("content") String content,
            @RequestParam("startTime") Long startTime,
            @RequestParam("endTime") Long endTime,
            @RequestParam("page") Long page
    );

    /**
     * 搜索指定著述
     *
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param content
     * @param page
     * @return
     */
    @GetMapping("/rpc/get/select/search/multi/by/content/topicInfo")
    List<TopicModel> getSelectSearchMultiTopicInfoByContentRpc(
            @RequestParam("status") Integer status,
            @RequestParam("content") String content,
            @RequestParam("page") Long page
    );

    /**
     * 指定著述数量
     *
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param content
     * @return
     */
    @GetMapping("/rpc/get/select/search/multi/topicInfo/by/content/page")
    Long getSelectSearchMultiTopicInfoByContentPageRpc(
            @RequestParam("status") Integer status,
            @RequestParam("content") String content
    );

    /**
     * 限定搜索
     *
     * @param status
     * @param entryId
     * @param content
     * @param page
     * @return
     */
    @GetMapping("/rpc/get/select/search/multi/by/content/and/entryId/topicInfo")
    List<TopicModel> getSelectSearchMultiTopicInfoByContentAndEntryIdRpc(
            @RequestParam("status") Integer status,
            @RequestParam("entryId") Long entryId,
            @RequestParam("content") String content,
            @RequestParam("page") Long page
    );

    /**
     * 根据topicIds组查entryIds
     *
     * @param topicIds
     * @return
     */
    @GetMapping("/rpc/get/select/entryIds/by/topicIds")
    List<Long> getSelectEntryIdsByTopicIdsRpc(@RequestParam("topicIds") List<Long> topicIds);

    /**
     * 根据entryIds获取著述数量
     *
     * @param entryIds
     * @return
     */
    @GetMapping("/rpc/get/select/entry/amount/by/entryIds")
    Map<Long, Long> getSelectEntryAmountByEntryIdsRpc(@RequestParam("entryIds") List<Long> entryIds);


    /**
     * 更改用topic拥有者,用于购买后使用时使用
     *
     * @param userId
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/update/single/topicInfo/with/owner")
    int postUpdateSingleTopicInfoWithOwnerRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId
    );

    /**
     * 获取用户最新几条著述信息
     *
     * @param userId
     * @param number
     * @return
     */
    @PostMapping("/rpc/post/select/multi/lastTopicInfo")
    List<TopicModel> postSelectMultiLastTopicInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("number") Integer number
    );

    /**
     * 获取著述管理者id
     *
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/select/all/managerUserInfo")
    List<Long> postSelectAllManagerUserIdsRpc(
            @RequestParam("topicId") Long topicId
    );

    /**
     * 判断topicId是否存在
     *
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/isExist/for/topicInfo")
    int postIsExistForTopicInfoRpc(
            @RequestParam("topicId") Long topicId
    );

    /**
     * 根据mainNumber,branchNumber,topicId获取article
     *
     * @param topicId
     * @param mainNumber
     * @param branchNumber
     * @return
     */
    @GetMapping("/rpc/get/select/single/articleInfo/by/topicId/and/number")
    ArticleModel getSelectSingleArticleByTopicIdAndNumberRpc(
            @RequestParam("topicId") Long topicId,
            @RequestParam("mainNumber") Long mainNumber,
            @RequestParam("branchNumber") Long branchNumber
    );

    /**
     * 获取著述所有文章数量
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/get/select/articleAmount/by/topicId")
    Long getSelectArticleAmountByTopicIdRpc(@RequestParam("topicId") Long topicId);

    /**
     * 检测文章是否存在
     *
     * @param topicId
     * @param mainNumber
     * @param branchNumber
     * @return
     */
    @GetMapping("/api/get/select/is/exist/topicArticleInfo")
    int getSelectIsExistTopicArticleInfoRpc(
            @RequestParam("topicId") Long topicId,
            @RequestParam("mainNumber") Long mainNumber,
            @RequestParam("branchNumber") Long branchNumber
    );

    /**
     * 获取当前主线下所有分支
     *
     * @param topicId
     * @param mainNumber
     * @return
     */
    @GetMapping("/rpc/get/select/all/branchInfo/by/mainNumber")
    List<ArticleModel> getSelectAllBranchInfoByMainNumberRpc(
            @RequestParam("topicId") Long topicId,
            @RequestParam("mainNumber") Long mainNumber
    );

    /**
     * 获取著述下所有主线文章
     *
     * @param topicId
     * @param page
     * @return
     */
    @GetMapping("/rpc/get/select/multi/mainInfos")
    List<ArticleModel> getSelectMultiMainInfosRpc(
            @RequestParam("topicId") Long topicId,
            @RequestParam("page") Long page
    );

    /**
     * 获取著述下所有主线文章数量
     *
     * @param topicId
     * @return
     */
    @GetMapping("/rpc/get/select/multi/mainInfoAmount")
    Long getSelectMultiMainInfoAmountRpc(@RequestParam("topicId") Long topicId);

    /**
     * 发布文章时组添加词条
     *
     * @param articleId
     * @param entryIds
     * @return
     */
    @PostMapping("/rpc/post/add/multi/articleEntryIds")
    int postAddMultiArticleEntryIdsRpc(
            @RequestParam("articleId") Long articleId,
            @RequestParam("entryIds") List<Long> entryIds
    );

    /**
     * 更新文章
     *
     * @param userId
     * @param articleId
     * @param content
     * @return
     */
    @PostMapping("/rpc/post/update/articleInfo")
    int postUpdateArticleInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("articleId") Long articleId,
            @RequestParam("content") String content
    );

    /**
     * 获取用户发布了的著述数量
     *
     * @param userId
     * @return
     */
    @GetMapping("/rpc/get/select/topicAmount/by/userId")
    Long getSelectTopicAmountByUserIdRpc(@RequestParam("userId") Long userId);

    /**
     * 获取topic总数量
     *
     * @return
     */
    @GetMapping("/rpc/get/select/all/topic/page/amount")
    Long getSelectAllTopicPageAmountRpc();

    /**
     * 获取点赞排行总数量
     *
     * @return
     */
    @GetMapping("/rpc/get/select/all/topic/by/star/rank/page/amount")
    Long getSelectMultiTopicIdByStarRankPageAmountRpc();

    /**
     * 创建赞踩表
     * @param topicId
     * @return
     */
    @PostMapping("/rpc/post/create/topic/likedAndTread")
    int postCreateTopicLikedAndTreadRpc(@RequestParam("topicId")Long topicId);

    /**
     * 点赞排行模糊搜索
     * @param page
     * @param blurry
     * @param entry
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/rpc/get/select/multi/blurry/search")
    List<TopicModel> getSelectMultiBlurrySearchRpc(
            @RequestParam("page")Long page,
            @RequestParam("blurry")String blurry,
            @RequestParam("entry")Long entry,
            @RequestParam("startTime")Long startTime,
            @RequestParam("endTime")Long endTime
    );
    /**
     * 点赞排行模糊搜索数据数量
     * @param page
     * @param blurry
     * @param entry
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/rpc/get/select/multi/blurry/search/page")
    Long getSelectMultiBlurrySearchPageAmountRpc(
            @RequestParam("page")Long page,
            @RequestParam("blurry")String blurry,
            @RequestParam("entry")Long entry,
            @RequestParam("startTime")Long startTime,
            @RequestParam("endTime")Long endTime
    );

    /**
     * 获取词条下所有topic数量
     * @param entryId
     * @return
     */
    @GetMapping("/rpc/get/select/all/topic/page/amount/by/entryId")
    Long getSelectAllTopicInfoPageAmountByEntryIdRpc(
        @RequestParam("entryId")Long entryId
    );

    /**
     * 随机获取正在拍卖的topic
     * @param entryId
     * @return
     */
    @GetMapping("/rpc/get/select/random/multi/topicIds/by/entryId/for/auction")
    List<Long> getSelectRandomMultiTopicIdsByEntryIdForAuctionInfoRpc(
            @RequestParam("entryId")Long entryId
    );
}












