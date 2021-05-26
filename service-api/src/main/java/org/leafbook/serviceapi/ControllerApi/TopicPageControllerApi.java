package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.hotPage.SearchTopicsResp;
import org.leafbook.api.respAbs.topicPage.AllEntriesResp;
import org.leafbook.api.respAbs.topicPage.RecommendedEntriesResp;
import org.leafbook.serviceapi.serviceApi.TopicPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    @ApiOperation("/api/get/select/recommended/entriesInfo")
    @GetMapping("/api/get/select/recommended/entriesInfo")
    public RecommendedEntriesResp getSelectRecommendedEntriesInfoApi(
            @RequestHeader("userId") Long userId
            ) {
        RecommendedEntriesResp resp = new RecommendedEntriesResp();

        resp.setEntryAbsList(topicPageServiceApi.getSelectRecommendedEntriesInfo(userId));
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
    @ApiOperation("/api/get/select/all/entriesInfo/page/{page}")
    @GetMapping("/api/get/select/all/entriesInfo/page/{page}")
    public AllEntriesResp getSelectAllEntriesInfoApi(
            @RequestHeader("userId") Long userId,
            @PathVariable("page") Long page) {
        AllEntriesResp resp = topicPageServiceApi.getSelectAllEntriesInfo(userId, page);
        resp.setCode(200);
        return resp;
    }

    /**
     * 搜索著述
     * @param userId
     * @param form:content,entryId,page
     * @return
     */
    @ApiOperation("/api/post/select/search/multi/topicInfo")
    @PostMapping("/api/post/select/search/multi/topicInfo")
    public SearchTopicsResp postSelectSearchMultiTopicInfoApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,String> form
    ) {
        SearchTopicsResp resp = topicPageServiceApi.getSelectSearchMultiTopicInfo(userId,form);
        resp.setCode(200);
        return resp;
    }

}











