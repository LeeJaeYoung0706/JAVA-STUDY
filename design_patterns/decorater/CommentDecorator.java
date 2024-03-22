package com.example.security_study.decorater;

public class CommentDecorator implements AfterCommentService{

    private final AfterCommentService afterCommentService;

    public CommentDecorator(AfterCommentService afterCommentService) {
        this.afterCommentService = afterCommentService;
    }

    @Override
    public void addComment(String comment) {
        afterCommentService.addComment(comment);
    }
}