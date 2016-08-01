package com.mks.authority;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;

 
@Deprecated
public class NvwaCredentialsMatcher extends SimpleCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken t = (UsernamePasswordToken) token;

        Object tokenCredentials = encrypt(String.valueOf(t.getPassword()));
        Object accountCredentials = getCredentials(info);
        return equals(tokenCredentials, accountCredentials);
    }

    private String encrypt(String data) {
        return new Md5Hash(data).toHex();
    }
}
