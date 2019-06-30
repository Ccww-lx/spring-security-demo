package com.cn.wlx.service;

import com.cn.wlx.utils.CommonUitl;
import com.cn.wlx.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Adminn on 2019/6/29.
 */
@Service
public class SmsCodeService {
    @Autowired
    private RedisUtil redisUtil;
    @Value("${sms.random.number.bigit}")
    private int randomNumberBigit;
    @Value("${sms.code.redis.retention.time}")
    private long smsCodeRetentionTime;

    public String generateAndSaveSmsCode(String mobile) {
        // 生成smsCode短信验证码
        String smsCode = CommonUitl.generateRandomNumbers(randomNumberBigit);
        //将smsCode保存在redis中
        redisUtil.set(mobile, smsCode, smsCodeRetentionTime);
        System.out.println("send smsCode:"+smsCode+" to the mobile:"+mobile);
        return smsCode;
    }
}