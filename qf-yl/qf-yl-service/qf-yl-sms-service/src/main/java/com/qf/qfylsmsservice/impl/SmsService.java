package com.qf.qfylsmsservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dto.ResultBean;
import com.qf.qfylsmsservice.util.SmsUtil;
import com.qf.sms.api.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import com.qf.constant.RedisConstant;

import java.util.concurrent.TimeUnit;

@Component
@Service
public class SmsService implements ISmsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultBean sendSms(String mobile, String tplId, String code) {

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(RedisConstant.SMS_CODE,code);
        redisTemplate.expire(code,30, TimeUnit.MINUTES);

        String content = "@1@="+code;

        String result = SmsUtil.sendTplSms(mobile, tplId, content);
        int start =  result.lastIndexOf("<status>")+8;
        int end = result.lastIndexOf("</status>");
        String result_sub = result.substring(start,end);
        if(result_sub.equals(0)){
            return ResultBean.success("短信发送成功");
        }
        return ResultBean.error("短信发送失败");
    }
}
