package com.demo.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQTestConfig {
    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(userPresenceQueue())
                .to(userPresenceExchange())
                .with("some.routing.key");
    }

    private DirectExchange userPresenceExchange() {
        return new DirectExchange("user-presence");
    }

    @Bean
    Queue userPresenceQueue() {
        return new Queue("user-presence");
    }
}
