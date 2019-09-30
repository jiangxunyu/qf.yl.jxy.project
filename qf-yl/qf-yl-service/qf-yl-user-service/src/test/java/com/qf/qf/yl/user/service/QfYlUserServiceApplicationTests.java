package com.qf.qf.yl.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QfYlUserServiceApplicationTests {

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void test1(){
        String encode = encoder.encode("123456");
        System.out.println(encode);
    }

    @Test
    public void contextLoads() {
    }

}
