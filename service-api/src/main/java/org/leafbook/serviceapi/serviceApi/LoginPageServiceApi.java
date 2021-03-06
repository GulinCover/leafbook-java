package org.leafbook.serviceapi.serviceApi;

import io.seata.spring.annotation.GlobalTransactional;
import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@GlobalTransactional
@Service
public class LoginPageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    /**
     * 账号登陆
     * @param ip:
     * @param form: email,password
     * @return jwt
     */
    public String postInsertUserLogin(String ip, Map<String,String> form) {
        String email = form.get("email");
        if (Objects.isNull(email)) return null;

        String password = form.get("password");
        if (Objects.isNull(password)) return null;
        String message = userServiceRpc.postLoginRpc(email, password, ip);
        String[] split = message.split("Bearer ");
        if (split.length <= 1) {
            return null;
        }
        return message;
    }
}
