package io.pivotal.service;

import io.pivotal.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public List<User> getUsers(int limit, String filter, String gender, int offset) {
        return null;
    }
}
