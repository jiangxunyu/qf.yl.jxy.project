package com.qf.qfylemailweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.dto.ResultBean;
import com.qf.entity.User;
import com.qf.user.api.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {

    @Reference
    private IUserService userService;

    @RequestMapping("active")
    @ResponseBody
    public String activeUser(String uuid){
        ResultBean resultBean = userService.activeUser(uuid);
        return resultBean.getMessage();
    }
}
