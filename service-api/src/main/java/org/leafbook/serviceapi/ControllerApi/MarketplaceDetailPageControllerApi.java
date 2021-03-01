package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.marketplaceDetailPage.ArticleInfoResp;
import org.leafbook.api.respAbs.marketplaceDetailPage.BidingResp;
import org.leafbook.serviceapi.serviceApi.MarketplaceDetailPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@CrossOrigin("*")
@Api("MarketplaceDetailPageControllerApi")
@RestController
public class MarketplaceDetailPageControllerApi {
    @Autowired
    private MarketplaceDetailPageServiceApi marketplaceDetailPageServiceApi;

    //获取拍卖品信息
    @ApiOperation("/api/get/select/articleInfo/{id}")
    @GetMapping("/api/get/select/articleInfo/{id}")
    public ArticleInfoResp getSelectArticleInfoApi(@PathVariable("id")Long articleId) {
        ArticleInfoResp resp = marketplaceDetailPageServiceApi.getSelectArticleInfo(articleId);
        if (Objects.isNull(resp)) {
            ArticleInfoResp articleInfoResp = new ArticleInfoResp();
            articleInfoResp.setCode(HttpStatus.OK.toString());
            return articleInfoResp;
        }

        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //竞拍物品
    /*
    article_id:竞拍物品
    price:出价
     */
    @ApiOperation("/api/get/select/biding/article")
    @PostMapping(value = "/api/post/insert/biding/article",produces = MediaType.APPLICATION_JSON_VALUE)
    public BidingResp postInsertBidingArticle(
            @RequestHeader("user_id")Long userId,
            @RequestBody Map<String, String> form
            ) {
        BidingResp resp = new BidingResp();
        resp.setCode(HttpStatus.OK.toString());
        resp.setMessage("竞拍成功");
        return resp;
    }
}
