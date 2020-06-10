package com.demo.consumer.controller;

import com.demo.consumer.model.User;
import com.demo.consumer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    @Autowired
    private ProducerService producerService;

    @GetMapping("/search")
    public List<User> search() {

        List<User> users = producerService.getUsers();

        return users;
    }
}
