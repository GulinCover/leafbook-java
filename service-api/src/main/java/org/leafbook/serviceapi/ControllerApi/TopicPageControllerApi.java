package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.topicPage.AllEntriesResp;
import org.leafbook.api.respAbs.topicPage.RecommendedEntriesResp;
import org.leafbook.serviceapi.serviceApi.TopicPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("TopicPageControllerApi")
@RestController
public class TopicPageControllerApi {
    @Autowired
    private TopicPageServiceApi topicPageServiceApi;

    //获取推荐词条信息,随机3条
    @ApiOperation("/api/get/select/recommended/EntriesInfo")
    @GetMapping("/api/get/select/recommended/EntriesInfo")
    public RecommendedEntriesResp getSelectRecommendedEntriesInfoApi() {
        RecommendedEntriesResp resp = new RecommendedEntriesResp();

        resp.setEntryAbsList(topicPageServiceApi.getSelectRecommendedEntriesInfo());
        resp.setCode(200);
        return resp;
    }

    //获取全部词条信息
    @ApiOperation("/api/get/select/all/EntriesInfo")
    @GetMapping("/api/get/select/all/EntriesInfo")
    public AllEntriesResp getSelectAllEntriesInfoApi(@RequestParam("entry_page")String page) {
        AllEntriesResp resp = new AllEntriesResp();

        resp.setEntryAbsList(topicPageServiceApi.getSelectAllEntriesInfo(page));
        resp.setCode(200);
        return resp;
    }

}











