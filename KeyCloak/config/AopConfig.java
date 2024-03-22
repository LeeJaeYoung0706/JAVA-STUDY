package com.keti.iam.idthub.config;



import com.keti.iam.idthub.aop.aspect.AspectJAdvisors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    @Bean
    public AspectJAdvisors aspectJAdvisors(){
        return new AspectJAdvisors();
    }
}
