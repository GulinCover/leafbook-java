package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.serviceapi.serviceApi.RegisterPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin("*")
@Api("RegisterPageControllerApi")
@RestController
public class RegisterPageControllerApi {
    @Autowired
    private RegisterPageServiceApi registerPageServiceApi;

    //注册账号
    /*
    username:
    email:
    password:
    code:
     */
    @ApiOperation("/api/post/create/account")
    @PostMapping("/api/post/create/account")
    public MessageResp postCreateAccountApi(@RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        resp.setMsg("注册成功");
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

    //获取注册验证码
    /*
    email:根据email返回验证码,一分钟只返回一次
     */
    @ApiOperation("/api/post/select/account/code")
    @PostMapping("/api/post/select/account/code")
    public MessageResp postSelectAccountCodeApi(@RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }
}
