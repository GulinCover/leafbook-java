package org.leafbook.serviceUserApi.config.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class ShiroRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = String.valueOf(principals.getPrimaryPrincipal());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        String role = "";
//        if (username.equals("alex")) {
//            role = "admin";
//        } else {
//            role = "user";
//        }
//        Set<String> set = new HashSet<>();
//        set.add(role);

//        simpleAuthorizationInfo.setRoles(set);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        char[] password = token.getPassword();

        String username = token.getUsername();
//        if (username.equals("alex")) {
//            if (!Arrays.equals(password, "12345678".toCharArray())) {
//                throw new AccountException("密码错误");
//            }
//        } else {
//            throw new AccountException("该账户不存在");
//        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }


}
