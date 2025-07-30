package techtask.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/github")
public class GitHubController {

    private final RestTemplate restTemplate = new RestTemplate();


    @GetMapping("/{username}")
    public ResponseEntity<String> gettingUserRepos(@PathVariable("username") String username) {
        if (username.isBlank()) {
            return ResponseEntity.badRequest().body("Username cannot be blank");
        }

        String url = "https://api.github.com/users/" + username;

        try {
            restTemplate.getForObject(url, String.class);

            return ResponseEntity.ok("Hello " + username);

        } catch (RestClientResponseException e) {

            if (e.getRawStatusCode() == 404) {
                return ResponseEntity.status(404).body("User not found");
            }

            return ResponseEntity.status(500).body("Something went wrong");
        }
    }
}