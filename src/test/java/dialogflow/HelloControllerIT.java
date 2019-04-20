package dialogflow;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIT {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/hello");
    }

    @Test
    public void getHello() throws Exception {
        DialogFlowRequestBody requestBody = new DialogFlowRequestBody();
        requestBody.queryInput = new DialogFlowRequestBody.QueryInput();
        requestBody.queryInput.text = new DialogFlowRequestBody.QueryInput.TextInput();
        requestBody.queryInput.text.text = "hello";
        requestBody.queryInput.text.languageCode = "en";
        ResponseEntity<String> response = template.postForEntity(base.toString(), requestBody, String.class);
        assertThat(response.getBody(), containsString("what-did-you-say"));
    }
}
