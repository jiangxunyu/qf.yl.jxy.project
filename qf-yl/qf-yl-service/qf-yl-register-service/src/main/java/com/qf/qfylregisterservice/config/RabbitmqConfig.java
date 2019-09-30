package com.qf.qfylregisterservice.config;

import com.qf.constant.RabbitmqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(RabbitmqConstant.REGISTER_USER_EXCHANGE);
    }

}
