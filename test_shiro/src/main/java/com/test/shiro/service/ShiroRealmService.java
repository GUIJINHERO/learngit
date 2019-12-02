package com.test.shiro.service;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

public interface ShiroRealmService {

     AuthorizationInfo doAuthorization(PrincipalCollection principalCollection);


    AuthenticationInfo doAuthentication(String account,String name);
}
