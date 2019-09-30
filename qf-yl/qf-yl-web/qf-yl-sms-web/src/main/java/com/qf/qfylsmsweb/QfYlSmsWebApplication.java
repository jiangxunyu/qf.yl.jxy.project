package com.qf.qfylsmsweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class QfYlSmsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(QfYlSmsWebApplication.class, args);
    }

}
