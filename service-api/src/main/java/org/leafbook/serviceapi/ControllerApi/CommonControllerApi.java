package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("CommonControllerApi")
@RestController
public class CommonControllerApi {
    //点赞
    /*
    type
    id
     */
    @ApiOperation("/api/post/insert/like")
    @PostMapping(value = "/api/post/insert/like", produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageResp postInsertLike(@RequestHeader("user_id") Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        form.put("user_id", userId.toString());
        System.out.println(form);
        resp.setCode(200);
        return resp;
    }

}
