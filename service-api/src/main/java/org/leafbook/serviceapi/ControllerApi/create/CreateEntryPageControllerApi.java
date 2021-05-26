package org.leafbook.serviceapi.ControllerApi.create;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.serviceapi.serviceApi.create.CreateEntryPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("EntryPageControllerApi")
@RestController
public class CreateEntryPageControllerApi {
    @Autowired
    private CreateEntryPageServiceApi createEntryPageServiceApi;

    /**
     * 创建普通词条
     * @param userId
     * @param form: entryContent,entryDesc,
     *
     * @return
     */
    @ApiOperation("/api/post/create/entry")
    @PostMapping("/api/post/create/entry")
    public MessageResp postCreateEntryInfoApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String, String> form
    ) {
        MessageResp resp = new MessageResp();
        int ret = createEntryPageServiceApi.postCreateEntryInfo(userId,form);
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
