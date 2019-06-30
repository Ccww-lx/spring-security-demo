package com.cn.wlx.mobile;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.Assert;

/**
 * Created by Adminn on 2019/6/28.
 */
public class SmsAuthenticationProvide implements AuthenticationProvider, MessageSourceAware {
    private UserDetailsService userDetailsService;
    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        Assert.isInstanceOf(SmsAuthenticationToken.class, authentication,
                messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.onlySupports",
                        "Only UsernamePasswordAuthenticationToken is supported"));
        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authenticationToken);
        String mobile = (String) authenticationToken.getPrincipal();
        if (mobile == null) {
            throw new InternalAuthenticationServiceException("can't obtain user info ");
        }
        mobile = mobile.trim();
        UserDetails user = userDetailsService.loadUserByUsername(mobile);
        if (user == null) {
            throw new InternalAuthenticationServiceException("can't obtain user info ");
        }
        SmsAuthenticationToken smsAuthenticationToken = new SmsAuthenticationToken(user, user.getAuthorities());
        return smsAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (SmsAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }
}
