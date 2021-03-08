package org.leafbook.serviceapi.ControllerApi.userManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.UserInfoResp;
import org.leafbook.api.respAbs.userManagerPage.UserLoginInfoResp;
import org.leafbook.serviceapi.serviceApi.userManager.SecurityPageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("SecurityPageControllerApi")
@RestController
public class SecurityPageControllerApi {
    @Autowired
    private SecurityPageServiceApi securityPageServiceApi;

    //更改密码
    @ApiOperation("/api/post/update/password")
    @PostMapping("/api/post/update/password")
    public String postUpdatePasswordApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, String> form) {
        return HttpStatus.OK.toString();
    }
    //更改email
    @ApiOperation("/api/post/update/email")
    @PostMapping("/api/post/update/email")
    public String postUpdateEmailApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, String> form) {
        return HttpStatus.OK.toString();
    }
    //更改phone
    @ApiOperation("/api/post/update/phone")
    @PostMapping("/api/post/update/phone")
    public String postUpdatePhoneApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, String> form) {
        return HttpStatus.OK.toString();
    }

    //剔除某项登录信息
    /*
    login_id:要剔除的登录信息id
     */
    @ApiOperation("/api/post/delete/userLogin")
    @PostMapping("/api/post/delete/userLogin")
    public String postDeleteWeedOutUserLoginInfoApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, String> form) {
        return HttpStatus.OK.toString();
    }

    //获取登录信息
    @ApiOperation("/api/post/select/userLogin")
    @PostMapping("/api/post/select/userLogin")
    public UserLoginInfoResp postSelectUserLoginInfoApi(@RequestHeader("user_id")Long userId) {
        UserLoginInfoResp resp = new UserLoginInfoResp();
        resp.setUserLoginAbsList(securityPageServiceApi.postSelectUserLoginInfo());
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }
}
