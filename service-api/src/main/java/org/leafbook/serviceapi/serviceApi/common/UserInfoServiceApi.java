package org.leafbook.serviceapi.serviceApi.common;

import org.leafbook.api.respAbs.common.UserInfoResp;
import org.leafbook.api.testModel.common.CommonTestModel;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceApi {

     public UserInfoResp postSelectUserInfo() {
        return CommonTestModel.createUserInfo();
    }
}
