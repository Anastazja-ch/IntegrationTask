package techtask.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import techtask.dto.GitHubRepoDto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubService {

    final RestTemplate restTemplate = new RestTemplate();

    public List<GitHubRepoDto> getUsersRepo(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";
        GitHubRepoDto[] gitHubAllRepos = restTemplate.getForObject(url, GitHubRepoDto[].class);

        return Arrays.stream(gitHubAllRepos)
                .filter(repo -> !repo.isFork())
                .collect(Collectors.toList());

    }

}
