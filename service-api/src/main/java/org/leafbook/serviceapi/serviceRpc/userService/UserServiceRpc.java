package org.leafbook.serviceapi.serviceRpc.userService;

import org.leafbook.api.modelApi.userInfo.ResModel;
import org.leafbook.api.modelApi.userInfo.LoginInfoModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.serviceapi.openfeinFallback.userService.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(
        value = "service-user-api",
        fallbackFactory = UserServiceFallback.class
)
public interface UserServiceRpc {
    /**
     * 单用户查询
     *
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/single/userInfo")
    UserModel postSelectSingleUserInfoRpc(@RequestParam("userId") Long userId);

    /**
     * 多用户查询
     *
     * @param userIds
     * @return
     */
    @PostMapping("/rpc/post/select/multi/userInfo")
    List<UserModel> postSelectMultiUserInfoRpc(@RequestBody List<Long> userIds);

    /**
     * 获取用户关注人列表
     *
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/multi/attentionUserInfo")
    List<Long> postSelectMultiAttentionUserInfoRpc(
            @RequestParam("userId") Long userId);

    /**
     * 获取订阅者列表
     *
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/multi/followedUserInfo")
    List<Long> postSelectMultiFollowedUserInfoRpc(
            @RequestParam("userId") Long userId);

    /**
     * 判断是否已关注
     *
     * @param userId
     * @param attentionId
     * @return
     */
    @PostMapping("/rpc/post/is/exist/attention")
    int postIsExistAttentionRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("attentionId") Long attentionId);

    /**
     * 创建用户
     *
     * @param username
     * @param email
     * @param password
     * @return userId
     */
    @PostMapping("/rpc/post/create/single/userInfo")
    Long postCreateSingleUserInfoRpc(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("ip") String ip
    );

    /**
     * 用户数据更新
     *
     * @param form:userId,userAvatar,userDesc,userLocation,userSex,backdrop
     * @return code
     */
    @PostMapping("/rpc/post/update/single/userInfo")
    int postUpdateSingleUserInfoRpc(@RequestBody Map<String, String> form);

    /**
     * 变更昵称
     *
     * @param userId
     * @param username
     * @return code
     */
    @PostMapping("/rpc/post/update/single/userInfo/username")
    int postUpdateSingleUserInfoUsernameRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("username") String username);

    /**
     * 手机号更改密码
     *
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/update/single/userInfo/password/by/phone")
    int postUpdateSingleUserInfoPasswordByPhoneRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("password") String password);

    /**
     * 邮箱更改密码
     *
     * @param email
     * @param password
     * @return code
     */
    @PostMapping("/rpc/post/update/single/userInfo/password/by/email")
    int postUpdateSingleUserInfoPasswordByEmailRpc(
            @RequestParam("email") String email,
            @RequestParam("password") String password);
    @PostMapping("/rpc/post/update/single/userInfo/password/by/userId")
    int postUpdateSingleUserInfoPasswordByUserIdRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("password") String password);
    /**
     * 更改手机号
     *
     * @param userId
     * @param phone
     * @return code
     */
    @PostMapping("/rpc/post/update/single/userInfo/phone")
    int postUpdateSingleUserInfoPhoneRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("phone") String phone);

    /**
     * 更改邮箱
     *
     * @param userId
     * @param email
     * @return code
     */
    @PostMapping("/rpc/post/update/single/userInfo/email")
    int postUpdateSingleUserInfoEmailRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("email") String email);

    /**
     * 查询当前账户已登录的登陆号
     *
     * @param userId
     * @return 登录信息
     */
    @PostMapping("/rpc/post/select/single/loginInfo")
    List<LoginInfoModel> postSelectSingleLoginInfoRpc(
            @RequestParam("userId") Long userId);

    /**
     * 踢出当前账户已登录的某个登陆号
     *
     * @param userId
     * @param loginMark
     * @return code
     */
    @PostMapping("/rpc/post/delete/single/userInfo/loginMark")
    int postDeleteSingleUserInfoLoginMarkRpc(
            @RequestParam("userId") Long userId,
            @RequestParam("loginMark") String loginMark);

    /**
     * 账户密码登陆
     *
     * @param email
     * @param password
     * @return jwt
     */
    @PostMapping("/rpc/post/login")
    String postLoginRpc(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("ip") String ip
    );

    /**
     * 检测用户合法性
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/detect/legality/with/userId")
    int postSelectDetectLegalityWithUserIdRpc(@RequestParam("userId")Long userId);


    /**
     * 检测登陆id合法性
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/detect/LoginId")
    int postSelectDetectLoginIdRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("loginId")Long loginId);

    /**
     * 更改用户信息
     * @param userModel
     * @return
     */
    @PostMapping("/rpc/post/update/userInfo/by/userModel")
    int postUpdateSingleUserInfoByUserInfoRpc(
            @RequestBody UserModel userModel);

    /**
     * 添加关注
     * @param userId
     * @param attentionUserId
     * @return
     */
    @PostMapping("/rpc/post/add/attentionUser")
    int postAddAttentionUserRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("attentionUserId")Long attentionUserId);

    /**
     * 获取物品信息
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/single/resInfo/by/userId")
    ResModel postSelectSingleResInfoByUserIdRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("resId")Long resId);

    /**
     * 查询用户所有可用物品
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/multi/user/resInfo/by/userId")
    List<ResModel> postSelectMultiUserResInfoByUserIdRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("page")Long page);

    /**
     * 查询用户所有可用物品数量
     * @param userId
     * @return
     */
    @PostMapping("/rpc/post/select/multi/user/resInfo/amount/by/userId/page")
    Long postSelectMultiUserResInfoAmountByUserIdRpc(
            @RequestParam("userId")Long userId);

    /**
     * 使用nickname物品
     * @param userId
     * @param resId
     * @return
     */
    @PostMapping("rpc/post/use/single/nickname/resInfo")
    int postUseSingleNicknameResInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("resId")Long resId);

    /**
     * 使用renameCard物品
     * @param userId
     * @param resId
     * @return
     */
    @PostMapping("rpc/post/use/single/renameCard/resInfo")
    int postUseSingleRenameCardResInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("resId")Long resId,
            @RequestParam("newName")String newName);

    /**
     * 更新用户余额
     * @param userId
     * @param balance
     * @return
     */
    @PostMapping("rpc/post/update/single/user/balance")
    int postUpdateSingleUserBalanceRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("balance")Long balance);

    /**
     * 取消关注
     * @param userId
     * @param attentionUserId
     * @return
     */
    @PostMapping("/rpc/post/cancel/attention/user")
    int postCancelAttentionRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("attentionUserId")Long attentionUserId
    );

    /**
     * 根据uuid查询用户
     * @param uuid
     * @return
     */
    @PostMapping("/rpc/post/select/single/userInfo/by/uuid")
    UserModel postSelectSingleUserInfoByUuidRpc(@RequestParam("uuid")String uuid);

    /**
     * email查重
     * @param email
     * @return
     */
    @PostMapping("/rpc/post/select/single/detect/is/exist/by/email")
    int postSelectSingleDetectIsExistByEmailRpc(@RequestParam("email")String email);

    /**
     * 删除物品
     * @param userId
     * @param resId
     * @return
     */
    @PostMapping("/rpc/post/delete/single/userResInfo")
    int postDeleteSingleUserResInfoRpc(
            @RequestParam("userId")Long userId,
            @RequestParam("resId")Long resId
    );
}
