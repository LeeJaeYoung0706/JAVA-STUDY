package com.example.security_study.decorater;

public class AfterClient {

    private AfterCommentService commentService;

    public AfterClient(AfterCommentService commentService) {
        this.commentService = commentService;
    }

    public void writeComment(String comment){
        System.out.println(commentService.toString() + "   " + comment);
        commentService.addComment(comment);
    }
}