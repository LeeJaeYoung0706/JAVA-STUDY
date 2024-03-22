package com.example.patterns.prototype;

import lombok.Data;


public class GithubIssueData {
    private int id;

    public GithubIssueData() {
    }

    private String title;

    private GithubRepository repository;

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

    public void setRepository(GithubRepository repository) {
        this.repository = repository;
    }
}