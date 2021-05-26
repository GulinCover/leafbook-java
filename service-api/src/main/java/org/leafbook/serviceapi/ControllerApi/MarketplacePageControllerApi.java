package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.LinkedMap;
import org.leafbook.api.respAbs.marketplacePage.*;
import org.leafbook.serviceapi.serviceApi.MarketplacePageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin("*")
@Api("MarketplacePageControllerApi")
@RestController
public class MarketplacePageControllerApi {
    @Autowired
    private MarketplacePageServiceApi marketplacePageServiceApi;

    /**
     * 获取售卖类型
     * @param userId
     * @return
     */
    @ApiOperation("/api/get/select/entryType")
    @GetMapping("/api/get/select/entryType")
    public TypeResp getSelectSellTypeApi(
            @RequestHeader("userId")Long userId
    ) {
        TypeResp resp = new TypeResp();

        resp.setTypeList(marketplacePageServiceApi.getSelectSellType(userId));
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取板块类型(#只显示官方)
     * @param userId
     * @return
     */
    @ApiOperation("/api/get/select/official/entryInfos")
    @GetMapping("/api/get/select/official/entryInfos")
    public EntryInfosResp getSelectOfficialEntryInfosApi(
            @RequestHeader("userId")Long userId
    ) {
        EntryInfosResp resp = new EntryInfosResp();

        resp.setEntryAbsList(marketplacePageServiceApi.getSelectOfficialEntryInfos(userId));
        resp.setCode(200);
        return resp;
    }

    /**
     * 随机推荐4条官方词条信息
     * @param userId
     * @return
     */
    @ApiOperation("/api/get/select/four/official/entryInfos")
    @GetMapping("/api/get/select/four/official/entryInfos")
    public EntryInfosResp getSelectRandomFourOfficialEntryInfosApi(
            @RequestHeader("userId")Long userId
    ) {
        EntryInfosResp resp = new EntryInfosResp();

        resp.setEntryAbsList(marketplacePageServiceApi.getSelectRandomFourOfficialEntryInfos(userId));
        resp.setCode(200);
        return resp;
    }

    /**
     * 随机获取5-10条热门拍卖品信息
     * @param userId
     * @return
     */
    @ApiOperation("/api/get/select/hot/articleInfos")
    @GetMapping("/api/get/select/hot/articleInfos")
    public ArticleInfosResp getSelectRandomPopularArticleInfosApi(
            @RequestHeader("userId")Long userId
    ) {
        ArticleInfosResp resp = new ArticleInfosResp();

        resp.setArticleInfoAbsList(marketplacePageServiceApi.getSelectRandomPopularArticleInfos(userId));
        resp.setCode(200);
        return resp;
    }

    /**
     * 随机获取最新5-10条拍卖品信息
     * @param userId
     * @return
     */
    @ApiOperation("/api/get/select/recent/articleInfos")
    @GetMapping("/api/get/select/recent/articleInfos")
    public ArticleInfosResp getSelectRandomRecentEntryInfosApi(
            @RequestHeader("userId")Long userId
    ) {
        ArticleInfosResp resp = new ArticleInfosResp();

        resp.setArticleInfoAbsList(marketplacePageServiceApi.getSelectRandomRecentEntryInfos(userId));
        resp.setCode(200);
        return resp;
    }

    /**
     * 按词条随机获取拍卖品信息
     * @param userId
     * @return
     */
    @ApiOperation("/api/get/select/entry/articleInfos")
    @GetMapping("/api/get/select/entry/articleInfos")
    public EntryArticleInfosResp getSelectRandomArticleInfosByEntryInfosApi(
            @RequestHeader("userId")Long userId
    ) {
        EntryArticleInfosResp resp = new EntryArticleInfosResp();

        resp.setArticleInfosAbsList(marketplacePageServiceApi.getSelectRandomArticleInfosByEntryInfos(userId));
        resp.setCode(200);
        return resp;
    }

    /**
     * 搜索
     * @param userId
     * @param entryName:词条名
     * @param entry:词条类型hot,official,nonofficial
     * @param type:拍卖品类型topic,nickname
     * @param publicTime:desc按时间查找,以~分割
     * @param content:模糊搜索
     * @param page
     * @return
     */
    @ApiOperation("/api/get/select/search/entry/articleInfos")
    @GetMapping("/api/get/select/search/entry/articleInfos")
    public SearchArticleInfosResp getSelectArticleInfosBySearchApi(
            @RequestHeader("userId")Long userId,
            @RequestParam("entry_name") String entryName,
            @RequestParam("entry") String entry,
            @RequestParam("type") String type,
            @RequestParam("public_time") String publicTime,
            @RequestParam("blurry") String content,
            @RequestParam("page") Long page
    ) {
        return marketplacePageServiceApi.getSelectArticleInfosBySearch(userId, entryName,entry,type,publicTime,content,page);
    }

}












