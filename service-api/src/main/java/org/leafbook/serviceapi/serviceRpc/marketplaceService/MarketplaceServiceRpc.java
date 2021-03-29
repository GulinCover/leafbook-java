package org.leafbook.serviceapi.serviceRpc.marketplaceService;

import org.leafbook.api.modelApi.billInfo.AuctionModel;
import org.leafbook.serviceapi.openfeinFallback.marketplaceService.MarketplaceServiceRpcFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
     * 获取number条拍卖物品信息
     * @param number
     * @return
     */
    @GetMapping("/rpc/get/select/random/multi/auctionInfo")
    List<AuctionModel> getSelectRandomMultiAuctionInfoRpc(@RequestParam("number")Integer number);
}
