package com.demo.consumer.listener;

import com.demo.consumer.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserPresenceListener {
    private List<String> availableUsers = new ArrayList<>();

    @RabbitListener(queues = "user.queue")
    public void handle(User user) {
        this.availableUsers.add(user.getName());
    }

    public List<String> getAvailableUsers() {
        return availableUsers;
    }
}