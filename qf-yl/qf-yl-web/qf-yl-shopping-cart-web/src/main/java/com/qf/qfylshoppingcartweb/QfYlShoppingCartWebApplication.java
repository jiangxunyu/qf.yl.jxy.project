package com.qf.qfylshoppingcartweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class QfYlShoppingCartWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(QfYlShoppingCartWebApplication.class, args);
    }

}
