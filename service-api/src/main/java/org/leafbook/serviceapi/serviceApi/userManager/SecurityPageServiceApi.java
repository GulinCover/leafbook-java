package org.leafbook.serviceapi.serviceApi.userManager;

import org.leafbook.api.modelApi.userInfo.LoginInfoModel;
import org.leafbook.api.respAbs.userManagerPage.UserLoginAbs;
import org.leafbook.api.testModel.userManagerPage.UserManagerTestModel;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.Covert2Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SecurityPageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;

    /**
     * 剔除某项登录信息
     * @param userId
     * @param form:loginId
     * @return
     */
    public int postDeleteWeedOutUserLoginInfo(Long userId, Map<String,String> form) {
        String loginId = form.get("loginId");
        if (Objects.isNull(loginId)) return 403;

        if (!Covert2Tools.isDigital(loginId)) return 403;
        int i = userServiceRpc.postSelectDetectLoginIdRpc(userId, Covert2Tools.covertToLong(loginId));
        if (i == 0) return 403;

        int ret = userServiceRpc.postDeleteSingleUserInfoLoginMarkRpc(userId, loginId);
        if (ret == 0) return 500;
        return 200;
    }

    /**
     * 获取登录信息
     * @param userId
     * @return
     */
    public List<UserLoginAbs> postSelectUserLoginInfo(Long userId) {
        List<UserLoginAbs> userLoginAbsList = new LinkedList<>();
        List<LoginInfoModel> loginInfoModelList = userServiceRpc.postSelectSingleLoginInfoRpc(userId);
        if (Objects.nonNull(loginInfoModelList) && !loginInfoModelList.isEmpty()) {
            for (LoginInfoModel loginInfoModel:loginInfoModelList) {
                UserLoginAbs userLoginAbs = new UserLoginAbs();
                userLoginAbs.setIP(loginInfoModel.getIP());
                userLoginAbs.setAddress(loginInfoModel.getLoginLocation());
                userLoginAbs.setLoginId(loginInfoModel.getLoginInfoId());

                userLoginAbsList.add(userLoginAbs);
            }
        }

        return userLoginAbsList;
    }
}
