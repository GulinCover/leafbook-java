package org.leafbook.serviceapi.serviceRpc.marketplaceService;

import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.api.modelApi.billInfo.BidingModel;
import org.leafbook.api.modelApi.billInfo.BillModel;
import org.leafbook.serviceapi.openfeinFallback.marketplaceService.MarketplaceServiceRpcFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value = "service-marketplace-api",
        fallbackFactory = MarketplaceServiceRpcFallback.class
)
public interface MarketplaceServiceRpc {
    /**
     * 著述结算生成 ‘物品’
     * @param userId
     * @param topicId
     * @return 物品id
     */
    @PostMapping("/rpc/post/create/settlement/single/auctionInfo")
    Long postCreateSettlementSingleAuctionInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("topicId")Long topicId);

    /**
     * 根据词条id搜索正在售卖的著述
     * @param entryId
     * @return
     */
    @GetMapping("/rpc/get/select/multi/auctionInfo/by/entryId/{entryId}/{page}")
    List<AuctionModel> getSelectMultiAuctionInfoByEntryIdRpc(
            @PathVariable("entryId")Long entryId,
            @PathVariable("page")Long page);

    /**
     * 购买改名卡
     * @param userId
     * @param uuid
     * @return code
     */
    @PostMapping("/rpc/post/buy/single/renameCard")
    int postBuySingleRenameCardRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("uuid")String uuid);

    /**
     * 上架物品
     * @param userId
     * @param auctionId
     * @param price
     * @param expireTimestamp
     * @return code
     */
    @PostMapping("/rpc/post/auction/single/resInfo")
    int postAuctionSingleResInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("auctionId")Long auctionId,
            @RequestParam("price")Long price,
            @RequestParam("expireTimestamp")Long expireTimestamp);

    /**
     * 使用改名卡，并将名称存储下来
     * @param userId
     * @param auctionId
     * @param newNickname
     * @return code
     */
    @PostMapping("/rpc/post/use/single/renameCard")
    int postUseSingleRenameCardRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("auctionId")Long auctionId,
            @RequestParam("newNickname")String newNickname);

    /**
     * 查询用户下所有物品
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/multi/resInfo/by/userId")
    List<AuctionModel> postSelectMultiResInfoRpc(
            @RequestParam("userId")Long userId);

    /**
     * 查询校验
     * @param userId
     * @param auctionId
     * @return
     */
    @PostMapping("/rpc/post/select/single/resInfo/userId/and/auctionId")
    AuctionModel postSelectSingleResInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("auctionId")Long auctionId);

    /**
     * 竞拍物品
     * @param userId
     * @param auctionId
     * @param price
     * @return code
     */
    @PostMapping("/rpc/post/biding/single/auctionInfo")
    int postBidingSingleAuctionInfoRpc(
            @RequestParam("userId")Long userId, 
            @RequestParam("auctionId")Long auctionId,
            @RequestParam("price")Long price);

    /**
     * 随机获取number条拍卖物品信息
     * @param number
     * @return
     */
    @GetMapping("/rpc/get/select/random/multi/auctionInfo")
    List<AuctionModel> getSelectRandomMultiAuctionInfoRpc(@RequestParam("number")Integer number);
    /**
     * 随机获取最新number条拍卖品信息
     * @return
     */
    @GetMapping("/rpc/get/select/random/multi/latest/auctionInfo")
    List<AuctionModel> getSelectRandomMultiLatestAuctionRpc(@RequestParam("number")Integer number);

    /**
     * 获取拍卖物品信息
     * @param auctionId
     * @return
     */
    @GetMapping("/rpc/get/select/single/auctionInfo")
    AuctionModel getSelectSingleAuctionInfoRpc(@RequestParam("auctionId")Long auctionId);

    /**
     * 检测auctionId合法性
     * @param auctionId
     * @return
     */
    @PostMapping("/rpc/post/select/detect/auctionId/legality")
    int postSelectDetectAuctionIdLegalityRpc(@RequestParam("auctionId")Long auctionId);

    /**
     * 更改买拍信息当
     * @param auctionModel
     * @return
     */
    @PostMapping("/rpc/post/update/auctionInfo/by/auctionInfo")
    int postUpdateAuctionInfoByAuctionInfoRpc(@RequestBody AuctionModel auctionModel);

    /**
     * 模糊搜索昵称拍卖物品
     * @param type://0:topic,1:nickname,2:renameCard
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    @GetMapping("/rpc/get/select/search/multi/nickname/auctionInfo")
    List<AuctionModel> getSelectSearchMultiNicknameAuctionInfoRpc(
            @RequestParam("type")Integer type,
            @RequestParam("content")String content,
            @RequestParam("startTime")Long startTime,
            @RequestParam("endTime")Long endTime,
            @RequestParam("page")Long page
    );

    /**
     * 获取条数
     * @param type
     * @param content
     * @param startTime
     * @param endTime
     * @param page
     * @return
     */
    @GetMapping("/rpc/get/select/search/multi/nickname/auctionInfo/page")
    Long getSelectSearchMultiNicknameAuctionInfoPageRpc(
            @RequestParam("type")Integer type,
            @RequestParam("content")String content,
            @RequestParam("startTime")Long startTime,
            @RequestParam("endTime")Long endTime,
            @RequestParam("page")Long page
    );

    /**
     * 根据topicIds查询拍卖物品
     * @param topicIds
     * @return
     */
    @GetMapping("/rpc/get/select/multi/auctionInfo/topicIds")
    List<AuctionModel> getSelectMultiAuctionInfoByTopicIdsRpc(@RequestParam("topicIds")List<Long> topicIds);

    /**
     * 查询用户账单
     * @param userId
     * @param page
     * @return
     */
    @PostMapping("/rpc/post/select/multi/billInfo/by/userId")
    List<BillModel> postSelectMultiBillInfoByUserIdRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page);
    /**
     * 查询用户账单总条数
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/multi/billInfo/by/userId/Page")
    Long postSelectMultiBillInfoByUserIdPageRpc(
            @RequestParam("userId")Long userId);

    /**
     * 查询用户正在售卖的物品
     * @param userId
     * @param page
     * @return
     */
    @PostMapping("/rpc/post/select/multi/auctionInfo/by/userId")
    List<AuctionModel> postSelectMultiAuctionInfoByUserIdRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page);

    /**
     * 获取用户正在上架总条数
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/multi/auctionInfo/by/userId/page")
    Long postSelectMultiAuctionInfoByUserIdPageRpc(
            @RequestParam("userId")Long userId);
    /**
     * 获取用户竞拍成功的账单
     * @param userId
     * @param page
     * @return
     */
    @PostMapping("/rpc/post/select/multi/photographed/billInfo/by/userId")
    List<BillModel> postSelectMultiPhotographedBillInfoByUserIdRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page);

    /**
     * 获取用户竞拍成功的条数
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/multi/photographed/billInfo/by/userId/page")
    Long postSelectMultiPhotographedBillInfoByUserIdPageRpc(@RequestParam("userId")Long userId);

    /**
     * 获取用户竞拍失败的信息
     * @param userId
     * @param page
     * @return
     */
    @PostMapping("/rpc/post/select/multi/bidingFailedInfo")
    List<BidingModel> postSelectMultiBidingFailedInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page
    );

    /**
     * 获取用户竞拍失败的信息数量
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/multi/bidingFailedInfo/page")
    Long postSelectMultiBidingFailedInfoPageRpc(
            @RequestParam("userId")Long userId
    );

    /**
     * 获取用户正在竞拍的信息
     * @param userId
     * @param page
     * @return
     */
    @PostMapping("/rpc/post/select/multi/bidingInfo")
    List<BidingModel> postSelectMultiBidingInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page
    );
    /**
     * 获取用户正在竞拍的信息的数量
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/multi/bidingInfo/page")
    Long postSelectMultiBidingInfoPageRpc(
            @RequestParam("userId")Long userId
    );

}
