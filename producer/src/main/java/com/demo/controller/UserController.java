package com.demo.controller;

import com.demo.service.UserService;
import com.demo.model.Response;
import com.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<Response> getUser(int limit, String filter, String gender, int offset) {

        List<User> users = userService.getUsers(limit, filter, gender, offset);

        return new ResponseEntity<>(new Response("success", users), users.isEmpty() ? HttpStatus.NOT_FOUND :HttpStatus.OK);
    }


    @PostMapping("/users")
    public ResponseEntity<Response> createUser(@RequestBody User user){

        return new ResponseEntity<>(new Response("success", user), HttpStatus.OK);
    }
}
