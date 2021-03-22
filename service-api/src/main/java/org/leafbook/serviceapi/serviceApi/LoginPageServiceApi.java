package org.leafbook.serviceapi.serviceApi;

import org.leafbook.serviceapi.serviceRpc.userService.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
public class LoginPageServiceApi {
    @Autowired
    private UserServiceRpc userServiceRpc;
    /**
     * 账号登陆
     * @param form: email,password
     * @return jwt
     */
    public String postInsertUserLogin(Map<String,String> form) {
        String email = form.get("email");
        if (Objects.isNull(email)) return null;

        String password = form.get("password");
        if (Objects.isNull(password)) return null;
        return userServiceRpc.postLoginRpc(email,password);
    }
}
