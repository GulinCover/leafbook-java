package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.topicEntryPage.EntryInfoResp;
import org.leafbook.api.respAbs.topicEntryPage.TopicInfosResp;
import org.leafbook.serviceapi.serviceApi.TopicEntryPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("TopicEntryPageControllerApi")
@RestController
public class TopicEntryPageControllerApi {
    @Autowired
    private TopicEntryPageServiceApi topicEntryPageServiceApi;

    //获取词条信息
    @ApiOperation("/api/select/entryInfo/{id}")
    @GetMapping("/api/select/entryInfo/{id}")
    public EntryInfoResp getSelectEntryInfoApi(@PathVariable("id")Long entryId) {
        EntryInfoResp resp = topicEntryPageServiceApi.getSelectEntryInfo(entryId);
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //获取著述信息
    @ApiOperation("/api/select/topicInfos")
    @GetMapping("/api/select/topicInfos")
    public TopicInfosResp getSelectTopicInfosApi() {
        TopicInfosResp resp = new TopicInfosResp();
        resp.setTopicDetailAbsList(topicEntryPageServiceApi.getSelectTopicInfos());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }
}
