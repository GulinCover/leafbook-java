package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.serviceapi.serviceApi.RegisterPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("RegisterPageControllerApi")
@RestController
public class RegisterPageControllerApi {
    @Autowired
    private RegisterPageServiceApi registerPageServiceApi;

    /**
     * 注册账号
     *
     * @param form username:
     *             email:
     *             password:
     *             code:验证码
     * @return
     */
    @ApiOperation("/api/post/create/account")
    @PostMapping("/api/post/create/account")
    public MessageResp postCreateAccountApi(@RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();

        if (registerPageServiceApi.postCreateAccount(form) == 200) {
            resp.setMsg("注册成功");
            resp.setCode(200);
        } else {
            resp.setMsg("注册失败,请重新注册");
            resp.setCode(403);
        }

        return resp;
    }

    /**
     * 发送注册码
     *
     * @param form:根据email返回验证码,一分钟只返回一次 email:根据email返回验证码,一分钟只返回一次
     * @return
     */
    @ApiOperation("/api/post/select/account/code")
    @PostMapping("/api/post/select/account/code")
    public MessageResp postSelectAccountCodeApi(@RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        if (registerPageServiceApi.postSelectAccountCode(form) == 200) {
            resp.setMsg("发送成功");
        } else {
            resp.setMsg("发送失败");
        }
        resp.setCode(200);
        return resp;
    }
}
