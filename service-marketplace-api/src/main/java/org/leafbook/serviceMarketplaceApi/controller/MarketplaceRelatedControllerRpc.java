package org.leafbook.serviceMarketplaceApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.api.modelApi.billInfo.BidingModel;
import org.leafbook.api.modelApi.billInfo.BillModel;
import org.leafbook.serviceMarketplaceApi.service.MarketplaceRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Api("MarketplaceRelatedControllerRpc")
@RestController
public class MarketplaceRelatedControllerRpc {
    @Autowired
    private MarketplaceRelatedServiceRpc marketplaceRelatedServiceRpc;

    /**
     * 著述结算生成 ‘物品’
     * @param userId
     * @param topicId
     * @return 物品id
     */
    @ApiOperation("/rpc/post/create/settlement/single/auctionInfo")
    @PostMapping("/rpc/post/create/settlement/single/auctionInfo")
    public Long postCreateSettlementSingleAuctionInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("topicId")Long topicId
    ) {
        return marketplaceRelatedServiceRpc.postCreateSettlementSingleAuctionInfo(userId,topicId);
    }

    /**
     * 根据词条id搜索正在售卖的著述
     * @param entryId
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/auctionInfo/by/entryId/{entryId}/{page}")
    @GetMapping("/rpc/get/select/multi/auctionInfo/by/entryId/{entryId}/{page}")
    public List<AuctionModel> getSelectMultiAuctionInfoByEntryIdRpc(@PathVariable("entryId")Long entryId,@PathVariable("page")Integer page) {
        return marketplaceRelatedServiceRpc.getSelectMultiAuctionInfoByEntryId(entryId,page);
    }

    /**
     * 购买改名卡
     * @param userId
     * @param uuid
     * @return code
     */
    @ApiOperation("/rpc/post/buy/single/renameCard")
    @PostMapping("/rpc/post/buy/single/renameCard")
    public int postBuySingleRenameCardRpc(@RequestParam("userId")Long userId,@RequestParam("uuid")String uuid) {
        return marketplaceRelatedServiceRpc.postBuySingleRenameCard(userId,uuid);
    }

    /**
     * 上架物品
     * @param userId
     * @param auctionId
     * @param price
     * @param expireTimestamp
     * @return code
     */
    @ApiOperation("/rpc/post/auction/single/resInfo")
    @PostMapping("/rpc/post/auction/single/resInfo")
    public int postAuctionSingleResInfoRpc(@RequestParam("userId")Long userId,@RequestParam("auctionId")Long auctionId,@RequestParam("price")Long price,@RequestParam("expireTimestamp")Long expireTimestamp) {
        return marketplaceRelatedServiceRpc.postAuctionSingleResInfo(userId,auctionId,price,expireTimestamp);
    }

    /**
     * 使用改名卡，并将名称存储下来
     * @param userId
     * @param auctionId
     * @param newNickname
     * @return code
     */
    @ApiOperation("/rpc/post/use/single/renameCard")
    @PostMapping("/rpc/post/use/single/renameCard")
    public int postUseSingleRenameCardRpc(@RequestParam("userId")Long userId,@RequestParam("auctionId")Long auctionId,@RequestParam("newNickname")String newNickname) {
        return marketplaceRelatedServiceRpc.postUseSingleRenameCard(userId,auctionId,newNickname);
    }

    /**
     * 查询用户下所有物品
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/resInfo/by/userId")
    @PostMapping("/rpc/post/select/multi/resInfo/by/userId")
    public List<AuctionModel> postSelectMultiResInfoRpc(@RequestParam("userId")Long userId) {
        return marketplaceRelatedServiceRpc.postSelectMultiResInfo(userId);
    }

    /**
     * 查询校验
     * @param userId
     * @param auctionId
     * @return
     */
    @ApiOperation("/rpc/post/select/single/resInfo/userId/and/auctionId")
    @PostMapping("/rpc/post/select/single/resInfo/userId/and/auctionId")
    public AuctionModel postSelectSingleResInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("auctionId")Long auctionId) {
        return marketplaceRelatedServiceRpc.postSelectSingleResInfo(userId,auctionId);
    }

    /**
     * 竞拍物品
     * @param userId
     * @param auctionId
     * @param price
     * @return code
     */
    @ApiOperation("/rpc/post/biding/single/auctionInfo")
    @PostMapping("/rpc/post/biding/single/auctionInfo")
    public int postBidingSingleAuctionInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("auctionId")Long auctionId,
            @RequestParam("price")Long price) {
        return marketplaceRelatedServiceRpc.postBidingSingleAuctionInfo(userId,auctionId,price);
    }

    /**
     * 随机获取number条拍卖物品信息
     * @param number
     * @return
     */
    @ApiOperation("/rpc/get/select/random/multi/auctionInfo")
    @GetMapping("/rpc/get/select/random/multi/auctionInfo")
    public List<AuctionModel> getSelectRandomMultiAuctionInfoRpc(@RequestParam("number")Integer number) {
        return marketplaceRelatedServiceRpc.getSelectRandomMultiAuctionInfo(number);
    }

    /**
     * 随机获取最新number条拍卖品信息
     * @return
     */
    @ApiOperation("/rpc/get/select/random/multi/latest/auctionInfo")
    @GetMapping("/rpc/get/select/random/multi/latest/auctionInfo")
    public List<AuctionModel> getSelectRandomMultiLatestAuctionRpc(@RequestParam("number")Integer number) {
        return marketplaceRelatedServiceRpc.getSelectRandomMultiLatestAuction(number);
    }
    /**
     * 获取拍卖物品信息
     * @param auctionId
     * @return
     */
    @ApiOperation("/rpc/get/select/single/auctionInfo")
    @GetMapping("/rpc/get/select/single/auctionInfo")
    public AuctionModel getSelectSingleAuctionInfoRpc(@RequestParam("auctionId")Long auctionId) {
        return marketplaceRelatedServiceRpc.getSelectSingleAuctionInfo(auctionId);
    }

    /**
     * 检测auctionId合法性
     * @param auctionId
     * @return
     */
    @ApiOperation("/rpc/post/select/detect/auctionId/legality")
    @PostMapping("/rpc/post/select/detect/auctionId/legality")
    public int postSelectDetectAuctionIdLegalityRpc(@RequestParam("auctionId")Long auctionId) {
        return marketplaceRelatedServiceRpc.postSelectDetectAuctionIdLegality(auctionId);
    }

    /**
     * 更改买拍信息当
     * @param auctionModel
     * @return
     */
    @ApiOperation("/rpc/post/update/auctionInfo/by/auctionInfo")
    @PostMapping("/rpc/post/update/auctionInfo/by/auctionInfo")
    public int postUpdateAuctionInfoByAuctionInfoRpc(@RequestBody AuctionModel auctionModel) {
        return marketplaceRelatedServiceRpc.postUpdateAuctionInfoByAuctionInfo(auctionModel);
    }

    /**
     * 模糊搜索昵称拍卖物品
     * @param type://0:topic,1:nickname,2:renameCard
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    @ApiOperation("/rpc/get/select/search/multi/nickname/auctionInfo")
    @GetMapping("/rpc/get/select/search/multi/nickname/auctionInfo")
    public List<AuctionModel> getSelectSearchMultiNicknameAuctionInfoRpc(
            @RequestParam("type")Integer type,
            @RequestParam("content")String content,
            @RequestParam("startTime")Long startTime,
            @RequestParam("endTime")Long endTime,
            @RequestParam("page")Long page
    ) {
        return marketplaceRelatedServiceRpc.getSelectSearchMultiNicknameAuctionInfo(type,content,startTime,endTime,page);
    }

    /**
     * 获取条数
     * @param type
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    @ApiOperation("/rpc/get/select/search/multi/nickname/auctionInfo/page")
    @GetMapping("/rpc/get/select/search/multi/nickname/auctionInfo/page")
    public Long getSelectSearchMultiNicknameAuctionInfoPageRpc(
            @RequestParam("type")Integer type,
            @RequestParam("content")String content,
            @RequestParam("startTime")Long startTime,
            @RequestParam("endTime")Long endTime,
            @RequestParam("page")Long page
    ) {
        return marketplaceRelatedServiceRpc.getSelectSearchMultiNicknameAuctionInfoPage(type,content,startTime,endTime,page);
    }

    /**
     * 根据topicIds查询拍卖物品
     * @param topicIds
     * @return
     */
    @ApiOperation("/rpc/get/select/multi/auctionInfo/topicIds")
    @GetMapping("/rpc/get/select/multi/auctionInfo/topicIds")
    public List<AuctionModel> getSelectMultiAuctionInfoByTopicIdsRpc(@RequestParam("topicIds")List<Long> topicIds) {
        return marketplaceRelatedServiceRpc.getSelectMultiAuctionInfoByTopicIds(topicIds);
    }


    /**
     * 查询用户账单
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/billInfo/by/userId")
    @PostMapping("/rpc/post/select/multi/billInfo/by/userId")
    public List<BillModel> postSelectMultiBillInfoByUserIdRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page) {
        return marketplaceRelatedServiceRpc.postSelectMultiBillInfoByUserId(userId,page);
    }

    /**
     * 查询用户账单总条数
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/billInfo/by/userId/Page")
    @PostMapping("/rpc/post/select/multi/billInfo/by/userId/Page")
    public Long postSelectMultiBillInfoByUserIdPageRpc(
            @RequestParam("userId")Long userId) {
        return marketplaceRelatedServiceRpc.postSelectMultiBillInfoByUserIdPage(userId);
    }

    /**
     * 查询用户正在售卖的物品
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/auctionInfo/by/userId")
    @PostMapping("/rpc/post/select/multi/auctionInfo/by/userId")
    public List<AuctionModel> postSelectMultiAuctionInfoByUserIdRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page) {
        return marketplaceRelatedServiceRpc.postSelectMultiAuctionInfoByUserId(userId, page);
    }

    /**
     * 获取用户正在上架总条数
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/auctionInfo/by/userId/page")
    @PostMapping("/rpc/post/select/multi/auctionInfo/by/userId/page")
    public Long postSelectMultiAuctionInfoByUserIdPageRpc(
            @RequestParam("userId")Long userId) {
        return marketplaceRelatedServiceRpc.postSelectMultiAuctionInfoByUserIdPage(userId);
    }

    /**
     * 获取用户竞拍成功的账单
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/photographed/billInfo/by/userId")
    @PostMapping("/rpc/post/select/multi/photographed/billInfo/by/userId")
    public List<BillModel> postSelectMultiPhotographedBillInfoByUserIdRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page) {
        return marketplaceRelatedServiceRpc.postSelectMultiPhotographedBillInfoByUserId(userId,page);
    }

    /**
     * 获取用户竞拍成功的条数
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/photographed/billInfo/by/userId/page")
    @PostMapping("/rpc/post/select/multi/photographed/billInfo/by/userId/page")
    public Long postSelectMultiPhotographedBillInfoByUserIdPageRpc(
            @RequestParam("userId")Long userId) {
        return marketplaceRelatedServiceRpc.postSelectMultiPhotographedBillInfoByUserIdPage(userId);
    }


    /**
     * 获取用户竞拍失败的信息
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/bidingFailedInfo")
    @PostMapping("/rpc/post/select/multi/bidingFailedInfo")
    public List<BidingModel> postSelectMultiBidingFailedInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page
    ) {
        return marketplaceRelatedServiceRpc.postSelectMultiBidingFailedInfo(userId,page);
    }

    /**
     * 获取用户竞拍失败的信息数量
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/bidingFailedInfo/page")
    @PostMapping("/rpc/post/select/multi/bidingFailedInfo/page")
    public Long postSelectMultiBidingFailedInfoPageRpc(
            @RequestParam("userId")Long userId
    ) {
        return marketplaceRelatedServiceRpc.postSelectMultiBidingFailedInfoPage(userId);
    }


    /**
     * 获取用户正在竞拍的信息
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/bidingInfo")
    @PostMapping("/rpc/post/select/multi/bidingInfo")
    public List<BidingModel> postSelectMultiBidingInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page
    ) {
        return marketplaceRelatedServiceRpc.postSelectMultiBidingInfo(userId,page);
    }
    /**
     * 获取用户正在竞拍的信息的数量
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/bidingInfo/page")
    @PostMapping("/rpc/post/select/multi/bidingInfo/page")
    public Long postSelectMultiBidingInfoPageRpc(
            @RequestParam("userId")Long userId
    ) {
        return marketplaceRelatedServiceRpc.postSelectMultiBidingInfoPage(userId);
    }

}





