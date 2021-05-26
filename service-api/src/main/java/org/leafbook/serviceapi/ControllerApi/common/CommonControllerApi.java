package org.leafbook.serviceapi.ControllerApi.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.AllEntryInfosResp;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.serviceapi.serviceApi.common.CommonServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("CommonControllerApi")
@RestController
public class CommonControllerApi {
    @Autowired
    private CommonServiceApi commonServiceApi;

    /**
     * 发送验证码
     * @param userId
     * @param from:email,type
     * @return
     */
    @ApiOperation("/api/post/send/code")
    @PostMapping("/api/post/send/code")
    public MessageResp postSendCodeApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String,String> from
            ) {
        commonServiceApi.postSendCode(userId,from);
        MessageResp resp = new MessageResp();
        resp.setMsg("发送成功");
        resp.setCode(200);
        return resp;
    }

    /**
     * 获取所有entryInfo
     * @param userId
     * @return
     */
    @ApiOperation("/api/get/select/all/entryInfos")
    @GetMapping("/api/get/select/all/entryInfos")
    public AllEntryInfosResp getSelectAllEntryInfosApi(
            @RequestHeader("userId")Long userId
    ) {
        AllEntryInfosResp resp = new AllEntryInfosResp();
        resp.setEntryAbsList(commonServiceApi.getSelectAllEntryInfos(userId));
        resp.setCode(200);
        return resp;
    }
}
