package org.leafbook.api.testModel.userManagerPage;

import org.leafbook.api.respAbs.indexPage.TopicAbs;
import org.leafbook.api.respAbs.userManagerPage.UserLoginAbs;
import org.leafbook.api.testModel.indexPage.TestModel;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class UserManagerTestModel extends TestModel {
    public static List<UserLoginAbs> createUserLoginAbsList() {
        List<UserLoginAbs> userLoginAbsList = new LinkedList<>();
        for (int i = 0; i < (new Random().nextInt(1)) + 5; ++i) {
            UserLoginAbs userLoginAbs = new UserLoginAbs();
            userLoginAbs.setIP("123.32.5.3");
            userLoginAbs.setAddress("shanghai");
            userLoginAbs.setLoginId(200L);

            userLoginAbsList.add(userLoginAbs);
        }

        return userLoginAbsList;
    }
}
