package com.qf.qfylregisterweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.dto.ResultBean;
import com.qf.dto.UserAndCode;
import com.qf.entity.User;
import com.qf.sms.api.ISmsService;
import com.qf.user.register.api.IUserRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;

@Controller
public class RegisterController {

    @Reference
    private IUserRegisterService registerService;

    @Reference
    private ISmsService smsService;

    @RequestMapping("register")
    public String register(){
        return "register";
    }

    @RequestMapping("checkIsRegister")
    public String checkIsRegister(User user){

        if (user!=null) {
            ResultBean resultBean = registerService.checkIsRegister(user);
            if (resultBean.getErrno() == 0) {
                return "redirect:http://localhost:8091/user/login";
            }
        }
        return "register";
    }

    @RequestMapping("checkRegisterCode")
    public String checkRegisterCode(UserAndCode userAndCode){

        ResultBean resultBean = registerService.checkRegisterCode(userAndCode);
        if (resultBean.getErrno() == 0) {
            return "redirect:http://localhost:8091/user/login";
        }
        return "register";
    }
}
