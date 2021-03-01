package org.leafbook.serviceapi.ControllerApi.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.UserInfoResp;
import org.leafbook.serviceapi.serviceApi.common.UserInfoServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Api("UserInfoControllerApi")
@RestController
public class UserInfoControllerApi {
    @Autowired
    private UserInfoServiceApi userInfoServiceApi;

    //获取登录用户信息
    @ApiOperation("/api/post/select/me/userInfo")
    @PostMapping("/api/post/select/me/userInfo")
    public UserInfoResp postSelectUserInfoApi(@RequestHeader("user_id")Long userId) {
        UserInfoResp resp = userInfoServiceApi.postSelectUserInfo();
        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

}
