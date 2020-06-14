package com.demo.service;

import com.demo.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public List<User> getUsers(int limit, String filter, String gender, int offset) {
        return null;
    }

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendUserMessage(String exchange, String routingKey, User user) {
        rabbitTemplate.convertAndSend(exchange, routingKey, user);
    }
}


