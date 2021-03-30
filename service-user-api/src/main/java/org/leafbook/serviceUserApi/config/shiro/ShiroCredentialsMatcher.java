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
        String encoder = bCryptPasswordEncoder.encode(Arrays.toString(token.getPassword()));
        Object credentials = getCredentials(info);
        System.out.println(encoder);
        System.out.println(credentials);
        System.out.println(equals(encoder, credentials));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(credentials.toString());
        return bCryptPasswordEncoder.matches(stringBuilder, "$2a$31$CbNnLqEWNoL0T/lxfI0n0.4b/yzrwkmAFDnrqqPr2.pODjXYWVO/C");
    }
}