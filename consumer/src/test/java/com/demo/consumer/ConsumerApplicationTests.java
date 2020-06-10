package com.demo.consumer;

import static org.assertj.core.api.Assertions.assertThat;

import com.demo.consumer.model.User;
import com.demo.consumer.service.ProducerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner(ids = {"com.demo:procedureService:+:stubs:6565"}, stubsMode  = StubRunnerProperties.StubsMode.LOCAL)

@DirtiesContext
public class ConsumerApplicationTests {

	@Autowired
	private ProducerService producerService;

	@Test
	public void contextLoads() {
	}
	
	@Test
	public void shouldGetUserSuccess() {
		// given:
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
		assertThat(createdUser.getName()).isNotNull();
	}

}
