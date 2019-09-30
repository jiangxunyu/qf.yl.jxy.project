package com.qf.qfylproductmanageweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(FdfsClientConfig.class)
@SpringBootApplication
@EnableDubbo
public class QfYlProductManageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(QfYlProductManageWebApplication.class, args);
    }

}
