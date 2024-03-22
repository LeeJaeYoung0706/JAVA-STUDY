package com.example.security_study.decorater;

public class EnterCommentDecorator extends CommentDecorator{

    public EnterCommentDecorator(AfterCommentService afterCommentService) {
        super(afterCommentService);
        System.out.println("2");
    }

    @Override
    public void addComment(String comment) {
        System.out.println("3");
        if(!isEnter(comment)) super.addComment(comment);
    }

    private boolean isEnter(String comment) {
        return comment.contains("엔터");
    }
}