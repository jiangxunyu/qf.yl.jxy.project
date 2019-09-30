package com.qf.qf.yl.user.service;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableDubbo
@MapperScan("com.qf.mapper")
public class QfYlUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QfYlUserServiceApplication.class, args);
    }

}
