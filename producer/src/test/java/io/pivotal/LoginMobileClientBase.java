package io.pivotal;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerServerApplication.class)
public abstract class LoginMobileClientBase {

    private static final String OUTPUT = "target/generated-snippets";

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation(OUTPUT);

    @Rule
    public TestName testName = new TestName();

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(MockMvcBuilders
                .standaloneSetup(new LoginController())
                .apply(documentationConfiguration(this.restDocumentation))
                .alwaysDo(document(
                        getClass().getSimpleName() + "_" + testName.getMethodName())));
    }

    public void assertThatRejectionReasonIsNull(Object rejectionReason) {
        assert rejectionReason == null;
    }

}
