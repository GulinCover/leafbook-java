package org.leafbook.serviceapi.ControllerApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
import org.leafbook.serviceapi.serviceApi.ForgotPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("ForgotPageControllerApi")
@RestController
public class ForgotPageControllerApi {
    @Autowired
    private ForgotPageServiceApi forgotPageServiceApi;

    //忘记密码,修改密码,发送验证码
    @ApiOperation("/api/post/select/forgot/email/send/code")
    @PostMapping("/api/post/select/forgot/email/send/code")
    public MessageResp postSelectSendEmailCodeApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        resp.setMsg("请验证您的电子邮箱");
        resp.setCode(200);
        return resp;
    }

    //忘记密码,修改密码
    /*
    new_password:
    code:
     */
    @ApiOperation("/api/post/update/forgot/password")
    @PostMapping("/api/post/update/forgot/password")
    public MessageResp postUpdatePasswordApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        resp.setMsg("修改成功");
        resp.setCode(200);
        return resp;
    }
}
