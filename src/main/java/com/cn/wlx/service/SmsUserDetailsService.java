package com.cn.wlx.service;

import com.cn.wlx.mobile.SmsAuthenticationToken;
import com.cn.wlx.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;

/**
 * Created by Adminn on 2019/6/28.
 */
@Service
public class SmsUserDetailsService implements UserDetailsService {
    //@Autowired
   private RedisUtil redisUtil;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从SecurityContext获取认证所需的信息（手机号码、验证码）
        SecurityContext context = SecurityContextHolder.getContext();
        SmsAuthenticationToken authentication = (SmsAuthenticationToken) context.getAuthentication();
        if (!additionalAuthenticationChecks(username, authentication)) {
            return null;
        }
        //获取用户手机号码对应用户的信息，包括权限等
        return new User("admin", "123456", Arrays.asList(new SimpleGrantedAuthority("admin")));
    }

    public boolean additionalAuthenticationChecks(String mobile, SmsAuthenticationToken smsAuthenticationToken) {
        //获取redis中手机键值对应的value验证码
        String smsCode = redisUtil.get(mobile).toString();
        //获取用户提交的验证码
        String credentials = (String) smsAuthenticationToken.getCredentials();
        if (StringUtils.isEmpty(credentials)) {
            return false;
        }
        if (credentials.equalsIgnoreCase(smsCode)) {
            return true;
        }
        return false;
    }
}
