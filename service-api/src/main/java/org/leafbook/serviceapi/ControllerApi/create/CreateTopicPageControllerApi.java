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

    /**
     * 创建topic
     * @param userId
     * @param form:topicTitle?String,
     *            topicDesc?String,
     *            topicEntryList?List<entryId?Long>
     * @return 返回创建的topicId
     */
    @ApiOperation("/api/post/create/topic")
    @PostMapping("/api/post/create/topic")
    public MessageResp postCreateTopicInfoApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String, Object> form) {
        MessageResp resp = new MessageResp();
        Long topicId = createTopicPageServiceApi.postCreateTopicInfo(userId,form);
        if (topicId != 0) {
            resp.setMsg(topicId.toString());
            resp.setCode(200);
        } else {
            resp.setMsg("创建失败");
            resp.setCode(500);
        }
        return resp;
    }

    /**
     * 获取官方词条
     * @return
     */
    @ApiOperation("/api/get/select/official/entry")
    @GetMapping("/api/get/select/official/entry")
    public EntryInfosResp getSelectOfficialEntryInfosApi() {
        EntryInfosResp resp = new EntryInfosResp();
        resp.setEntryAbsList(createTopicPageServiceApi.getSelectOfficialEntryInfos());
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取非官方词条
     * @return
     */
    @ApiOperation("/api/get/select/nonofficial/entry")
    @GetMapping("/api/get/select/nonofficial/entry")
    public EntryInfosResp getSelectNonofficialEntryInfosApi() {
        EntryInfosResp resp = new EntryInfosResp();
        resp.setEntryAbsList(createTopicPageServiceApi.getSelectNonofficialEntryInfos());
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取hot词条
     * @return
     */
    @ApiOperation("/api/get/select/hot/entry")
    @GetMapping("/api/get/select/hot/entry")
    public EntryInfosResp getSelectHotEntryInfosApi() {
        EntryInfosResp resp = new EntryInfosResp();
        resp.setEntryAbsList(createTopicPageServiceApi.getSelectHotEntryInfos());
        resp.setCode(200);
        return resp;
    }
}
