package org.leafbook.serviceapi.ControllerApi.userManager;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.respAbs.common.MessageResp;
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

    /**
     * 更改密码
     * @param userId
     * @param form:old_password,new_password,code
     * @return 响应状态
     */
    @ApiOperation("/api/post/update/password")
    @PostMapping("/api/post/update/password")
    public MessageResp postUpdatePasswordApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        resp.setMsg("密码修改成功");
        resp.setCode(200);
        return resp;
    }
    //更改email
    @ApiOperation("/api/post/update/email")
    @PostMapping("/api/post/update/email")
    public MessageResp postUpdateEmailApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        resp.setMsg("email修改成功");
        resp.setCode(200);
        return resp;
    }
    //更改phone
    @ApiOperation("/api/post/update/phone")
    @PostMapping("/api/post/update/phone")
    public MessageResp postUpdatePhoneApi(@RequestHeader("user_id")Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();
        resp.setMsg("phone修改成功");
        resp.setCode(200);
        return resp;
    }

    /**
     * 剔除某项登录信息
     * @param userId
     * @param form:loginId
     * @return
     */
    @ApiOperation("/api/post/delete/userLogin")
    @PostMapping("/api/post/delete/userLogin")
    public MessageResp postDeleteWeedOutUserLoginInfoApi(@RequestHeader("userId")Long userId, @RequestBody Map<String, String> form) {
        MessageResp resp = new MessageResp();

        int ret = securityPageServiceApi.postDeleteWeedOutUserLoginInfo(userId,form);
        if (ret == 200) {
            resp.setCode(200);
            resp.setMsg("更新成功");
            return resp;
        } else {
            resp.setCode(ret);
            resp.setMsg("更新失败");
            return resp;
        }

    }

    /**
     * 获取登录信息
     * @param userId
     * @return
     */
    @ApiOperation("/api/post/select/userLogin")
    @PostMapping("/api/post/select/userLogin")
    public UserLoginInfoResp postSelectUserLoginInfoApi(@RequestHeader("userId")Long userId) {
        UserLoginInfoResp resp = new UserLoginInfoResp();
        resp.setUserLoginAbsList(securityPageServiceApi.postSelectUserLoginInfo(userId));
        resp.setCode(200);
        return resp;
    }
}
