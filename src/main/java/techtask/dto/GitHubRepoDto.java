package techtask.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubRepoDto {

    public String name;

    @JsonProperty("fork")
    private boolean isFork;

    private Owner owner;

    private List<GitHubBranchDto> branches;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFork() {
        return isFork;
    }

    public void setFork(boolean fork) {
        isFork = fork;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<GitHubBranchDto> getBranches() {
        return branches;
    }

    public void setBranches(List<GitHubBranchDto> branches) {
        this.branches = branches;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner {

        private String login;

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }


    }

}
