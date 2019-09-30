package com.qf.qfylemailweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class QfYlEmailWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(QfYlEmailWebApplication.class, args);
    }

}
