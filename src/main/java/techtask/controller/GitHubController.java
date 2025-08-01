package techtask.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientResponseException;
import techtask.dto.GitHubRepoDto;
import techtask.service.GitHubService;

import java.util.List;

@RestController
@RequestMapping("/github")
public class GitHubController {


    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<GitHubRepoDto>> gettingUserRepos(@PathVariable("username") String username) {
        if (username.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        try {
            List<GitHubRepoDto> repos = gitHubService.getUsersRepo(username);
            return ResponseEntity.ok(repos);

        } catch (RestClientResponseException e) {

            if (e.getRawStatusCode() == 404) {
                return ResponseEntity.status(404).build();
            }

            return ResponseEntity.status(500).build();
        }
    }
}