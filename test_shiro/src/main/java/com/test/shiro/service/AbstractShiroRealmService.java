package com.test.shiro.service;

import com.test.shiro.dto.ShiroUser;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractShiroRealmService implements ShiroRealmService {

    @Override
    public AuthorizationInfo doAuthorization(PrincipalCollection principalCollection) {
        ShiroUser user = (ShiroUser)principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<String>(user.getRoleMap().values());
        Set<String> permissions = new HashSet<String>(user.getPermissionList());
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    public abstract AuthenticationInfo doAuthentication(String account, String name);
}
