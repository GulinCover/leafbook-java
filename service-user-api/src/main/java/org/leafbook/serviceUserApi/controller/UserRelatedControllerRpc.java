package org.leafbook.serviceUserApi.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.leafbook.api.custom.HttpStatus;
import org.leafbook.api.modelApi.UserModel;
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
     * @param username
     * @param email
     * @param password
     * @return userId
     */
    @ApiOperation("/rpc/post/create/single/userInfo")
    @PostMapping("/rpc/post/create/single/userInfo")
    public Long postCreateSingleUserInfoRpc(String username,String email,String password) {
        return userRelatedServiceRpc.postCreateSingleUserInfo(username, email, password);
    }

    /**
     * 用户数据更新
     * @param form
     * form: desc,location,sex,avatar,backdrop
     * @return code
     */
    @ApiOperation("/rpc/post/update/single/userInfo")
    @PostMapping("/rpc/post/update/single/userInfo")
    public int postUpdateSingleUserInfoRpc(@RequestBody Map<String, String> form) {
        return userRelatedServiceRpc.postUpdateSingleUserInfo(form);
    }

    /**
     * 变更昵称
     * @param userId
     * @param username
     * @return code
     */
    @ApiOperation("/rpc/post/update/single/userInfo/username")
    @PostMapping("/rpc/post/update/single/userInfo/username")
    public int postUpdateSingleUserInfoUsernameRpc(Long userId, String username) {
        return userRelatedServiceRpc.postUpdateSingleUserInfoUsername(userId,username);
    }
}
