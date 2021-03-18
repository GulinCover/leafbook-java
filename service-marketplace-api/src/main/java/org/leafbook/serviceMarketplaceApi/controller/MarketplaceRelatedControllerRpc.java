package org.leafbook.serviceMarketplaceApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.billInfo.AuctionModel;
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
    @ApiOperation("/rpc/get/select/multi/auctionInfo/by/entryId/{entryId}")
    @GetMapping("/rpc/get/select/multi/auctionInfo/by/entryId/{entryId}")
    public List<AuctionModel> getSelectMultiAuctionInfoByEntryIdRpc(@PathVariable("entryId")Long entryId) {
        return marketplaceRelatedServiceRpc.getSelectMultiAuctionInfoByEntryId(entryId);
    }


}





