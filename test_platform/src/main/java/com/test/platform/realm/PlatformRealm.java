package com.test.platform.realm;

import com.test.shiro.service.AbstractShiroRealmService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class PlatformRealm extends AbstractShiroRealmService {

    @Override
    public AuthenticationInfo doAuthentication(String account, String name) {
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo();
        ByteSource source = new Md5Hash();
        return info;
    }
}
