package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.api.respAbs.marketplaceDetailPage.ArticleInfoResp;
import org.leafbook.serviceapi.serviceApi.MarketplaceDetailPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("MarketplaceDetailPageControllerApi")
@RestController
public class MarketplaceDetailPageControllerApi {
    @Autowired
    private MarketplaceDetailPageServiceApi marketplaceDetailPageServiceApi;

    /**
     * 获取拍卖品信息
     * @param auctionId
     * @return
     */
    @ApiOperation("/api/get/select/auctionInfo/{auctionId}")
    @GetMapping("/api/get/select/auctionInfo/{auctionId}")
    public ArticleInfoResp getSelectAuctionInfoApi(@PathVariable("auctionId")Long auctionId) {
        ArticleInfoResp resp = marketplaceDetailPageServiceApi.getSelectArticleInfo(auctionId);
        resp.setCode(200);
        return resp;
    }

    /**
     * 竞拍物品
     * @param userId
     * @param form: auctionId,price
     * @return
     */
    @ApiOperation("/api/post/insert/biding/auctionInfo")
    @PostMapping("/api/post/insert/biding/auctionInfo")
    public MessageResp postInsertBidingAuctionInfoApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String, Long> form
            ) {
        MessageResp resp = new MessageResp();
        int ret = marketplaceDetailPageServiceApi.postInsertBidingAuctionInfo(userId,form);
        if (ret == 1) {
            resp.setCode(200);
            resp.setMsg("竞拍成功");
        } else {
            resp.setCode(ret);
            resp.setMsg("竞拍失败");
        }
        return resp;
    }
}
