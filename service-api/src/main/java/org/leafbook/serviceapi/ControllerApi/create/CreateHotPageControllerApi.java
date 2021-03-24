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

    /**
     * 创建热论词条
     * @param userId
     * @param form: entryContent,entryDesc,
     *
     * @return
     */
    @ApiOperation("/api/post/create/hot/entry")
    @PostMapping("/api/post/create/hot/entry")
    public MessageResp postCreateHotInfoApi(@RequestHeader("userId")Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        int ret = createHotPageServiceApi.postCreateHotInfo(userId,form);
        if (ret == 200) {
            resp.setMsg("创建成功");
            resp.setCode(200);
        } else {
            resp.setMsg("创建失败");
            resp.setCode(ret);
        }
        return resp;
    }
}
