package org.leafbook.serviceUserApi.controller;

import com.nimbusds.jose.JOSEException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.modelApi.userInfo.LoginInfoModel;
import org.leafbook.api.modelApi.userInfo.ResModel;
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
    public UserModel postSelectSingleUserInfoRpc(@RequestParam("userId") Long userId) {
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
    public List<Long> postSelectMultiAttentionUserInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("page") Long page) {
        return userRelatedServiceRpc.postSelectMultiAttentionUserInfo(userId, page);
    }

    /**
     * 获取订阅者列表
     *
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/followedUserInfo")
    @PostMapping("/rpc/post/select/multi/followedUserInfo")
    public List<Long> postSelectMultiFollowedUserInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("page") Long page) {
        return userRelatedServiceRpc.postSelectMultiFollowedUserInfo(userId, page);
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
            @RequestParam("password") String password,
            @RequestParam("ip") String ip) {
        return userRelatedServiceRpc.postCreateSingleUserInfo(username, email, password,ip);
    }

    /**
     * 用户数据更新
     *
     * @param form:userId,,userAvatar,userDesc,userLocation,userSex,backdrop
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
     * @param password
     * @return code
     */
    @ApiOperation("/rpc/post/update/single/userInfo/password/by/email")
    @PostMapping("/rpc/post/update/single/userInfo/password/by/email")
    public int postUpdateSingleUserInfoPasswordByEmailRpc(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        return userRelatedServiceRpc.postUpdateSingleUserInfoPasswordByEmail(email, password);
    }
    @ApiOperation("/rpc/post/update/single/userInfo/password/by/userId")
    @PostMapping("/rpc/post/update/single/userInfo/password/by/userId")
    int postUpdateSingleUserInfoPasswordByUserIdRpc(
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
     *
     * @param email
     * @param password
     * @return jwt
     */
    @ApiOperation("/rpc/post/login")
    @PostMapping("/rpc/post/login")
    public String postLoginRpc(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("ip") String ip
    ) throws JOSEException {
        return userRelatedServiceRpc.postLogin(email, password, ip);
    }

    /**
     * 检测用户合法性
     *
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/detect/legality/with/userId")
    @PostMapping("/rpc/post/select/detect/legality/with/userId")
    public int postSelectDetectLegalityWithUserIdRpc(@RequestParam("userId") Long userId) {
        return userRelatedServiceRpc.postSelectDetectLegalityWithUserId(userId);
    }

    /**
     * 检测登陆id合法性
     *
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/detect/LoginId")
    @PostMapping("/rpc/post/select/detect/LoginId")
    public int postSelectDetectLoginIdRpc(@RequestParam("userId") Long userId, @RequestParam("loginId") Long loginId) {
        return userRelatedServiceRpc.postSelectDetectLoginId(userId, loginId);
    }


    /**
     * 更改用户信息
     *
     * @param userModel
     * @return
     */
    @ApiOperation("/rpc/post/update/userInfo/by/userModel")
    @PostMapping("/rpc/post/update/userInfo/by/userModel")
    int postUpdateSingleUserInfoByUserInfoRpc(
            @RequestBody UserModel userModel) {
        return userRelatedServiceRpc.postUpdateSingleUserInfoByUserInfo(userModel);
    }

    /**
     * 添加关注
     *
     * @param userId
     * @param attentionUserId
     * @return
     */
    @ApiOperation("/rpc/post/add/attentionUser")
    @PostMapping("/rpc/post/add/attentionUser")
    public int postAddAttentionUserRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("attentionUserId") Long attentionUserId) {
        return userRelatedServiceRpc.postAddAttentionUser(userId, attentionUserId);
    }

    /**
     * 获取物品信息
     *
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/single/resInfo/by/userId")
    @PostMapping("/rpc/post/select/single/resInfo/by/userId")
    public ResModel postSelectSingleResInfoByUserIdRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("resId") Long resId) {
        return userRelatedServiceRpc.postSelectSingleResInfoByUserId(userId, resId);
    }

    /**
     * 查询用户所有可用物品
     *
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/user/resInfo/by/userId")
    @PostMapping("/rpc/post/select/multi/user/resInfo/by/userId")
    public List<ResModel> postSelectMultiUserResInfoByUserIdRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("page") Long page) {
        return userRelatedServiceRpc.postSelectMultiUserResInfoByUserId(userId, page);
    }

    /**
     * 查询用户所有可用物品数量
     * @param userId
     * @return
     */
    @ApiOperation("/rpc/post/select/multi/user/resInfo/amount/by/userId/page")
    @PostMapping("/rpc/post/select/multi/user/resInfo/amount/by/userId/page")
    public Long postSelectMultiUserResInfoAmountByUserIdRpc(
            @RequestParam("userId")Long userId) {
        return userRelatedServiceRpc.postSelectMultiUserResInfoAmountByUserId(userId);
    }

    /**
     * 使用nickname物品
     *
     * @param userId
     * @param resId
     * @return
     */
    @ApiOperation("rpc/post/use/single/nickname/resInfo")
    @PostMapping("rpc/post/use/single/nickname/resInfo")
    public int postUseSingleNicknameResInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("resId") Long resId) {
        return userRelatedServiceRpc.postUseSingleNicknameResInfo(userId, resId);
    }

    /**
     * 使用renameCard物品
     *
     * @param userId
     * @param resId
     * @return
     */
    @ApiOperation("rpc/post/use/single/renameCard/resInfo")
    @PostMapping("rpc/post/use/single/renameCard/resInfo")
    public int postUseSingleRenameCardResInfoRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("resId") Long resId,
            @RequestParam("newName") String newName) {
        return userRelatedServiceRpc.postUseSingleRenameCardResInfo(userId, resId, newName);
    }


    /**
     * 更新用户余额
     *
     * @param userId
     * @param balance
     * @return
     */
    @ApiOperation("rpc/post/update/single/user/balance")
    @PostMapping("rpc/post/update/single/user/balance")
    public int postUpdateSingleUserBalanceRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("balance") Long balance) {
        return userRelatedServiceRpc.postUpdateSingleUserBalanceRpc(userId, balance);
    }

    /**
     * 取消关注
     *
     * @param userId
     * @param attentionUserId
     * @return
     */
    @ApiOperation("/rpc/post/cancel/attention/user")
    @PostMapping("/rpc/post/cancel/attention/user")
    public int postCancelAttentionRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("attentionUserId") Long attentionUserId
    ) {
        return userRelatedServiceRpc.postCancelAttention(userId, attentionUserId);
    }

    /**
     * 根据uuid查询用户
     *
     * @param uuid
     * @return
     */
    @ApiOperation("/rpc/post/select/single/userInfo/by/uuid")
    @PostMapping("/rpc/post/select/single/userInfo/by/uuid")
    public UserModel postSelectSingleUserInfoByUuidRpc(@RequestParam("uuid") String uuid) {
        return userRelatedServiceRpc.postSelectSingleUserInfoByUuid(uuid);
    }

    /**
     * email查重
     * @param email
     * @return
     */
    @ApiOperation("/rpc/post/select/single/detect/is/exist/by/email")
    @PostMapping("/rpc/post/select/single/detect/is/exist/by/email")
    public int postSelectSingleDetectIsExistByEmailRpc(
            @RequestParam("email")String email
    ) {
        return userRelatedServiceRpc.postSelectSingleDetectIsExistByEmail(email);
    }


    /**
     * 删除物品
     * @param userId
     * @param resId
     * @return
     */
    @ApiOperation("/rpc/post/delete/single/userResInfo")
    @PostMapping("/rpc/post/delete/single/userResInfo")
    public int postDeleteSingleUserResInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("resId")Long resId
    ) {
        return userRelatedServiceRpc.postDeleteSingleUserResInfo(userId, resId);
    }

}
