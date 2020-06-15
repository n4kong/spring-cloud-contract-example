package com.demo.contract;

import com.demo.model.User;
import com.demo.service.UserService;
import com.demo.processor.AsciidocMessageProcessor;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMessageVerifier
public class UserMessageBase {
    @Autowired
    UserService userService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setup() {
        String testName = getClass().getSimpleName() + "_" + this.testName.getMethodName();
        AsciidocMessageProcessor asciidocMessageProcessor = new AsciidocMessageProcessor(testName, rabbitTemplate);
        rabbitTemplate.addBeforePublishPostProcessors(asciidocMessageProcessor);
    }

    protected void mockSendUserMessage() {
        rabbitTemplate.setRoutingKey("some.routing.key");
        userService.sendUserMessage("user.exchange", "some.routing.key", new User(99, "John"));
    }
}



