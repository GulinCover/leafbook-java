package org.leafbook.serviceapi.ControllerApi.create;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.serviceapi.serviceApi.create.CreateHotPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("HotPageControllerApi")
@RestController
public class CreateHotPageControllerApi {
    @Autowired
    private CreateHotPageServiceApi createHotPageServiceApi;

    //创建热论词条
    /*
    entry_content:
    entry_desc:
     */
    @ApiOperation("/api/post/create/hot/entry")
    @PostMapping("/api/post/create/hot/entry")
    public MessageResp postCreateHotInfoApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        resp.setMsg("创建成功");
        resp.setCode(200);
        return resp;
    }
}
