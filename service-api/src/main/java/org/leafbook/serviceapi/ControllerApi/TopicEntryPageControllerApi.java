package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.annotation.ToBeOptimized;
import org.leafbook.api.respAbs.explorePage.EntryInfosResp;
import org.leafbook.api.respAbs.topicEntryPage.EntryInfoResp;
import org.leafbook.api.respAbs.topicEntryPage.TopicInfosResp;
import org.leafbook.serviceapi.serviceApi.TopicEntryPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@CrossOrigin("*")
@Api("TopicEntryPageControllerApi")
@RestController
public class TopicEntryPageControllerApi {
    @Autowired
    private TopicEntryPageServiceApi topicEntryPageServiceApi;

    /**
     * 获取词条信息
     * @param userId
     * @param entryId
     * @return
     */
    @ApiOperation("/api/get/select/entryInfo/{entryId}")
    @GetMapping("/api/get/select/entryInfo/{entryId}")
    public EntryInfoResp getSelectEntryInfoApi(
            @RequestHeader("userId")Long userId,
            @PathVariable("entryId")Long entryId
    ) {
        EntryInfoResp resp = topicEntryPageServiceApi.getSelectEntryInfo(userId, entryId);
        resp.setCode(200);
        return resp;
    }

    /**
     * 根据词条获取著述信息
     * @param userId
     * @param entryId
     * @param page: 页码
     * @return
     */
    @ApiOperation("/api/get/select/topicInfos/by/entryId/{entryId}/page/{page}")
    @GetMapping("/api/get/select/topicInfos/by/entryId/{entryId}/page/{page}")
    public TopicInfosResp getSelectTopicInfosApi(
            @RequestHeader("userId")Long userId,
            @PathVariable("entryId")Long entryId,
            @PathVariable("page")Long page) {
        TopicInfosResp resp = topicEntryPageServiceApi.getSelectTopicInfos(userId,entryId,page);
        resp.setCode(200);
        return resp;
    }

    /**
     * 随机获取相关词条
     * @param userId
     * @param number
     * @return
     */
    @ToBeOptimized
    @ApiOperation("/api/get/select/random/related/entry/{number}")
    @GetMapping("/api/get/select/random/related/entry/{number}")
    public org.leafbook.api.respAbs.topicEntryPage.EntryInfosResp getSelectRandomRelatedEntryApi(
            @RequestHeader("userId")Long userId,
            @PathVariable("number")Long number
            ) {
        org.leafbook.api.respAbs.topicEntryPage.EntryInfosResp resp = topicEntryPageServiceApi.getSelectRandomRelatedEntry(userId,number);
        resp.setCode(200);
        return resp;
    }
}
