package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.serviceapi.serviceApi.LoginPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@CrossOrigin("*")
@Api("LoginPageControllerApi")
@RestController
public class LoginPageControllerApi {
    @Autowired
    private LoginPageServiceApi loginPageServiceApi;

    /**
     * 账号登陆
     * @param ip:
     * @param form: email,password
     * @return jwt
     */
    @ApiOperation("/api/inset/user/login")
    @PostMapping("/api/inset/user/login")
    public MessageResp postInsertUserLoginApi(
            @RequestHeader("ip")String ip,
            @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        String jwt = loginPageServiceApi.postInsertUserLogin(ip, form);
        if (Objects.isNull(jwt)) {
            resp.setMsg("登陆失败");
            resp.setCode(403);
            return resp;
        }
        resp.setMsg(jwt);
        resp.setCode(200);
        return resp;
    }
}
