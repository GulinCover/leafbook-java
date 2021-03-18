package org.leafbook.serviceUserApi.dao;

import org.leafbook.api.modelApi.userInfo.LoginInfoModel;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class LoginInfoModelMapper {

    public List<LoginInfoModel> selectLoginInfoListByLoginMarks(List<Integer> loginMarks) {
        List<LoginInfoModel> loginInfoModelList = new LinkedList<>();
        for (int i = 0;i<5;++i) {
            LoginInfoModel loginInfoModel = new LoginInfoModel();
            loginInfoModel.setIP("172.8.16.2");
            loginInfoModel.setLoginInfoId((long) new Random().nextInt(100));
            loginInfoModel.setUserId((long) new Random().nextInt(100));
            loginInfoModel.setLoginLocation("shanghai");
            loginInfoModelList.add(loginInfoModel);
        }

        return loginInfoModelList;
    }
}
