package com.qf.qfylsearchservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.qf.constant.RabbitmqConstant;

@Configuration
public class RabbitmqConfig {

    @Bean
    public Queue getQueue(){
        return new Queue(RabbitmqConstant.PRODUCT_ADD_QUEUE);
    }

    @Bean
    public TopicExchange getTopicExchange(){
        return new TopicExchange(RabbitmqConstant.PRODUCT_ADD_EXCHANGE);
    }

    @Bean
    public Binding getBinding(Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with(RabbitmqConstant.PRODUCT_ADD_ROUTING_KEY);
    }
}
