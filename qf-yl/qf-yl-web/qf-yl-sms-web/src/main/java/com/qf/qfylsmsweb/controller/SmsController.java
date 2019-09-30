package com.qf.qfylsmsweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.dto.ResultBean;
import com.qf.sms.api.ISmsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SmsController {

    @Reference
    private ISmsService smsService;

    @RequestMapping("checkPhone/{phone}")
    @ResponseBody
    public ResultBean checkPhone(@PathVariable String phone){

        String tplid = "JSM42639-0001";
        int code1 = (int)(Math.random()*9+1)*1000;
        String code = code1+"";

        ResultBean resultBean = smsService.sendSms(phone, tplid, code);
        return resultBean;
    }
}
