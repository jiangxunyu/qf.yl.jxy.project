package com.qf.qfylproductmanageservice.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.qf.constant.RabbitmqConstant;

@Configuration
public class RabbitmqConfig {

    @Bean
    public TopicExchange getTopicExchange(){

        return new TopicExchange(RabbitmqConstant.PRODUCT_ADD_EXCHANGE);
    }
}
