package com.hydsoft.rabbitmq.manual.configuration;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: ManualConfiguration
 * @Description:
 * @Author Jane
 * @Date: 2022/7/7 9:24
 * @Version 1.0
 */
@Configuration
public class ManualConfiguration {

    @Bean
    public Queue directQueueManual() {
        return new Queue("rabbit.test.queue.direct.manual", true);
    }

    @Bean
    public Exchange directExchangeManual() {
        return new DirectExchange("rabbit.test.exchange.direct.manual", true, false);
    }

    @Bean
    public Binding directBindingManual() {
        return BindingBuilder.bind(directQueueManual()).to(directExchangeManual()).with("manual").noargs();
    }

    @Bean
    public Queue delayQueue() {
        return new Queue("rabbit.test.queue.delay", true);
    }

    @Bean
    public Exchange delayExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("rabbit.test.exchange.delay", "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with("delay").noargs();
    }
}
