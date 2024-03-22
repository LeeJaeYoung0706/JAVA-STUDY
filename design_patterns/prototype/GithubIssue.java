package com.example.patterns.prototype;

import java.util.Objects;

public class GithubIssue implements Cloneable{

    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GithubIssue that = (GithubIssue) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(repository, that.repository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, repository);
    }

    private String title;

    private GithubRepository repository;

    public GithubIssue(GithubRepository repository) {
        this.repository = repository;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GithubRepository getRepository() {
        return repository;
    }

    public String getUrl() {
        return String.format("https://github.com/%s/%s/issues/%d",
                repository.getUser(),
                repository.getName(),
                this.getId());
    }

    @Override
    public GithubIssue clone() {
            GithubRepository repository = new GithubRepository();
            repository.setUser(this.repository.getUser());
            repository.setName(this.repository.getName());

            GithubIssue clone = new GithubIssue(repository);
            clone.setId(this.id);
            clone.setTitle(this.title);

            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
    }
}
