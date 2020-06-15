package com.demo.contract;

import com.demo.ProducerServerApplication;
import com.demo.controller.UserController;
import com.demo.service.UserService;
import com.demo.model.User;
import com.demo.processor.AddContextPathProcessor;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerServerApplication.class)
public abstract class UserBase {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets");

    @Rule
    public TestName testName = new TestName();

    @MockBean
    private UserService userService;

    @Autowired
    private UserController userController;

    @Autowired
    private AddContextPathProcessor addContextPathProcessor;

    @Before
    public void setup() {
        when(userService.getUsers(anyInt(),eq("email"),anyString(),anyInt()))
                .thenReturn(Arrays.asList(new User(1,"John"), new User(2,"Tom")));
        when(userService.getUsers(anyInt(),eq("not found"),anyString(),anyInt()))
                .thenReturn(Arrays.asList());

        RestAssuredMockMvc.standaloneSetup(MockMvcBuilders
                .standaloneSetup(userController)
                .apply(documentationConfiguration(this.restDocumentation)
                        .operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .alwaysDo(document(getClass().getSimpleName() + "_" + testName.getMethodName(), addContextPathProcessor))
        );
    }

    public void assertThatRejectionReasonIsNull(Object rejectionReason) {
        assert rejectionReason == null;
    }
}
