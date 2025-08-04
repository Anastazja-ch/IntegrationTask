package techtask.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GitHubControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnRepositoriesForExistingUser() {
        // given
        String username = "octocat";
        String url = "http://localhost:" + port + "/github/" + username;

        // when
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);


        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("octocat");
        assertThat(response.getBody()).contains("name");
        assertThat(response.getBody()).contains("commit");
        assertThat(response.getBody()).contains("sha");
    }
}