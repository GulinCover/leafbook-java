package org.leafbook.serviceapi.ControllerApi.userManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.UserInfoResp;
import org.leafbook.serviceapi.serviceApi.userManager.ProfilePageServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin("*")
@Api("ProfilePageControllerApi")
@RestController
public class ProfilePageControllerApi {
    @Autowired
    private ProfilePageServiceApi profilePageServiceApi;

    /*
    user_avatar:图片url
    user_desc:
    user_location:
    user_sex:
     */
    @ApiOperation("/api/update/userInfo")
    @PostMapping("/api/update/userInfo")
    public UserInfoResp postUpdateUserInfoApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, String> form) {
        UserInfoResp resp = profilePageServiceApi.postUpdateUserInfo(form);

        resp.setCode(HttpStatus.OK.toString());
        return resp;
    }

}











