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
     * @param form:oldPassword,newPassword,code
     * @return 响应状态
     */
    @ApiOperation("/api/post/update/password")
    @PostMapping("/api/post/update/password")
    public MessageResp postUpdatePasswordApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String, String> form) {
        int ret = securityPageServiceApi.postUpdatePassword(userId,form);
        MessageResp resp = new MessageResp();
        if (ret == 1) {
            resp.setMsg("密码修改成功");
            resp.setCode(200);
        } else {
            resp.setMsg("密码修改失败");
            resp.setCode(ret);
        }
        return resp;
    }
    /**
     * 更改email
     * @param userId
     * @param form:oldEmail,newEmail,code1,code2
     * @return
     */
    @ApiOperation("/api/post/update/email")
    @PostMapping("/api/post/update/email")
    public MessageResp postUpdateEmailApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String, String> form) {
        int ret = securityPageServiceApi.postUpdateEmail(userId,form);
        MessageResp resp = new MessageResp();
        if (ret == 1) {
            resp.setMsg("email修改成功");
            resp.setCode(200);
        } else {
            resp.setMsg("email修改失败");
            resp.setCode(ret);
        }
        return resp;
    }

    /**
     * 检查email合法性
     * @param userId
     * @param form:email
     * @return
     */
    @ApiOperation("/api/post/select/detect/is/exist/email/by/userId")
    @PostMapping("/api/post/select/detect/is/exist/email/by/userId")
    public MessageResp postSelectDetectIsExistEmailByUserIdApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String, String> form
    ) {
        int ret = securityPageServiceApi.postSelectDetectIsExistEmailByUserId(userId,form);
        MessageResp resp = new MessageResp();
        resp.setCode(ret);
        return resp;
    }

    /**
     * email查重
     * @param userId
     * @param form:email
     * @return
     */
    @ApiOperation("/api/post/select/detect/is/exist/email")
    @PostMapping("/api/post/select/detect/is/exist/email")
    public MessageResp postSelectDetectIsExistEmailApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String, String> form
    ) {
        int ret = securityPageServiceApi.postSelectDetectIsExistEmail(userId,form);
        MessageResp resp = new MessageResp();
        resp.setCode(ret);
        return resp;
    }

    /**
     * 更改phone
     * @param userId
     * @param form:oldPhone,newPhone,code
     * @return
     */
    @ApiOperation("/api/post/update/phone")
    @PostMapping("/api/post/update/phone")
    public MessageResp postUpdatePhoneApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String, String> form) {
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
    public MessageResp postDeleteWeedOutUserLoginInfoApi(
            @RequestHeader("userId")Long userId,
            @RequestBody Map<String, String> form) {
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
    public UserLoginInfoResp postSelectUserLoginInfoApi(
            @RequestHeader("userId")Long userId) {
        UserLoginInfoResp resp = new UserLoginInfoResp();
        resp.setUserLoginAbsList(securityPageServiceApi.postSelectUserLoginInfo(userId));
        resp.setCode(200);
        return resp;
    }
}
