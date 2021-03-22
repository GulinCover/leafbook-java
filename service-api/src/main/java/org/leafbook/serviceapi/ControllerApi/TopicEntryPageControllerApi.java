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

    /**
     * 获取词条信息
     * @param entryId
     * @return
     */
    @ApiOperation("/api/get/select/entryInfo/{entryId}")
    @GetMapping("/api/get/select/entryInfo/{entryId}")
    public EntryInfoResp getSelectEntryInfoApi(@PathVariable("entryId")Long entryId) {
        EntryInfoResp resp = topicEntryPageServiceApi.getSelectEntryInfo(entryId);
        resp.setCode(200);
        return resp;
    }

    /**
     * 根据词条获取著述信息
     * @param entryId
     * @param page: 页码
     * @return
     */
    @ApiOperation("/api/get/select/topicInfos/by/entryId/{entryId}/page/{page}")
    @GetMapping("/api/get/select/topicInfos/by/entryId/{entryId}/page/{page}")
    public TopicInfosResp getSelectTopicInfosApi(
            @PathVariable("entryId")Long entryId,
            @PathVariable("page")Long page) {
        TopicInfosResp resp = new TopicInfosResp();
        resp.setTopicDetailAbsList(topicEntryPageServiceApi.getSelectTopicInfos(entryId,page));
        resp.setCode(200);
        return resp;
    }
}
