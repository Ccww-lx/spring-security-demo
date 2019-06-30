package com.cn.wlx.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by Adminn on 2019/6/28.
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken {
    private final Object principal;
    private Object credentials;

    public SmsAuthenticationToken(Object principal,Object credentials ) {
        super(null);
        this.principal = principal;
        this.credentials=credentials;
        setAuthenticated(false);
    }

    public SmsAuthenticationToken(Object principal, Object credentials,Collection<? extends GrantedAuthority> authorities) {
        super(null);
        this.principal = principal;
        this.credentials=credentials;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials=credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();

    }
}
