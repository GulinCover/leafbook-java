package org.leafbook.serviceapi.serviceApi.userManager;

import org.leafbook.api.respAbs.userManagerPage.UserLoginAbs;
import org.leafbook.api.testModel.userManagerPage.UserManagerTestModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityPageServiceApi {

    public List<UserLoginAbs> postSelectUserLoginInfo() {
        return UserManagerTestModel.createUserLoginAbsList();
    }
}
