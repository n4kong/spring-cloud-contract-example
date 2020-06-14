package com.demo.contract;

import com.demo.model.User;
import com.demo.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMessageVerifier
public class UserMessageBase {
    @Autowired
    UserService userService;

    protected void mockSendUserMessage() {
        userService.sendUserMessage("user.exchange", "some.routing.key", new User(99, "John"));
    }
}
