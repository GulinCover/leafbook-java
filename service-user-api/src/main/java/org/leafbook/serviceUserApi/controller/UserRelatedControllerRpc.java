package org.leafbook.serviceUserApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.userInfo.LoginInfoModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.serviceUserApi.service.UserRelatedServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@Api("UserRelatedControllerRpc")
@RestController
public class UserRelatedControllerRpc {
    @Autowired
    private UserRelatedServiceRpc userRelatedServiceRpc;

    /**
     * 单用户查询
     *
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/single/userInfo")
    @PostMapping("/rpc/post/select/single/userInfo")
    public UserModel postSelectSingleUserInfoRpc(Long userId) {
        return userRelatedServiceRpc.postSelectSingleUserInfo(userId);
    }

    /**
     * 多用户查询
     *
     * @param userIds
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/userInfo")
    @PostMapping("/rpc/post/select/multi/userInfo")
    public List<UserModel> postSelectMultiUserInfoRpc(@RequestBody List<Long> userIds) {
        return userRelatedServiceRpc.postSelectMultiUserInfo(userIds);
    }

    /**
     * 创建用户
     *
     * @param username
     * @param email
     * @param password
     * @return userId
     */
    @ApiOperation("/rpc/post/create/single/userInfo")
    @PostMapping("/rpc/post/create/single/userInfo")
    public Long postCreateSingleUserInfoRpc(String username, String email, String password) {
        return userRelatedServiceRpc.postCreateSingleUserInfo(username, email, password);
    }

    /**
     * 用户数据更新
     *
     * @param form form: desc,location,sex,avatar,backdrop
     * @return code
     */
    @ApiOperation("/rpc/post/update/single/userInfo")
    @PostMapping("/rpc/post/update/single/userInfo")
    public int postUpdateSingleUserInfoRpc(@RequestBody Map<String, String> form) {
        return userRelatedServiceRpc.postUpdateSingleUserInfo(form);
    }

    /**
     * 变更昵称
     *
     * @param userId
     * @param username
     * @return code
     */
    @ApiOperation("/rpc/post/update/single/userInfo/username")
    @PostMapping("/rpc/post/update/single/userInfo/username")
    public int postUpdateSingleUserInfoUsernameRpc(Long userId, String username) {
        return userRelatedServiceRpc.postUpdateSingleUserInfoUsername(userId, username);
    }

    /**
     * 手机号更改密码
     *
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/update/single/userInfo/password/by/phone")
    @PostMapping("/rpc/post/update/single/userInfo/password/by/phone")
    public int postUpdateSingleUserInfoPasswordByPhoneRpc(Long userId, String password) {
        return userRelatedServiceRpc.postUpdateSingleUserInfoPassword(userId, password);
    }

    /**
     * 邮箱更改密码
     *
     * @param userId
     * @param password
     * @return code
     */
    @ApiOperation("/rpc/post/update/single/userInfo/password/by/email")
    @PostMapping("/rpc/post/update/single/userInfo/password/by/email")
    public int postUpdateSingleUserInfoPasswordByEmailRpc(Long userId, String password) {
        return userRelatedServiceRpc.postUpdateSingleUserInfoPassword(userId, password);
    }

    /**
     * 更改手机号
     *
     * @param userId
     * @param phone
     * @return code
     */
    @ApiOperation("/rpc/post/update/single/userInfo/phone")
    @PostMapping("/rpc/post/update/single/userInfo/phone")
    public int postUpdateSingleUserInfoPhoneRpc(Long userId, String phone) {
        return userRelatedServiceRpc.postUpdateSingleUserInfoPhone(userId, phone);
    }

    /**
     * 更改邮箱
     *
     * @param userId
     * @param email
     * @return code
     */
    @ApiOperation("/rpc/post/update/single/userInfo/email")
    @PostMapping("/rpc/post/update/single/userInfo/email")
    public int postUpdateSingleUserInfoEmailRpc(Long userId, String email) {
        return userRelatedServiceRpc.postUpdateSingleUserInfoEmail(userId, email);
    }

    /**
     * 查询当前账户已登录的登陆号
     *
     * @param userId
     * @return 登录信息
     */
    @ApiOperation("/rpc/post/select/single/loginInfo")
    @PostMapping("/rpc/post/select/single/loginInfo")
    public List<LoginInfoModel> postSelectSingleLoginInfoRpc(Long userId) {
        return userRelatedServiceRpc.postSelectSingleLoginInfo(userId);
    }

    /**
     * 踢出当前账户已登录的某个登陆号
     *
     * @param userId
     * @param loginMark
     * @return code
     */
    @ApiOperation("/rpc/post/delete/single/userInfo/loginMark")
    @PostMapping("/rpc/post/delete/single/userInfo/loginMark")
    public int postDeleteSingleUserInfoLoginMarkRpc(Long userId, String loginMark) {
        return userRelatedServiceRpc.postDeleteSingleUserInfoLoginMark(userId, loginMark);
    }

    /**
     * 发送手机验证码
     * @param userId
     * @param phone
     * @return 验证码
     */
    @ApiOperation("/rpc/post/generate/phone/code")
    @PostMapping("/rpc/post/generate/phone/code")
    public String postGeneratePhoneCodeRpc(Long userId,String phone) {
        return userRelatedServiceRpc.postGeneratePhoneCode(userId, phone);
    }

    /**
     * 发送邮箱验证码
     * @param userId
     * @param email
     * @return 验证码
     */
    @ApiOperation("/rpc/post/generate/email/code")
    @PostMapping("/rpc/post/generate/email/code")
    public String postGenerateEmailCodeRpc(Long userId,String email) {
        return userRelatedServiceRpc.postGenerateEmailCode(userId, email);
    }
}
