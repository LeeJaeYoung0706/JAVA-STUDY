package com.example.patterns.domain.singleton;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {

    @Bean
    public String helloConfig(){
        return "helloConfig";
    }
}
