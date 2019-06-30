package com.cn.wlx.controller;

import com.cn.wlx.service.SmsCodeService;
import com.cn.wlx.utils.CheckUitl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Adminn on 2019/6/29.
 */
@Controller
@RequestMapping("/app/mobile")
public class MobileController {
    @Autowired
    private SmsCodeService smsCodeService;
    @RequestMapping(value = "/obtainVerifyCode",method = RequestMethod.GET)
    public String obtainVerifyCode(String mobile){
        if(StringUtils.isEmpty(mobile)){
            return "";
        }
        if(CheckUitl.isMobile(mobile)){
            return "";
        }
        String smsCode = smsCodeService.generateAndSaveSmsCode(mobile);
        return smsCode;
    }
}
