package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.LinkedMap;
import org.leafbook.api.respAbs.marketplacePage.*;
import org.leafbook.serviceapi.serviceApi.MarketplacePageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * @return
     */
    @ApiOperation("/api/get/select/entryType")
    @GetMapping("/api/get/select/entryType")
    public TypeResp getSelectSellTypeApi() {
        TypeResp resp = new TypeResp();

        resp.setTypeList(marketplacePageServiceApi.getSelectSellType());
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取板块类型(#只显示官方)
     * @return
     */
    @ApiOperation("/api/get/select/official/entryInfos")
    @GetMapping("/api/get/select/official/entryInfos")
    public EntryInfosResp getSelectOfficialEntryInfosApi() {
        EntryInfosResp resp = new EntryInfosResp();

        resp.setEntryAbsList(marketplacePageServiceApi.getSelectOfficialEntryInfos());
        resp.setCode(200);
        return resp;
    }

    /**
     * 随机推荐4条官方词条信息
     * @return
     */
    @ApiOperation("/api/get/select/four/official/entryInfos")
    @GetMapping("/api/get/select/four/official/entryInfos")
    public EntryInfosResp getSelectRandomFourOfficialEntryInfosApi() {
        EntryInfosResp resp = new EntryInfosResp();

        resp.setEntryAbsList(marketplacePageServiceApi.getSelectRandomFourOfficialEntryInfos());
        resp.setCode(200);
        return resp;
    }

    /**
     * 随机获取5-10条热门拍卖品信息
     * @return
     */
    @ApiOperation("/api/get/select/hot/articleInfos")
    @GetMapping("/api/get/select/hot/articleInfos")
    public ArticleInfosResp getSelectRandomPopularArticleInfosApi() {
        ArticleInfosResp resp = new ArticleInfosResp();

        resp.setArticleInfoAbsList(marketplacePageServiceApi.getSelectRandomPopularArticleInfos());
        resp.setCode(200);
        return resp;
    }

    /**
     * 随机获取最新5-10条拍卖品信息
     * @return
     */
    @ApiOperation("/api/get/select/recent/articleInfos")
    @GetMapping("/api/get/select/recent/articleInfos")
    public ArticleInfosResp getSelectRandomRecentEntryInfosApi() {
        ArticleInfosResp resp = new ArticleInfosResp();

        resp.setArticleInfoAbsList(marketplacePageServiceApi.getSelectRandomRecentEntryInfos());
        resp.setCode(200);
        return resp;
    }

    /**
     * 按词条随机获取拍卖品信息
     * @return
     */
    @ApiOperation("/api/get/select/entry/articleInfos")
    @GetMapping("/api/get/select/entry/articleInfos")
    public EntryArticleInfosResp getSelectRandomArticleInfosByEntryInfosApi() {
        EntryArticleInfosResp resp = new EntryArticleInfosResp();

        resp.setArticleInfosAbsList(marketplacePageServiceApi.getSelectRandomArticleInfosByEntryInfos());
        resp.setCode(200);
        return resp;
    }


    /*
    entry_name:词条名
    entry:词条类型all,hot,official,custom
    type:拍卖品类型all,topic,nickname
    public_time:desc按时间查找,以~分割
    blurry:模糊搜索
     */
    @ApiOperation("/api/get/select/search/entry/articleInfos")
    @GetMapping("/api/get/select/search/entry/articleInfos")
    public SearchArticleInfosResp getSelectArticleInfosBySearchApi(
            @RequestParam("entry_name") String entryName,
            @RequestParam("entry") String entry,
            @RequestParam("type") String type,
            @RequestParam("public_time") String publicTime,
            @RequestParam("blurry") String content,
            @RequestParam("page") Integer page
    ) {
        SearchArticleInfosResp resp = new SearchArticleInfosResp();
        if (!entryName.isEmpty()) {
            resp.setArticleInfosAbsList(marketplacePageServiceApi.getSelectArticleInfosBySearch());
            resp.setMaxPage(40);
            resp.setCode(200);
        } else {
            resp.setMaxPage(50);
            resp.setArticleInfosAbsList(marketplacePageServiceApi.getSelectArticleInfosBySearch());
            resp.setCode(200);
        }
        return resp;
    }

}












