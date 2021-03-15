package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    //获取词条信息
    @ApiOperation("/api/get/select/entryInfo/{id}")
    @GetMapping("/api/get/select/entryInfo/{id}")
    public EntryInfoResp getSelectEntryInfoApi(@PathVariable("id")Long entryId) {
        EntryInfoResp resp = topicEntryPageServiceApi.getSelectEntryInfo(entryId);
        resp.setCode(200);
        return resp;
    }

    //获取著述信息
    @ApiOperation("/api/get/select/topicInfos/{id}")
    @GetMapping("/api/get/select/topicInfos/{id}")
    public TopicInfosResp getSelectTopicInfosApi(@PathVariable("id")Long entryId) {
        TopicInfosResp resp = new TopicInfosResp();
        resp.setTopicDetailAbsList(topicEntryPageServiceApi.getSelectTopicInfos());
        resp.setCode(200);
        return resp;
    }
}
