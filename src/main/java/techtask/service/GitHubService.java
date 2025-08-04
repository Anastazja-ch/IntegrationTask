package techtask.service;


import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import techtask.dto.GitHubBranchDto;
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

        assert gitHubAllRepos != null;
        List<GitHubRepoDto> nonForkedRepos = Arrays.stream(gitHubAllRepos)
                .filter(repo -> !repo.isFork())
                .toList();

        for (GitHubRepoDto repo : nonForkedRepos) {
            List<GitHubBranchDto> branches = getBranches(repo.getOwner().getLogin(), repo.getName());
            repo.setBranches(branches);
        }
        return nonForkedRepos;
    }

    public List<GitHubBranchDto> getBranches(String owner, String repoName) {
        String url = "https://api.github.com/repos/" + owner + "/" + repoName + "/branches";
        GitHubBranchDto[] branches = restTemplate.getForObject(url, GitHubBranchDto[].class);
        assert branches != null;
        return Arrays.asList(branches);

    }

}
