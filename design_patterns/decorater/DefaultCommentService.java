package com.example.security_study.decorater;

public class DefaultCommentService implements AfterCommentService{

    @Override
    public void addComment(String comment) {
        System.out.println("4");
        System.out.println("default = " + comment);
    }
}