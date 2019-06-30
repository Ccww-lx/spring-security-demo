package com.cn.wlx.config;

import com.cn.wlx.filter.SmsAuthenticationFilter;
import com.cn.wlx.mobile.CustomAuthenticationFailureHandler;
import com.cn.wlx.mobile.CustomAuthenticationSuccessHandler;
import com.cn.wlx.mobile.SmsAuthenticationProvide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created by Adminn on 2019/6/28.
 */
@Configurable
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsAuthenticationFilter smsAuthenticationfilter = new SmsAuthenticationFilter();
        smsAuthenticationfilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsAuthenticationfilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler());
        smsAuthenticationfilter.setAuthenticationFailureHandler(customAuthenticationFailureHandler());

        SmsAuthenticationProvide smsAuthenticationProvide=new SmsAuthenticationProvide();
        smsAuthenticationProvide.setUserDetailsService(userDetailsService);
        http.authenticationProvider(smsAuthenticationProvide);

        http.addFilterAfter(smsAuthenticationfilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public CustomAuthenticationFailureHandler customAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
    }}
