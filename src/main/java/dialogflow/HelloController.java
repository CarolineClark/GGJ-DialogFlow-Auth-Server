package dialogflow;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Lists;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@RestController
public class HelloController {

    private final String CREDENTIALS_JSON = "credentials.json";
    private final String POST_URL = "https://dialogflow.googleapis.com/v2/projects/ggj19-what-does-home-mean/agent/sessions/34563:detectIntent";

    @CrossOrigin
    @RequestMapping(path = "/hello", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DialogFlowResponse index(@RequestBody DialogFlowRequestBody body) throws IOException {
        String authHeader = authExplicit();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authHeader);

        HttpEntity<DialogFlowRequestBody> request = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();

        return getDisplayName(request, restTemplate);
//        return getBodyAsString(request, restTemplate);
    }

    private String getBodyAsString(HttpEntity<DialogFlowRequestBody> request, RestTemplate restTemplate) {
        ResponseEntity<String> response = restTemplate.postForEntity(POST_URL, request , String.class);
        return response.getBody();
    }

    private DialogFlowResponse getDisplayName(HttpEntity<DialogFlowRequestBody> request, RestTemplate restTemplate) {
        ResponseEntity<DialogFlowResponse> response = restTemplate.postForEntity(POST_URL, request , DialogFlowResponse.class);
        return response.getBody();
    }

    private String authExplicit() throws IOException {
        File f = new File(CREDENTIALS_JSON);
        if (!f.exists()) {
            return null;
        }
        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_JSON))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        AccessToken accessToken1 = credentials.refreshAccessToken();
        return accessToken1.getTokenValue();
    }
}
