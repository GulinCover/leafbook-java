package org.leafbook.serviceapi.serviceApi.userManager;

import org.leafbook.api.respAbs.common.UserInfoResp;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProfilePageServiceApi {

    public UserInfoResp postUpdateUserInfo(Map<String, String> form) {
        return new UserInfoResp();
    }
}
