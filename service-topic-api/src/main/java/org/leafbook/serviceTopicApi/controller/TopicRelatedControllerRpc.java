package org.leafbook.serviceTopicApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.api.modelApi.topicInfo.ContributorModel;
import org.leafbook.api.modelApi.topicInfo.DirectoryModel;
import org.leafbook.api.modelApi.topicInfo.TopicModel;
import org.leafbook.serviceTopicApi.service.TopicRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@Api("TopicRelatedControllerRpc")
@RestController
public class TopicRelatedControllerRpc {
    @Autowired
    private TopicRelatedServiceRpc topicRelatedServiceRpc;

    /**
     * 获取著述拥有者id
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/topic/ownerId/{topicId}")
    @GetMapping("/rpc/get/select/topic/ownerId/{topicId}")
    public Long getSelectTopicOwnerRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectTopicOwner(topicId);
    }
    /**
     * 获取所有著述管理者id
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/topic/manager/{topicId}")
    @GetMapping("/rpc/get/select/topic/manager/{topicId}")
    public List<Long> getSelectTopicManagerRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectTopicManager(topicId);
    }
    /**
     * 添加管理者
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/add/topic/manager")
    @PostMapping("/rpc/post/add/topic/manager")
    public int postAddTopicManagerRpc(@RequestParam("userId") Long userId,@RequestParam("topicId")Long topicId,@RequestParam("managerId") Long managerId) {
        return topicRelatedServiceRpc.postAddTopicManager(userId,topicId,managerId);
    }

    /**
     * 删除管理者
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/delete/topic/manager")
    @PostMapping("/rpc/post/delete/topic/manager")
    public int postDeleteTopicManagerRpc(@RequestParam("userId") Long userId,@RequestParam("topicId")Long topicId,@RequestParam("managerId") Long managerId) {
        return topicRelatedServiceRpc.postDeleteTopicManager(userId,topicId,managerId);
    }

    /**
     * 著述拥有者权限检测
     * @param userId
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/topic/owner/authority/decide")
    @PostMapping("/rpc/post/topic/owner/authority/decide")
    public int postTopicOwnerAuthorityDecideRpc(@RequestParam("userId") Long userId, @RequestParam("topicId") Long topicId) {
        return topicRelatedServiceRpc.postTopicOwnerAuthorityDecide(userId,topicId);
    }

    /**
     * 著述管理者权限检测
     * @param userId
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/topic/manager/authority/decide")
    @PostMapping("/rpc/post/topic/manager/authority/decide")
    public int postTopicManagerAuthorityDecideRpc(@RequestParam("userId") Long userId, @RequestParam("topicId") Long topicId) {
        return topicRelatedServiceRpc.postTopicManagerAuthorityDecide(userId,topicId);
    }

    /**
     * topicId查询
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/single/topicInfo/{topicId}")
    @GetMapping("/rpc/get/select/single/topicInfo/{topicId}")
    public TopicModel getSelectSingleTopicInfoRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectSingleTopicInfo(topicId);
    }

    /**
     * topicIds组查询
     * @param topicIds
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/topicInfo")
    @GetMapping("/rpc/get/select/multi/topicInfo")
    public List<TopicModel> getSelectMultiTopicInfoRpc(@RequestParam("topicIds")List<Long> topicIds) {
        return topicRelatedServiceRpc.getSelectMultiTopicInfo(topicIds);
    }

    /**
     * 创建著述
     * @param userId
     * @param topicTitle
     * @param topicDesc
     * @param entryIds
     * @return topicId
     */
    @ApiOperation("/rpc/post/create/single/topicInfo")
    @PostMapping("/rpc/post/create/single/topicInfo")
    public Long postCreateTopicInfoRpc(@RequestParam("userId") Long userId,@RequestParam("topicTitle")String topicTitle,@RequestParam("topicDesc")String topicDesc,@RequestParam("entryIds")List<Long> entryIds) {
        return topicRelatedServiceRpc.postCreateTopicInfo(userId, topicTitle, topicDesc, entryIds);
    }

