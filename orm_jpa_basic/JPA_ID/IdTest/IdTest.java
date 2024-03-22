package com.example.security_study.IdTest;

import com.example.security_study.domain.Product;
import com.example.security_study.domain.Template;
import com.example.security_study.mapper.ProductRepository;
import com.example.security_study.mapper.TemplateRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IdTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    TemplateRepository templateRepository;


    @Test
    @DisplayName("기본 ID 사용 테스트")
    @Rollback(value = false)
    public void defaultIdTest() {


        int count = 0;
        while (count < 10) {
            Product product = new Product();
            productRepository.save(product);
            count++;
        }
    }

    @Test
    @DisplayName("embedded ID 사용 테스트")
    @Rollback(value = false)
    public void embeddedIdTest() {


        int count = 0;
        while (count < 10) {
            Product product = Product.createProduct(count, count + 1);
            productRepository.save(product);
            count++;
        }
    }

    @Test
    @DisplayName("복합 ID 사용 테스트")
    public void IdTest() {

        Template template = new Template();
        Template save = templateRepository.save(template);

        Product product = Product.createProduct(3 ,  4 , template);
        Product save1 = productRepository.save(product);

        Assertions.assertThat(save.getTemplateId()).isEqualTo(save1.getProduct_id().getTemplateId());
    }
}
