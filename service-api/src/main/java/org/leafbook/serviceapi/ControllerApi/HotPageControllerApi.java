package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.api.respAbs.hotPage.AllTopicInfosResp;
import org.leafbook.api.respAbs.hotPage.EntryAbsListResp;
import org.leafbook.api.respAbs.hotPage.SearchTopicsResp;
import org.leafbook.serviceapi.serviceApi.HotPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("HotPageControllerApi")
@RestController
public class HotPageControllerApi {

    @Autowired
    private HotPageServiceApi hotPageServiceApi;


    /**
     * 获取点赞排行著述id,每页20条
     * @param userId
     * @param page
     * @return
     */
    @ApiOperation("/api/get/select/all/rank/topicInfos")
    @GetMapping("/api/get/select/all/rank/topicInfos")
    public AllTopicInfosResp getSelectAllRankTopicInfosApi(
            @RequestHeader("userId")Long userId,
            @RequestParam("page") Long page
    ) {
        AllTopicInfosResp resp = hotPageServiceApi.getSelectAllRankTopicInfos(userId, page);
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取所有标签
     * @return
     */
    @ApiOperation("/api/get/select/all/hot/entry")
    @GetMapping("/api/get/select/all/hot/entry")
    public EntryAbsListResp getSelectAllHotEntryListApi(
            @RequestHeader("userId")Long userId
    ) {
        EntryAbsListResp resp = new EntryAbsListResp();

        resp.setEntryAbsList(hotPageServiceApi.getSelectAllEntryListApi(userId));
        resp.setCode(200);
        return resp;
    }

    /**
     * 点赞排行模糊搜索
     * @param userId
     * @param page
     * @param blurry
     * @param entry
     * @param time
     * @return
     */
    @ApiOperation("/api/get/select/search/hot/entry/topics")
    @GetMapping("/api/get/select/search/hot/entry/topics")
    public SearchTopicsResp getSelectSearchHotEntryTopicsApi(
            @RequestHeader("userId")Long userId,
            @RequestParam("page")Long page,
            @RequestParam("blurry")String blurry,
            @RequestParam("entry")Long entry,
            @RequestParam("time")String time
            ) {
        SearchTopicsResp resp = hotPageServiceApi.getSelectSearchHotEntryTopics(userId, page,blurry,entry,time);
        resp.setCode(200);
        return resp;
    }

}
