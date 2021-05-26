package org.leafbook.serviceapi.ControllerApi.userManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
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

    /**
     * 修改用户信息,图片url;单独修改
     * @param userId
     * @param form:userAvatar,userDesc,userLocation,userSex,backdrop
     * @return
     */
    @ApiOperation("/api/update/userInfo")
    @PostMapping("/api/update/userInfo")
    public MessageResp postUpdateUserInfoApi(@RequestHeader("userId")Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        int ret = profilePageServiceApi.postUpdateUserInfo(userId,form);
        if (ret == 1) {
            resp.setCode(200);
            resp.setMsg("修改成功");
            return resp;
        } else {
            resp.setCode(ret);
            resp.setMsg("修改失败");
            return resp;
        }

    }

}











