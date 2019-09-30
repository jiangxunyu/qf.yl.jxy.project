package com.qf.qfylshoppingcartweb.interceptor;

import com.qf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qf.util.GeneratorRedisKey;
import com.qf.constant.RedisConstant;

import java.util.concurrent.TimeUnit;

@Component
public class CartInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if("user_token".equals(cookie.getName())){
                    String uuid = cookie.getValue();
                    redisTemplate.setKeySerializer(new StringRedisSerializer());
                    String key = GeneratorRedisKey.getKey(RedisConstant.user_token,uuid);
                    //根据key获取redis中对应的user对象
                    Object o = redisTemplate.opsForValue().get(key);
                    if(o!=null){
                        User user = (User) o;
                        //存入request域中
                        request.setAttribute("user",user);
                        //更新redis时间
                        redisTemplate.expire(key,30, TimeUnit.MINUTES);
                        return true;
                    }

                }
            }
        }
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }

}
