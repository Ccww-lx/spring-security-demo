package com.cn.wlx.config;

import com.cn.wlx.constant.SecurityConstants;
import com.cn.wlx.mobile.CustomAuthenticationFailureHandler;
import com.cn.wlx.mobile.CustomAuthenticationSuccessHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Adminn on 2019/6/28.
 */
@Configurable
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;
    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable().and()
                .formLogin()
                .loginPage(SecurityConstants.APP_FORM_LOGIN_PAGE)
                .loginProcessingUrl(SecurityConstants.APP_FORM_LOGIN_URL)
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .apply(smsAuthenticationSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstants.APP_MOBILE_VERIFY_CODE_URL,
                        SecurityConstants.APP_USER_REGISTER_URL)
                .permitAll()
                .and()
                .csrf().disable();
    }
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

}
