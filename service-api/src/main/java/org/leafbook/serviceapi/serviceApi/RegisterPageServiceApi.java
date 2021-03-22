package org.leafbook.serviceapi.serviceApi;

import org.leafbook.serviceapi.serviceRpc.commonService.CommonServiceRpc;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegisterPageServiceApi {
    @Autowired
    private CommonServiceRpc commonServiceRpc;
    @Autowired
    private UserServiceRpc userServiceRpc;

    /**
     * 注册账号
     *
     * @param form username:
     *             email:
     *             password:
     *             code:验证码
     * @return
     */
    public int postCreateAccount(Map<String, String> form) {
        String email = form.get("email");
        if (Objects.isNull(email)) return 403;
        String username = form.get("username");
        if (Objects.isNull(username)) return 403;
        String password = form.get("password");
        if (Objects.isNull(password)) return 403;
        String code = form.get("code");
        if (Objects.isNull(code)) return 403;

        if (password.length() < 8 || password.length() > 16) {
            return 403;
        }
        if (username.length() < 1 || username.length() > 12) {
            return 403;
        }

        if (code.length() != 6) {
            return 403;
        }


        String pattern = "^[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.(com|cn|com\\.cn|net)$";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(email);
        if (matcher.find()) {
            String s = commonServiceRpc.postAcquireCodeRpc(email);
//            if (!form.get("code").equals(s)) return 403;

            //创建账户
            Long userId = userServiceRpc.postCreateSingleUserInfoRpc(
                    form.get("username"),
                    form.get("email"),
                    form.get("password"));
            if (!Objects.isNull(userId)) {
                if (userId != 0) {
                    return 200;
                }
            }
        } else {
            return 403;
        }

        return 403;
    }

    /**
     * 发送注册码
     *
     * @param form email:根据email返回验证码,一分钟只返回一次
     * @return
     */
    public int postSelectAccountCode(Map<String, String> form) {
        String email = form.get("email");
        if (Objects.isNull(email)) return 403;

        String pattern = "^[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.(com|cn|com\\.cn|net)$";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(email);
        if (matcher.find()) {
            return commonServiceRpc.postSendCodeRpc(email) != 0 ? 200 : 403;
        } else {
            return 403;
        }

    }
}
