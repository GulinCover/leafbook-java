package org.leafbook.serviceUserApi.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Arrays;

@Component
public class ShiroCredentialsMatcher extends SimpleCredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(241234254233214L);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(31,secureRandom);

        StringBuilder password = new StringBuilder();
        password.append(token.getPassword());
        return bCryptPasswordEncoder.matches(password, "$2a$31$LcXnP9OEqLjzhUt98pKiJudlVbujX.9TXQAQrvoS0M57zYILRMiHu");
    }
}
