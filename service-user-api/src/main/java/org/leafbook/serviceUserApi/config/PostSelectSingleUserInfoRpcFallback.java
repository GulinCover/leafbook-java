package org.leafbook.serviceUserApi.config;

import org.leafbook.api.modelApi.userInfo.UserModel;
import org.springframework.stereotype.Service;

@Service
public class PostSelectSingleUserInfoRpcFallback {
    public static UserModel fallbackHandle(Long userId) {
        final UserModel userModel = new UserModel();
        userModel.setUuid("xxxxxxxxxxxxx");
        return userModel;
    }
}
