package org.leafbook.serviceUserApi.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.leafbook.serviceUserApi.daoImpl.UserModelMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Objects;

@Component
public class ShiroCredentialsMatcher extends SimpleCredentialsMatcher {
    @Autowired
    private UserModelMapperImpl userModelMapper;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(241234254233214L);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(31,secureRandom);

        StringBuilder password = new StringBuilder();
        password.append(token.getPassword());
        String pwd = userModelMapper.selectSingleUserPwdByEmail(token.getUsername());
        if (Objects.isNull(pwd)) {
            return false;
        }
        return bCryptPasswordEncoder.matches(password, pwd);
    }
}
