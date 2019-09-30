package com.qf.qf.yl.sso.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.constant.CookieConstant;
import com.qf.dto.ResultBean;
import com.qf.entity.User;
import com.qf.user.api.IUserService;
import com.qf.util.GeneratorRedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("user")
public class UserController {

    @Reference
    private IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("login")
    public String login(){
        return "login";
    }

    /**
     * 验证登录
     */
    @RequestMapping("checkLogin")
    public String checkLogin(User user, HttpServletResponse response){

        User currentUser = userService.checkLogin(user);
        if(currentUser!=null){

            //登录成功，往redis中存数据
            String uuid = UUID.randomUUID().toString();
            //生成redis键
            String key = GeneratorRedisKey.getKey(uuid);
            //设置键的系列化
            redisTemplate.setKeySerializer(new StringRedisSerializer());
            //添加redis
            redisTemplate.opsForValue().set(key,currentUser);
            //设置超时时间
            redisTemplate.expire(key,30, TimeUnit.MINUTES);

            //新建一个Cookie，保存uuid
            Cookie cookie = new Cookie(CookieConstant.userToken, uuid);
            cookie.setPath("/");
//            cookie.setDomain("qf.com");
            response.addCookie(cookie);

            return "redirect:http://localhost:8092/index";
        }
        return "login";
    }

    /**
     * 验证是否已登录
     */
    @RequestMapping("checkIsLogin")
    @ResponseBody
    public ResultBean checkIsLogin(@CookieValue(name = CookieConstant.userToken,required = false)String uuid){

        return userService.checkIsLogin(uuid);
    }

    /**
     * 注销
     */
    @RequestMapping("logout")
    public String logout(@CookieValue(name = CookieConstant.userToken)String uuid){

        userService.logout(uuid);


        Cookie cookie = new Cookie(CookieConstant.userToken, "");
        cookie.setMaxAge(0);

        return "redirect:http://localhost:8092/index";
    }

}
