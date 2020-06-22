package com.demo.consumer.service;

import com.demo.consumer.model.User;
import org.junit.Test;
import org.junit.jupiter.params.aggregator.ArgumentAccessException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.demo:procedureService:+:stubs:6565"}, stubsMode  = StubRunnerProperties.StubsMode.LOCAL)
//@AutoConfigureStubRunner(
//        ids = {"com.demo:procedureService:+:stubs:6565"},
//        repositoryRoot = "https://nexus/nexus/content/repositories/contracts/",
//        stubsMode  = StubRunnerProperties.StubsMode.REMOTE)
public class ProducerServiceTest {

    @Autowired
    private ProducerService producerService;

    @Test
    public void shouldGetUserSuccess() {
        // when:
        List<User> users = producerService.getUsers();
        // then:
        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void shouldCreateUserSuccess() {
        // given:
        User user = new User();
        user.setName("John");
        user.setRequestId("11e2e42c-ab36-11ea-bb37-0242ac130003");
        // when:
        User createdUser = producerService.createUser(user);
        // then:
        assertThat(createdUser.getId()).isNotNull();
    }
}