    /**
     * 更换著述封面
     * @param userId
     * @param topicId
     * @param cover
     * @return
     */
    @ApiOperation("/rpc/post/update/single/topicInfo/cover")
    @PostMapping("/rpc/post/update/single/topicInfo/cover")
    public int postUpdateSingleTopicInfoForCoverRpc(@RequestParam("userId") Long userId,@RequestParam("topicId")Long topicId,@RequestParam("cover")String  cover) {
        return topicRelatedServiceRpc.postUpdateSingleTopicInfoForCover(userId, topicId, cover);
    }

    /**
     * 更改著述描述
     * @param userId
     * @param topicId
     * @param topicDesc
     * @return code
     */
    @ApiOperation("/rpc/post/update/single/topicInfo/desc")
    @PostMapping("/rpc/post/update/single/topicInfo/desc")
    public int postUpdateTopicInfoDescRpc(@RequestParam("userId")Long userId,@RequestParam("topicId")Long topicId,@RequestParam("topicDesc")String topicDesc) {
        return topicRelatedServiceRpc.postUpdateTopicInfoDesc(userId, topicId, topicDesc);
    }

    /**
     * 查询著述下默认文章目录顺序
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/directoryInfo/by/topicId/{topicId}")
    @GetMapping("/rpc/get/select/directoryInfo/by/topicId/{topicId}")
    public List<DirectoryModel> getSelectDirectoryInfoRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectDirectoryInfo(topicId);
    }

    /**
     * 修改著述默认文章顺序
     * @param userId
     * @param topicId
     * @param pageId
     * @param articleId
     * @return code
     */
    @ApiOperation("/rpc/post/update/directoryInfo/by/topicId/{topicId}")
    @GetMapping("/rpc/post/update/directoryInfo/by/topicId/{topicId}")
    public int postUpdateDirectoryInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId")Long topicId,
            @RequestParam("pageId")Long pageId,
            @RequestParam("articleId")Long articleId) {
        return topicRelatedServiceRpc.postUpdateDirectoryInfo(userId,topicId,pageId,articleId);
    }

    /**
     * 添加词条
     * @param userId
     * @param topicId
     * @param entryId
     * @return code
     */
    @ApiOperation("/rpc/post/add/single/topicInfo/entryId")
    @PostMapping("/rpc/post/add/single/topicInfo/entryId")
    public int postAddTopicInfoEntryRpc(@RequestParam("userId") Long userId,@RequestParam("topicId")Long topicId,@RequestParam("entryId")Long entryId) {
        return topicRelatedServiceRpc.postAddTopicInfoEntry(userId, topicId, entryId);
    }

    /**
     * 单查询著述下的词条，词条提交数大于50的进行展示
     * 定时任务每一小时统计一次
     * @param topicId
     * @return entryIds
     */
    @ApiOperation("/rpc/get/select/single/topicInfo/entryInfo/by/{topicId}")
    @GetMapping("/rpc/get/select/single/topicInfo/entryInfo/by/{topicId}")
    public List<Long> getSelectSingleTopicInfoForEntryIdsRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectSingleTopicInfoForEntryIds(topicId);
    }

    /**
     * 给著述点赞，著述赞数加1
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/insert/touch/topic/star")
    @PostMapping("/rpc/post/insert/touch/topic/star")
    public int postInsertTouchTopicStarRpc(@RequestParam("topicId")Long topicId) {
        return topicRelatedServiceRpc.postInsertTouchTopicStar(topicId);
    }
    /**
     * 给著述点踩，著述踩数加1
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/insert/touch/topic/tread")
    @PostMapping("/rpc/post/insert/touch/topic/tread")
    public int postInsertTouchTopicTreadRpc(@RequestParam("topicId")Long topicId) {
        return topicRelatedServiceRpc.postInsertTouchTopicTread(topicId);
    }
    /**
     * 浏览著述，浏览量加1
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/insert/touch/topic/browse")
    @PostMapping("/rpc/post/insert/touch/topic/browse")
    public int postInsertTouchTopicBrowseRpc(@RequestParam("topicId")Long topicId) {
        return topicRelatedServiceRpc.postInsertTouchTopicBrowse(topicId);
    }

    /**
     * 获取某词条下的著述数量
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/topicInfoAmount/by/entryId/{entryId}")
    @GetMapping("/rpc/get/select/multi/topicInfoAmount/by/entryId/{entryId}")
    public Long getSelectMultiTopicInfoAmountByEntryInfoRpc(@PathVariable("entryId")Long entryId){
        return topicRelatedServiceRpc.getSelectMultiTopicInfoAmountByEntryInfo(entryId);
    }

    /**
     * 获取某词条下的所有著述
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/topicInfo/by/entryId/{entryId}/page/{page}")
    @GetMapping("/rpc/get/select/multi/topicInfo/by/entryId/{entryId}/page/{page}")
    public List<TopicModel> getSelectMultiTopicInfoRpcByEntryIdRpc(
            @PathVariable("entryId")Long entryId,
            @PathVariable("page")Long page) {
        return topicRelatedServiceRpc.getSelectMultiTopicInfoRpcByEntryId(entryId,page);
    }

    /**
     * 获取著述赞数量
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/topic/star/amount/{topicId}")
    @GetMapping("/rpc/get/select/topic/star/amount/{topicId}")
    public Long getSelectTopicStarAmountRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectTopicStarAmount(topicId);
    }
    /**
     * 获取著述赞数量
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/topic/tread/amount/{topicId}")
    @GetMapping("/rpc/get/select/topic/tread/amount/{topicId}")
    public Long getSelectTopicTreadAmountRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectTopicTreadAmount(topicId);
    }
    /**
     * 获取著述赞数量
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/topic/browse/amount/{topicId}")
    @GetMapping("/rpc/get/select/topic/browse/amount/{topicId}")
    public Long getSelectTopicBrowseAmountRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectTopicBrowseAmount(topicId);
    }

    /**
     * 随机获取贡献者信息
     * @param topicId
     * @param randomNumber
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/random/contributorInfo/by/topicId/{topicId}")
    @GetMapping("/rpc/get/select/multi/random/contributorInfo/by/topicId/{topicId}")
    List<ContributorModel> getSelectRandomContributorInfoRpc(
            @PathVariable("topicId")Long topicId,
            Integer randomNumber) {
        return topicRelatedServiceRpc.getSelectRandomContributorInfo(topicId,randomNumber);
    }

    /**
     * 获取用户发布的著述
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/me/topic/by/page")
    @PostMapping("/rpc/post/select/me/topic/by/page")
    public List<TopicModel> postSelectMeTopicInfoRpc(@RequestParam("userId") Long userId,@RequestParam("page") Long page) {
        return topicRelatedServiceRpc.postSelectMeTopicInfo(userId,page);
    }
    /**
     * 获取数量
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/me/topic/page")
    @PostMapping("/rpc/post/select/me/topic/page")
    public Long postSelectMeTopicInfoPageRpc(@RequestParam("userId") Long userId) {
        return topicRelatedServiceRpc.postSelectMeTopicInfoPage(userId);
    }

    /**
     * 模糊搜索自己拥有的著述
     * @param userId
     * @param blurry
     * @param page
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/topicInfo/By/userId")
    @PostMapping("/rpc/post/select/multi/topicInfo/By/userId")
    List<TopicModel> postSelectMultiTopicInfoByUserIdRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("blurry")String blurry,
            @RequestParam("page")Long page
    ) {
        return topicRelatedServiceRpc.postSelectMultiTopicInfoByUserIdRpc(userId,blurry,page);
    }

    /**
     * 获取点赞排行著述id,每页20条
     * @param page
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/topicId/by/star/rank/page/{page}")
    @GetMapping("/rpc/get/select/multi/topicId/by/star/rank/page/{page}")
    public List<Long> getSelectMultiTopicIdByStarRankRpc(@PathVariable("page")Long page) {
        return topicRelatedServiceRpc.getSelectMultiTopicIdByStarRank(page);
    }

    /**
     * 获取topic总数量
     * @return
     */
    @ApiOperation("/rpc/get/select/all/topic/page/amount")
    @GetMapping("/rpc/get/select/all/topic/page/amount")
    public Long getSelectAllTopicPageAmountRpc() {
        return topicRelatedServiceRpc.getSelectAllTopicPageAmount();
    }

    /**
     * 获取著述的管理者数量
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/managerAmount/by/topicId/{topicId}")
    @GetMapping("/rpc/get/select/managerAmount/by/topicId/{topicId}")
    public Long getSelectManagerAmountByTopicIdRpc(@PathVariable("topicId")Long topicId) {
        return topicRelatedServiceRpc.getSelectManagerAmountByTopicId(topicId);
    }

    /**
     * 获取著述的贡献者id
     *
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/contributorId/by/topicId")
    @PostMapping("/rpc/post/select/multi/contributorId/by/topicId")
    public List<Long> postSelectMultiContributorIdByTopicIdRpc(
            @RequestParam("topicId") Long topicId,
            @RequestParam("page") Long page) {
        return topicRelatedServiceRpc.postSelectMultiContributorIdByTopicId(topicId,page);
    }

    /**
     * 获取著述的贡献者数量
     *
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/get/select/contributorAmount/by/topicId/{topicId}")
    @GetMapping("/rpc/get/select/contributorAmount/by/topicId/{topicId}")
    public Long getSelectContributorAmountByTopicIdRpc(
            @PathVariable("topicId") Long topicId) {
        return topicRelatedServiceRpc.postSelectMultiContributorIdByTopicIdPage(topicId);
    }

    /**
     * 根据词条随机查询5~8条著述
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/get/select/random/multi/topicInfo/by/entryId")
    @GetMapping("/rpc/get/select/random/multi/topicInfo/by/entryId")
    List<TopicModel> getSelectRandomMultiTopicInfoByEntryIdRpc(@RequestParam("entryId")Long entryId) {
        return topicRelatedServiceRpc.getSelectRandomMultiTopicInfoByEntryId(entryId);
    }


    /**
     * 更改著述点赞数量
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/update/topic/star/amount")
    @PostMapping("/rpc/post/update/topic/star/amount")
    public int postUpdateTopicStarAmountRpc(@RequestParam("topicId")Long topicId) {
        return topicRelatedServiceRpc.postUpdateTopicStarAmount(topicId);
    }

    /**
     * 更改著述点踩数量
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/update/topic/tread/amount")
    @PostMapping("/rpc/post/update/topic/tread/amount")
    public int postUpdateTopicTreadAmountRpc(@RequestParam("topicId")Long topicId) {
        return topicRelatedServiceRpc.postUpdateTopicTreadAmount(topicId);
    }

    /**
     * 搜索指定著述
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param entryIds
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    @ApiOperation("/rpc/get/select/search/multi/topicInfo")
    @GetMapping("/rpc/get/select/search/multi/topicInfo")
    public List<TopicModel> getSelectSearchMultiTopicInfoRpc(
            @RequestParam("status") Integer status,
            @RequestParam("entryIds") List<Long> entryIds,
            @RequestParam("content") String content,
            @RequestParam("startTime") Long startTime,
            @RequestParam("endTime") Long endTime,
            @RequestParam("page") Long page
    ) {
        return topicRelatedServiceRpc.getSelectSearchMultiTopicInfo(
                status,
                entryIds,
                content,
                startTime,
                endTime,
                page);
    }

    /**
     * 获取搜索条数
     * @param status
     * @param entryIds
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    @ApiOperation("/rpc/get/select/search/multi/topicInfo/page")
    @GetMapping("/rpc/get/select/search/multi/topicInfo/page")
    public Long getSelectSearchMultiTopicInfoPageRpc(
            @RequestParam("status") Integer status,
            @RequestParam("entryIds") List<Long> entryIds,
            @RequestParam("content") String content,
            @RequestParam("startTime") Long startTime,
            @RequestParam("endTime") Long endTime,
            @RequestParam("page") Long page
    ) {
        return topicRelatedServiceRpc.getSelectSearchMultiTopicInfoPage(
                status,
                entryIds,
                content,
                startTime,
                endTime,
                page);
    }

    /**
     * 搜索指定著述
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param content
     * @return
     */
    @ApiOperation("/rpc/get/select/search/multi/by/content/topicInfo")
    @GetMapping("/rpc/get/select/search/multi/by/content/topicInfo")
    public List<TopicModel> getSelectSearchMultiTopicInfoByContentRpc(
            @RequestParam("status") Integer status,
            @RequestParam("content") String content,
            @RequestParam("page") Long page
    ) {
        return topicRelatedServiceRpc.getSelectSearchMultiTopicInfoByContent(status,content,page);
    }

    /**
     * 指定著述数量
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param content
     * @return
     */
    @ApiOperation("/rpc/get/select/search/multi/topicInfo/by/content/page")
    @GetMapping("/rpc/get/select/search/multi/topicInfo/by/content/page")
    public Long getSelectSearchMultiTopicInfoByContentPageRpc(
            @RequestParam("status") Integer status,
            @RequestParam("content") String content
    ) {
        return topicRelatedServiceRpc.getSelectSearchMultiTopicInfoByContentPage(status,content);
    }

    /**
     * 限定搜索
     * @param status://0:不可出售，可以使用,1:待出售，不可使用,2:正在拍卖,3:可以出售，可以使用
     * @param entryId
     * @param content
     * @param page
     * @return
     */
    @ApiOperation("/rpc/get/select/search/multi/by/content/and/entryId/topicInfo")
    @GetMapping("/rpc/get/select/search/multi/by/content/and/entryId/topicInfo")
    public List<TopicModel> getSelectSearchMultiTopicInfoByContentAndEntryIdRpc(
            @RequestParam("status") Integer status,
            @RequestParam("entryId") Long entryId,
            @RequestParam("content") String content,
            @RequestParam("page") Long page
    ) {
        return topicRelatedServiceRpc.getSelectSearchMultiTopicInfoByContentAndEntryId(
                status,
                entryId,
                content,
                page
        );
    }

    /**
     * 根据topicIds组查entryIds
     * @param topicIds
     * @return
     */
    @ApiOperation("/rpc/get/select/entryIds/by/topicIds")
    @GetMapping("/rpc/get/select/entryIds/by/topicIds")
    public List<Long> getSelectEntryIdsByTopicIdsRpc(@RequestParam("topicIds") List<Long> topicIds) {
        return topicRelatedServiceRpc.getSelectEntryIdsByTopicIds(topicIds);
    }

    /**
     * 根据entryIds获取著述数量
     * @param entryIds
     * @return
     */
    @ApiOperation("/rpc/get/select/entry/amount/by/entryIds")
    @GetMapping("/rpc/get/select/entry/amount/by/entryIds")
    public Map<Long,Long> getSelectEntryAmountByEntryIdsRpc(@RequestParam("entryIds") List<Long> entryIds) {
        return topicRelatedServiceRpc.getSelectEntryAmountByEntryIds(entryIds);
    }

    /**
     * 更改用topic拥有者,用于购买后使用时使用
     * @param userId
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/update/single/topicInfo/with/owner")
    @PostMapping("/rpc/post/update/single/topicInfo/with/owner")
    public int postUpdateSingleTopicInfoWithOwnerRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("topicId") Long topicId
    ) {
        return topicRelatedServiceRpc.postUpdateSingleTopicInfoWithOwner(userId,topicId);
    }

    /**
     * 获取用户最新几条著述信息
     * @param userId
     * @param number
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/lastTopicInfo")
    @PostMapping("/rpc/post/select/multi/lastTopicInfo")
    public List<TopicModel> postSelectMultiLastTopicInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("number")Integer number
    ) {
        return topicRelatedServiceRpc.postSelectMultiLastTopicInfo(userId,number);
    }

    /**
     * 获取著述管理者ids
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/select/all/managerUserInfo")
    @PostMapping("/rpc/post/select/all/managerUserInfo")
    public List<Long> postSelectAllManagerUserIdsRpc(
            @RequestParam("topicId")Long topicId
    ) {
        return topicRelatedServiceRpc.postSelectAllManagerUserIds(topicId);
    }

    /**
     * 判断topicId是否存在
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/isExist/for/topicInfo")
    @PostMapping("/rpc/post/isExist/for/topicInfo")
    public int postIsExistForTopicInfoRpc(
            @RequestParam("topicId") Long topicId
    ) {
        return topicRelatedServiceRpc.postIsExistForTopicInfo(topicId);
    }


    /**
     * 获取用户发布了的著述数量
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/get/select/topicAmount/by/userId")
    @GetMapping("/rpc/get/select/topicAmount/by/userId")
    public Long getSelectTopicAmountByUserIdRpc(@RequestParam("userId")Long userId) {
        return topicRelatedServiceRpc.getSelectTopicAmountByUserId(userId);
    }

    /**
     * 获取点赞排行总数量
     *
     * @return
     */
    @ApiOperation("/rpc/get/select/all/topic/by/star/rank/page/amount")
    @GetMapping("/rpc/get/select/all/topic/by/star/rank/page/amount")
    public Long getSelectMultiTopicIdByStarRankPageAmountRpc() {
        return topicRelatedServiceRpc.getSelectMultiTopicIdByStarRankPageAmount();
    }

    /**
     * 创建赞踩表
     * @param topicId
     * @return
     */
    @ApiOperation("/rpc/post/create/topic/likedAndTread")
    @PostMapping("/rpc/post/create/topic/likedAndTread")
    public int postCreateTopicLikedAndTreadRpc(
            @RequestParam("topicId")Long topicId) {
        return topicRelatedServiceRpc.postCreateTopicLikedAndTread(topicId);
    }

    /**
     * 点赞排行模糊搜索
     * @param page
     * @param blurry
     * @param entry
     * @param startTime
     * @param endTime
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/blurry/search")
    @GetMapping("/rpc/get/select/multi/blurry/search")
    public List<TopicModel> getSelectMultiBlurrySearchRpc(
            @RequestParam("page")Long page,
            @RequestParam("blurry")String blurry,
            @RequestParam("entry")Long entry,
            @RequestParam("startTime")Long startTime,
            @RequestParam("endTime")Long endTime
    ) {
        return topicRelatedServiceRpc.getSelectMultiBlurrySearchRpc(page,blurry,entry,startTime,endTime);
    }
    /**
     * 点赞排行模糊搜索数据数量
     * @param page
     * @param blurry
     * @param entry
     * @param startTime
     * @param endTime
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/blurry/search/page")
    @GetMapping("/rpc/get/select/multi/blurry/search/page")
    public Long getSelectMultiBlurrySearchPageAmountRpc(
            @RequestParam("page")Long page,
            @RequestParam("blurry")String blurry,
            @RequestParam("entry")Long entry,
            @RequestParam("startTime")Long startTime,
            @RequestParam("endTime")Long endTime
    ) {
        return topicRelatedServiceRpc.getSelectMultiBlurrySearchPageAmountRpc(page,blurry,entry,startTime,endTime);

    }


    /**
     * 获取词条下所有topic数量
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/get/select/all/topic/page/amount/by/entryId")
    @GetMapping("/rpc/get/select/all/topic/page/amount/by/entryId")
    public Long getSelectAllTopicInfoPageAmountByEntryIdRpc(
            @RequestParam("entryId")Long entryId
    ) {
        return topicRelatedServiceRpc.getSelectAllTopicInfoPageAmountByEntryId(entryId);
    }

    /**
     * 随机获取正在拍卖的topic
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/get/select/random/multi/topicIds/by/entryId/for/auction")
    @GetMapping("/rpc/get/select/random/multi/topicIds/by/entryId/for/auction")
    public List<Long> getSelectRandomMultiTopicIdsByEntryIdForAuctionInfoRpc(
            @RequestParam("entryId")Long entryId
    ) {
        return topicRelatedServiceRpc.getSelectRandomMultiTopicIdsByEntryIdForAuctionInfo(entryId);
    }
}





