package org.leafbook.serviceapi.ControllerApi.create;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.api.respAbs.createTopicPage.EntryInfosResp;
import org.leafbook.serviceapi.serviceApi.create.CreateTopicPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("TopicPageControllerApi")
@RestController
public class CreateTopicPageControllerApi {
    @Autowired
    private CreateTopicPageServiceApi createTopicPageServiceApi;

    //创建著述
    /*
    topic_title:
    topic_desc:
    topic_entry_list:[
        entry_id:
    ]
     */
    @ApiOperation("/api/post/create/topic")
    @PostMapping("/api/post/create/topic")
    public MessageResp postCreateTopicInfoApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, Object> form) {
        MessageResp resp = new MessageResp();
        resp.setMsg("312312");//返回创建的topicId
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //获取官方词条
    @ApiOperation("/api/get/select/official/entry")
    @GetMapping("/api/get/select/official/entry")
    public EntryInfosResp getSelectOfficialEntryInfosApi() {
        EntryInfosResp resp = new EntryInfosResp();
        resp.setEntryAbsList(createTopicPageServiceApi.getSelectOfficialEntryInfos());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //获取非官方词条
    @ApiOperation("/api/get/select/nonofficial/entry")
    @GetMapping("/api/get/select/nonofficial/entry")
    public EntryInfosResp getSelectNonofficialEntryInfosApi() {
        EntryInfosResp resp = new EntryInfosResp();
        resp.setEntryAbsList(createTopicPageServiceApi.getSelectNonofficialEntryInfos());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }
}
