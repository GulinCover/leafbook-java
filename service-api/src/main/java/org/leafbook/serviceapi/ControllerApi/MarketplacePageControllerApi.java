package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.marketplacePage.ArticleInfosResp;
import org.leafbook.api.respAbs.marketplacePage.EntryArticleInfosResp;
import org.leafbook.api.respAbs.marketplacePage.EntryInfosResp;
import org.leafbook.api.respAbs.marketplacePage.TypeResp;
import org.leafbook.serviceapi.serviceApi.MarketplacePageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@CrossOrigin("*")
@Api("MarketplacePageControllerApi")
@RestController
public class MarketplacePageControllerApi {
    @Autowired
    private MarketplacePageServiceApi marketplacePageServiceApi;

    //获取售卖类型
    @ApiOperation("/api/get/select/entryType")
    @GetMapping("/api/get/select/entryType")
    public TypeResp getSelectSellTypeApi() {
        TypeResp resp = new TypeResp();

        resp.setTypeList(marketplacePageServiceApi.getSelectSellType());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //获取板块类型(#只显示官方)
    @ApiOperation("/api/get/select/official/entryInfos")
    @GetMapping("/api/get/select/official/entryInfos")
    public EntryInfosResp getSelectOfficialEntryInfosApi() {
        EntryInfosResp resp = new EntryInfosResp();

        resp.setEntryAbsList(marketplacePageServiceApi.getSelectOfficialEntryInfos());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //随机推荐4条官方词条信息
    @ApiOperation("/api/get/select/four/official/entryInfos")
    @GetMapping("/api/get/select/four/official/entryInfos")
    public EntryInfosResp getSelectRandomFourOfficialEntryInfosApi() {
        EntryInfosResp resp = new EntryInfosResp();

        resp.setEntryAbsList(marketplacePageServiceApi.getSelectRandomFourOfficialEntryInfos());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //随机获取5-10条热门拍卖品信息
    @ApiOperation("/api/get/select/hot/articleInfos")
    @GetMapping("/api/get/select/hot/articleInfos")
    public ArticleInfosResp getSelectRandomPopularArticleInfosApi() {
        ArticleInfosResp resp = new ArticleInfosResp();

        resp.setArticleInfoAbsList(marketplacePageServiceApi.getSelectRandomPopularArticleInfos());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //随机获取最新5-10条拍卖品信息
    @ApiOperation("/api/get/select/recent/articleInfos")
    @GetMapping("/api/get/select/recent/articleInfos")
    public ArticleInfosResp getSelectRandomRecentEntryInfosApi() {
        ArticleInfosResp resp = new ArticleInfosResp();

        resp.setArticleInfoAbsList(marketplacePageServiceApi.getSelectRandomPopularArticleInfos());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //按词条随机获取拍卖品信息
    @ApiOperation("/api/get/select/entry/articleInfos")
    @GetMapping("/api/get/select/entry/articleInfos")
    public EntryArticleInfosResp getSelectRandomArticleInfosByEntryInfosApi() {
        EntryArticleInfosResp resp = new EntryArticleInfosResp();

        resp.setArticleInfosAbsList(marketplacePageServiceApi.getSelectRandomArticleInfosByEntryInfos());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }


    /*
    entry_name:词条名
    entry:词条类型all,hot,official,custom
    type:拍卖品类型all,topic,nickname
    hot:热门关键词
    public_time:desc按时间查找从新到旧
    blurry:模糊搜索
     */
    @ApiOperation("/api/get/select/search/entry/articleInfos")
    @GetMapping("/api/get/select/search/entry/articleInfos")
    public void getSelectArticleInfosBySearchApi(
            @RequestParam("entry_name")String entryName,
            @RequestParam("entry")String entry,
            @RequestParam("type")String type,
            @RequestParam("hot")String hot,
            @RequestParam("recent")String recent,
            @RequestParam("public_time") Date publicTime,
            @RequestParam("blurry")String content,
            @RequestParam("page")Integer page
            ) {
        if (!entryName.isEmpty()) {
            //按照词条名称查询
            if (!entry.isEmpty()) {
                //按照词条名称 和 词条类型查询
            } else {
                //按照词条名称查询全部
            }
        } else if (!hot.isEmpty()) {
            //查询热门词条
        } else if (!recent.isEmpty()) {
            if (!publicTime.toString().isEmpty()) {
                //按照时间查询最近上架的全部
            }
            //查询最近上架
        } else {
            //模糊查询
        }
    }

}












