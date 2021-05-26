package org.leafbook.serviceapi.serviceApi;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.leafbook.utils.tools.VerifyTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@GlobalTransactional
@Service
public class ForgotPageServiceApi {
    @Autowired
    private CommonServiceRpc commonServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;
    /**
     * 忘记密码,修改密码,发送验证码
     * @param userId
     * @param form:email
     * @return
     */
    public int postSelectSendEmailCode(Long userId, Map<String,String> form) {
        final String email = form.get("email");
        if (!VerifyTools.EmailVerify(email)) return 4000;
        int ret = userServiceRpc.postSelectSingleDetectIsExistByEmailRpc(email);
        if (ret != 1) return 4003;
        return commonServiceRpc.postSendCodeRpc(email, 2);
    }
    /**
     * 忘记密码,修改密码
     * @param userId
     * @param form:email,newPassword,code
     * @return
     */
    public int postUpdatePassword(Long userId, Map<String,String> form) {
        final String newPassword = form.get("newPassword");
        final String email = form.get("email");
        final String code = form.get("code");
        if (!VerifyTools.PasswordVerify(newPassword)) return 4000;
        if (!VerifyTools.EmailVerify(email)) return 4000;
        if (!VerifyTools.EmailCodeVerify(code)) return 4000;

        final String retCode = commonServiceRpc.postAcquireCodeRpc(email, 2);
        if (!code.equals(retCode)) return 4005;
        commonServiceRpc.postDeleteCodeRpc(email, 2);
        return userServiceRpc.postUpdateSingleUserInfoPasswordByEmailRpc(email, newPassword) == 1 ? 200 : 500;
    }
}
