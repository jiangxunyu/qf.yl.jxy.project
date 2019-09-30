package com.qf.util;

import com.qf.constant.RedisConstant;

public class GeneratorRedisKey {

    public static String getKey(String uuid){
        return RedisConstant.user_token+uuid;
    }

    public static String getKey(String key,String uuid){
        return new StringBuilder(key).append(uuid).toString();
    }
}
