package org.leafbook.serviceapi.serviceApi.common;

import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.common.UserInfoResp;
import org.leafbook.api.testModel.common.CommonTestModel;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.ServerError;
import java.util.Objects;

@Service
public class UserInfoServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    /**
     * 获取登录用户信息
     * @param userId
     * @return
     */
     public UserInfoResp postSelectUserInfo(Long userId) throws ServerError {
         UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
         if (Objects.isNull(userModel)) throw new ServerError("服务器异常",new Error("数据丢失"));
         UserInfoResp userInfoResp = new UserInfoResp();
         userInfoResp.setLocation(userModel.getLocation());
         userInfoResp.setSex(userModel.getSex());
         userInfoResp.setUserAvatar(userModel.getAvatar());
         userInfoResp.setUserDesc(userModel.getDesc());
         userInfoResp.setUserId(userId);
         userInfoResp.setBalance(userModel.getBalance());
         userInfoResp.setUserLevel(userModel.getLevel());
         userInfoResp.setUUID(userModel.getUuid());
         userInfoResp.setUsername(userModel.getUsername());

         return userInfoResp;
    }
}
