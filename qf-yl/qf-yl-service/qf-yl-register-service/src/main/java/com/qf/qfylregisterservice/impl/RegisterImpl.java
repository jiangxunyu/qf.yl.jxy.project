package com.qf.qfylregisterservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.constant.RabbitmqConstant;
import com.qf.dto.ResultBean;
import com.qf.dto.UserAndCode;
import com.qf.dto.UuidAndUser;
import com.qf.entity.User;
import com.qf.mapper.UserMapper;
import com.qf.user.register.api.IUserRegisterService;
import com.qf.util.GeneratorRedisKey;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.qf.constant.RedisConstant;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@Service
public class RegisterImpl implements IUserRegisterService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResultBean checkIsRegister(User user) {

        String password = encoder.encode(user.getPassword());
        user.setPassword(password);

        int i = mapper.insertUser(user);

        Long id = user.getId();
        user.setId(id);
        if (i>=0){

            //往redis中保存用户对应的uuid
            String uuid = UUID.randomUUID().toString();

            UuidAndUser uuidAndUser = new UuidAndUser(uuid, user);
            String key = GeneratorRedisKey.getKey(RedisConstant.USER_REGIST, uuid);
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            redisTemplate.opsForValue().set(key,user);
            redisTemplate.expire(key,30, TimeUnit.MINUTES);

            //往消息队列里发
            rabbitTemplate.convertAndSend(RabbitmqConstant.REGISTER_USER_EXCHANGE,
                    RabbitmqConstant.REGISTER_USER_ROUTING_KEY,
                    uuidAndUser);
            return ResultBean.success("注册成功");
        }

        return ResultBean.error("注册失败");
    }

    @Override
    public ResultBean checkRegisterCode(UserAndCode userAndCode) {

        String code = userAndCode.getCode();
        User user = userAndCode.getUser();

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        String o = (String) redisTemplate.opsForValue().get(RedisConstant.SMS_CODE);

        if (code.equals(o)){

            redisTemplate.delete(RedisConstant.SMS_CODE);
            String password = encoder.encode(user.getPassword());
            user.setPassword(password);

            int i = mapper.insertUser(user);

            Long id = user.getId();
            user.setId(id);
            if (i>=0){

                //往redis中保存用户对应的uuid
                String uuid = UUID.randomUUID().toString();

                UuidAndUser uuidAndUser = new UuidAndUser(uuid, user);
                String key = GeneratorRedisKey.getKey(RedisConstant.USER_REGIST, uuid);
                redisTemplate.setKeySerializer(new StringRedisSerializer());
                redisTemplate.opsForValue().set(key,user);
                redisTemplate.expire(key,30, TimeUnit.MINUTES);

                //往消息队列里发
                rabbitTemplate.convertAndSend(RabbitmqConstant.REGISTER_USER_EXCHANGE,
                        RabbitmqConstant.REGISTER_USER_ROUTING_KEY,
                        uuidAndUser);
                return ResultBean.success("注册成功");
            }
        }

        return ResultBean.error("注册失败");
    }
}
