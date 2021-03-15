package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.serviceapi.serviceApi.LoginPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin("*")
@Api("LoginPageControllerApi")
@RestController
public class LoginPageControllerApi {
    @Autowired
    private LoginPageServiceApi loginPageServiceApi;

    @ApiOperation("/api/inset/user/login")
    @PostMapping("/api/inset/user/login")
    public MessageResp postInsertUserLogin(@RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        resp.setMsg("user.jwt");
        resp.setCode(200);
        return resp;
    }
}
