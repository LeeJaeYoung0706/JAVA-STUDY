package com.example.security_study.decorater;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Method;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class Main {

    @Test
    @DisplayName("데코레이터 패턴 테스트")
    public void decoratorTest(){
        boolean enterFilter = true;
        boolean trimEnable = true;
        AfterCommentService afterCommentService = new DefaultCommentService();

        if (enterFilter){
            afterCommentService = new EnterCommentDecorator(afterCommentService);
        }

        if (trimEnable) {
            afterCommentService = new TrimmCommentDecorator(afterCommentService);
        }

        AfterClient afterClient = new AfterClient(afterCommentService);
        afterClient.writeComment("테스트 트림테스트");
        afterClient.writeComment("테스트 트림 엔터 테스트");
    }
}
