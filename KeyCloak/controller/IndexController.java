package com.keti.iam.idthub.controller;

import com.keti.iam.idthub.config.SecurityConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {
    /**
     * 테스트 컨트롤러 요청권한 테스트를 위한 맵핑
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "test";
    }


    @GetMapping("/test/bean")
    public String testString() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SecurityConfig.class);
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        StringBuilderUtilList stringBuilderUtilList  = new StringBuilderUtilList();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName);
            stringBuilderUtilList.stringBuilderAdd(beanDefinitionName);
            stringBuilderUtilList.stringBuilderAdd(" ");
        }
        return stringBuilderUtilList.resultString();
    }

    private class StringBuilderUtilList {
        private final List<String> stringList = new ArrayList<>();

        public List<String> stringBuilderAdd(String value) {
            stringList.add(value);
            return stringList;
        }
        public String resultString(){
            StringBuilder stringBuilder = new StringBuilder();
            for (String value : stringList) {
                stringBuilder.append(value);
            }
            stringList.clear();
            return stringBuilder.toString();
        }
    }
}
