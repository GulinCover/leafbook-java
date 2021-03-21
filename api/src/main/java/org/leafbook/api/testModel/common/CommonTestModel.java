package org.leafbook.api.testModel.common;

import org.leafbook.api.respAbs.common.UserInfoResp;
import org.leafbook.api.testModel.indexPage.TestModel;

public class CommonTestModel extends TestModel {
    public static UserInfoResp createUserInfo() {
        UserInfoResp userInfoResp = new UserInfoResp();
        userInfoResp.setLocation("shanghai");
        userInfoResp.setSex(1);
        userInfoResp.setUUID(randomWord());
        userInfoResp.setUserAvatar(Picture);
        userInfoResp.setUserDesc(randomWord());
        userInfoResp.setUserId(12L);
        userInfoResp.setUserLevel(1321);
        userInfoResp.setUsername(randomWord());

        return userInfoResp;
    }
}
