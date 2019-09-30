package com.qf.qf.yl.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.base.BaseServiceImpl;
import com.qf.base.IBaseDao;
import com.qf.dto.ResultBean;
import com.qf.entity.User;
import com.qf.mapper.UserMapper;
import com.qf.user.api.IUserService;
import com.qf.util.GeneratorRedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import com.qf.constant.RedisConstant;

import java.util.concurrent.TimeUnit;

@Component
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public IBaseDao<User> getBaseDao() {
        //返回楼上bean出来的mapper
        return userMapper;
    }

    /**
     * 先验证用户名，再验证密码
     */
    @Override
    public User checkLogin(User user) {

        //验证用户是否存在
        if (user!=null){
            User user1 = userMapper.selectByUsername(user.getUsername());
            //如果用户存在，校验密码
            if(user1!=null){
                boolean matches = encoder.matches(user.getPassword(), user1.getPassword());
                if(matches){
                    //校验用户状态
                    if(user1.getStatu().equals("ok")){
                        return user1;
                    }
                    return null;
                }
            }
        }

        return null;
    }

    /**
     * 验证是否已登录
     */
    @Override
    public ResultBean checkIsLogin(String uuid) {

        User user = null;

        if(uuid!=null) {
            //根据user_token的键的cookie的值（uuid）获取redis中的键
            String key = GeneratorRedisKey.getKey(uuid);
            //存的时候设置了系列化，取的时候也要设置系列化
            redisTemplate.setKeySerializer(new StringRedisSerializer());

            user = (User) redisTemplate.opsForValue().get(key);
            if (user!=null){
                if(user.getStatu().equals("ok")){
                    //刷新redis存储时间
                    redisTemplate.expire(key,30, TimeUnit.MINUTES);

                    return ResultBean.success(user,"登录成功");
                }
                return ResultBean.error("登录失败");
            }
        }
        return ResultBean.error("登录失败");
    }

    @Override
    public void logout(String uuid) {

        if(uuid!=null) {

            String key = GeneratorRedisKey.getKey(uuid);
            redisTemplate.delete(key);

//            return ResultBean.success("删除成功");
        }
//        return ResultBean.success("删除失败");
    }

    @Override
    public ResultBean activeUser(String uuid) {

        String key = GeneratorRedisKey.getKey(RedisConstant.USER_REGIST, uuid);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        User user = (User) redisTemplate.opsForValue().get(key);
        redisTemplate.delete(key);
        Long id = user.getId();

        int i = userMapper.activeUser(id);

        if(i>=1){
            return ResultBean.success("激活成功");
        }
        return ResultBean.error("激活失败");
    }
}
