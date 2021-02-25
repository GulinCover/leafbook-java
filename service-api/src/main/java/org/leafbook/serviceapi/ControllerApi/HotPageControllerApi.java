package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.hotPage.AllTopicInfosResp;
import org.leafbook.api.respAbs.hotPage.EntryAbs;
import org.leafbook.api.respAbs.hotPage.EntryAbsListResp;
import org.leafbook.api.respAbs.hotPage.SearchTopicsResp;
import org.leafbook.serviceapi.serviceApi.HotPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("HotPageControllerApi")
@RestController
public class HotPageControllerApi {

    @Autowired
    private HotPageServiceApi hotPageServiceApi;


    //获取所有热论,每页15条数据
    @ApiOperation("/api/get/select/all/hotTopicInfos")
    @GetMapping("/api/get/select/all/hotTopicInfos")
    public AllTopicInfosResp getSelectAllHotTopicInfosApi(@RequestParam("page")Integer page) {
        AllTopicInfosResp resp = new AllTopicInfosResp();

        resp.setTopicInfoAbsList(hotPageServiceApi.getSelectAllHotTopicInfos(page));
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //获取所有热论标签
    @ApiOperation("/api/get/select/all/hot/entry")
    @GetMapping("/api/get/select/all/hot/entry")
    public EntryAbsListResp getSelectAllHotEntryListApi() {
        EntryAbsListResp resp = new EntryAbsListResp();

        resp.setEntryAbsList(hotPageServiceApi.getSelectAllHotEntryListApi());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //搜索?page=0&blurry=&entry=&time=
    @ApiOperation("/api/get/select/search/hot/entry/topics")
    @GetMapping("/api/get/select/search/hot/entry/topics")
    public SearchTopicsResp getSelectSearchHotEntryTopicsApi(
            @RequestParam("page")Integer page,
            @RequestParam("blurry")String blurry,
            @RequestParam("entry")String entry,
            @RequestParam("time")String time
            ) {
        SearchTopicsResp resp = new SearchTopicsResp();

        resp.setTopicInfoAbsList(hotPageServiceApi.getSelectAllHotTopicInfos(page));
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

}
