package com.demo.consumer.service;

import com.demo.consumer.model.CreateUserResponse;
import com.demo.consumer.model.User;
import com.demo.consumer.model.UserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProducerService {


    @Value("${user-service.uri}")
    private String uri;

    public List<User> getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        httpHeaders.add("key","value");
        httpHeaders.add("Cookie","another_key=another_value");

        ResponseEntity<UserResponse> response = restTemplate.exchange(uri + "/users?limit=1094818791&filter=email&gender=m&offset=1234", HttpMethod.GET, new HttpEntity<>(httpHeaders), UserResponse.class);
        return response.getBody().getData();
    }

    public User createUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
        httpHeaders.add("key","value");

        ResponseEntity<CreateUserResponse> response = restTemplate.exchange(uri + "/users", HttpMethod.POST, new HttpEntity<>(user, httpHeaders), CreateUserResponse.class);
        return response.getBody().getData();
    }
}
