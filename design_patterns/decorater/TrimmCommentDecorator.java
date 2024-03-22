package com.example.security_study.decorater;

public class TrimmCommentDecorator extends CommentDecorator{

    // 상속 해야하니까 기본 생성자가 없는 부모와 같이 생성자가 필요합니다.
    public TrimmCommentDecorator(AfterCommentService afterCommentService) {
        super(afterCommentService);
    }

    @Override
    public void addComment(String comment) {
        super.addComment(trim(comment));
    }

    private String trim(String comment) {
        return comment.replace("트림" , "");
    }
}