package org.leafbook.serviceapi.serviceApi.userManager;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.api.custom.HttpStatus;
import org.leafbook.api.modelApi.userInfo.LoginInfoModel;
import org.leafbook.api.modelApi.userInfo.UserModel;
import org.leafbook.api.respAbs.userManagerPage.UserLoginAbs;
import org.leafbook.api.testModel.userManagerPage.UserManagerTestModel;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.Covert2Tools;
import org.leafbook.utils.tools.VerifyTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@GlobalTransactional
@Service
public class SecurityPageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    @Autowired
    private CommonServiceRpc commonServiceRpc;
    /**
     * 更改密码
     * @param userId
     * @param form:oldPassword,newPassword,code
     * @return 响应状态
     */
    public int postUpdatePassword(Long userId,Map<String,String> form) {
        final String oldPassword = form.get("oldPassword");
        final String newPassword = form.get("newPassword");
        final String code = form.get("code");

        if (!VerifyTools.EmailCodeVerify(code) || !VerifyTools.PasswordVerify(oldPassword) || !VerifyTools.PasswordVerify(newPassword)) return 4000;

        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(241234254233214L);
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(31, secureRandom);
        final String oldEncodePassword = bCryptPasswordEncoder.encode(oldPassword);

        UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
        if (Objects.isNull(userModel)) return 0;
        final String password = userModel.getPassword();
        if (!password.equals(oldEncodePassword)) return 5004;


        final String emailCode = commonServiceRpc.postAcquireCodeRpc(userModel.getEmail(), 2);
        if (!code.equals(emailCode)) return 4005;

        commonServiceRpc.postDeleteCodeRpc(userModel.getEmail(), 2);
        return userServiceRpc.postUpdateSingleUserInfoPasswordByUserIdRpc(userId, newPassword);
    }


    /**
     * 更改email
     * @param userId
     * @param form:oldEmail,newEmail,code1,code2
     * @return
     */
    public int postUpdateEmail(Long userId,Map<String,String> form) {
        final String oldEmail = form.get("oldEmail");
        final String newEmail = form.get("newEmail");
        final String code1 = form.get("code1");
        final String code2 = form.get("code2");

        if (!VerifyTools.EmailVerify(oldEmail) || !VerifyTools.EmailVerify(newEmail)) return 4000;
        if (!VerifyTools.EmailCodeVerify(code1) || !VerifyTools.EmailCodeVerify(code2)) return 4000;

        int ret = userServiceRpc.postSelectSingleDetectIsExistByEmailRpc(newEmail);
        if (ret == 1) return 4002;

        UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
        if (Objects.isNull(userModel)) return 0;
        final String email = userModel.getEmail();
        if (!email.equals(oldEmail)) return 5004;


        final String oldEmailCode = commonServiceRpc.postAcquireCodeRpc(userModel.getEmail(),1);
        final String newEmailCode = commonServiceRpc.postAcquireCodeRpc(newEmail,1);
        if (!code1.equals(oldEmailCode)) return 5005;
        if (!code2.equals(newEmailCode)) return 5005;

        ret = userServiceRpc.postUpdateSingleUserInfoEmailRpc(userId, newEmail);
        commonServiceRpc.postDeleteCodeRpc(oldEmail, 1);
        commonServiceRpc.postDeleteCodeRpc(newEmail, 1);
        return ret;
    }
    /**
     * email查重
     * @param userId
     * @param form:email
     * @return
     */
    public int postSelectDetectIsExistEmail(Long userId,Map<String,String> form) {
        final String email = form.get("email");
        if (!VerifyTools.EmailVerify(email)) return 4000;
        final int ret = userServiceRpc.postSelectSingleDetectIsExistByEmailRpc(email);
        if (ret == 1) return 4002;
        return 200;
    }
    /**
     * 检查email合法性
     * @param userId
     * @param form:email
     * @return
     */
    public int postSelectDetectIsExistEmailByUserId(Long userId,Map<String,String> form) {
        final String email = form.get("email");
        if (!VerifyTools.EmailVerify(email)) return 4000;
        final UserModel userModel = userServiceRpc.postSelectSingleUserInfoRpc(userId);
        if (!email.equals(userModel.getEmail())) return 403;
        return 200;
    }


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
                userLoginAbs.setIP(loginInfoModel.getIp());
                userLoginAbs.setAddress(loginInfoModel.getLoginLocation());
                userLoginAbs.setLoginId(loginInfoModel.getLoginInfoId());

                userLoginAbsList.add(userLoginAbs);
            }
        }

        return userLoginAbsList;
    }
}
