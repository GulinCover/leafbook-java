package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.topicPage.AllEntriesResp;
import org.leafbook.api.respAbs.topicPage.RecommendedEntriesResp;
import org.leafbook.serviceapi.serviceApi.TopicPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Api("TopicPageControllerApi")
@RestController
public class TopicPageControllerApi {
    @Autowired
    private TopicPageServiceApi topicPageServiceApi;

    /**
     * 获取推荐词条信息,随机3条
     *
     * @return
     */
    @ApiOperation("/api/get/select/recommended/EntriesInfo")
    @GetMapping("/api/get/select/recommended/EntriesInfo")
    public RecommendedEntriesResp getSelectRecommendedEntriesInfoApi() {
        RecommendedEntriesResp resp = new RecommendedEntriesResp();

        resp.setEntryAbsList(topicPageServiceApi.getSelectRecommendedEntriesInfo());
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取全部词条信息
     *
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation("/api/get/select/all/EntriesInfo/page/{page}")
    @GetMapping("/api/get/select/all/EntriesInfo/page/{page}")
    public AllEntriesResp getSelectAllEntriesInfoApi(
            @RequestHeader(value = "userId") Long userId,
            @PathVariable("page") Long page) {
        AllEntriesResp resp = new AllEntriesResp();

        resp.setEntryAbsList(topicPageServiceApi.getSelectAllEntriesInfo(userId, page));
        resp.setCode(200);
        return resp;
    }

}











