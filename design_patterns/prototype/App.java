package com.example.patterns.prototype;

import lombok.RequiredArgsConstructor;


public class App {

    public static void main(String[] args) {
        GithubRepository repository = new GithubRepository();
        repository.setUser("jaesoon");
        repository.setName("live-study");

        GithubIssue githubIssue = new GithubIssue(repository);
        githubIssue.setId(1);
        githubIssue.setTitle("우린 자바 고수가 될 거시다.");

        String url = githubIssue.getUrl();
        System.out.println(url);


        GithubIssue clone = githubIssue.clone();

        repository.setUser("바뀐다.");
        System.out.println("(clone != githubIssue) = " + (clone != githubIssue));
        System.out.println("(clone.equals(githubIssue)) = " + (clone.equals(githubIssue)));
        System.out.println("(clone.getClass() == githubIssue.getClass()) = " + (clone.getClass() == githubIssue.getClass()));
        System.out.println("(clone.getRepository() == githubIssue.getRepository()) = " + (clone.getRepository() == githubIssue.getRepository()));
        System.out.println("clone = " + clone.getUrl());
        System.out.println("original = " + githubIssue.getUrl());
    }
}


