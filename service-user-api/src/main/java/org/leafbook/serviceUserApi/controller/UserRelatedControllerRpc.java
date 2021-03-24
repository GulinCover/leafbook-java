package org.leafbook.serviceUserApi.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.userInfo.LoginInfoModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.serviceUserApi.config.PostSelectSingleUserInfoRpcFallback;
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
    @SentinelResource(
            value = "postSelectSingleUserInfoRpc",
            fallbackClass = PostSelectSingleUserInfoRpcFallback.class,
            fallback = "fallbackHandle"
    )
    @ApiOperation("/rpc/post/select/single/userInfo")
    @PostMapping("/rpc/post/select/single/userInfo")
    public UserModel postSelectSingleUserInfoRpc(@RequestParam("userId")Long userId) {
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
     * 获取用户关注人列表
     *
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/attentionUserInfo")
    @PostMapping("/rpc/post/select/multi/attentionUserInfo")
    public List<Long> postSelectMultiAttentionUserInfoRpc(@RequestParam("userId") Long userId) {
        return userRelatedServiceRpc.postSelectMultiAttentionUserInfo(userId);
    }

    /**
     * 获取订阅者列表
     *
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/followedUserInfo")
    @PostMapping("/rpc/post/select/multi/followedUserInfo")
    public List<Long> postSelectMultiFollowedUserInfoRpc(@RequestParam("userId") Long userId) {
        return userRelatedServiceRpc.postSelectMultiFollowedUserInfo(userId);
    }

    /**
     * 判断是否已关注
     *
     * @param userId
     * @param attentionId
     * @return
     */
    @ApiOperation("/rpc/post/is/exist/attention")
    @PostMapping("/rpc/post/is/exist/attention")
    public int postIsExistAttentionRpc(@RequestParam("userId") Long userId, @RequestParam("attentionId") Long attentionId) {
        return userRelatedServiceRpc.postIsExistAttention(userId, attentionId);
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
    public Long postCreateSingleUserInfoRpc(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
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
    public int postUpdateSingleUserInfoUsernameRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("username") String username) {
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
    public int postUpdateSingleUserInfoPasswordByPhoneRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("password") String password) {
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
    public int postUpdateSingleUserInfoPasswordByEmailRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("password") String password) {
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
    public int postUpdateSingleUserInfoPhoneRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("phone") String phone) {
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
    public int postUpdateSingleUserInfoEmailRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("email") String email) {
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
    public List<LoginInfoModel> postSelectSingleLoginInfoRpc(
            @RequestParam("userId") Long userId) {
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
    public int postDeleteSingleUserInfoLoginMarkRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("loginMark") String loginMark) {
        return userRelatedServiceRpc.postDeleteSingleUserInfoLoginMark(userId, loginMark);
    }

    /**
     * 账户密码登陆
     * @param email
     * @param password
     * @return jwt
     */
    @ApiOperation("/rpc/post/login")
    @PostMapping("/rpc/post/login")
    public String postLoginRpc(
            @RequestParam("email")String email,
            @RequestParam("password")String password
    ) {
        return userRelatedServiceRpc.postLogin(email,password);
    }

    /**
     * 检测用户合法性
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/detect/legality/with/userId")
    @PostMapping("/rpc/post/select/detect/legality/with/userId")
    public int postSelectDetectLegalityWithUserIdRpc(@RequestParam("userId")Long userId) {
        return userRelatedServiceRpc.postSelectDetectLegalityWithUserId(userId);
    }
}
