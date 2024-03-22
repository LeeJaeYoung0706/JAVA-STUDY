package com.example.security_study.domain;

import jakarta.persistence.*;
import jakarta.persistence.EmbeddedId;
import lombok.Getter;

@Entity
@Getter
public class Product {

//    @Id
//    private UUID id;
//
//    public void setId(UUID id) {
//        this.id = id;
//    }

//    @Id
//    @GeneratedValue(generator = "product_sequence2")
//    @GenericGenerator(
//            name = "product_sequence2",
//            type = SequenceStyleGenerator.class,
//            parameters = {
//                    @org.hibernate.annotations.Parameter(
//                            name = "initial_value",
//                            value = "1"
//                    ), // 시작점
//                    @org.hibernate.annotations.Parameter(
//                            name = "increment_size",
//                            value = "1"
//                    ) // 캐싱 사이즈
//            }
//    )
//    private long id;

//    @Id
//    @GeneratedValue(generator = "my-generator")
//    @GenericGenerator(name = "my-generator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(
//                            name = "initial_value",
//                            value = "1"
//                    ), // 시작점
//                    @org.hibernate.annotations.Parameter(
//                            name = "increment_size",
//                            value = "50"
//                    ), // 캐싱 사이즈
//                    @org.hibernate.annotations.Parameter(
//                            name = "prefix",
//                            value = "sub"
//                    )
//            },
//            type = CustomSequenceGenerator.class)
//    private String id;

    @EmbeddedId
    private EmbeddedIdTest product_id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("templateId")
    @JoinColumn(name = "template_id")
    private Template template;

    private void setProduct_id(EmbeddedIdTest product_id) {
        this.product_id = product_id;
    }

    public static Product createProduct(long orderId , long templateId) {
        EmbeddedIdTest embeddedIdTest = new EmbeddedIdTest();
        embeddedIdTest.setTemplateId(templateId);
        embeddedIdTest.setOrderId(orderId);
        Product product = new Product();
        product.setProduct_id(embeddedIdTest);
        return product;
    }

    public static Product createProduct(long orderId , long templateId , Template template) {
        EmbeddedIdTest embeddedIdTest = new EmbeddedIdTest();
        embeddedIdTest.setTemplateId(templateId);
        embeddedIdTest.setOrderId(orderId);
        Product product = new Product();
        product.setProduct_id(embeddedIdTest);
        product.setTemplate(template);
        return product;
    }

    private void setTemplate(Template template) {
        this.template = template;
    }
}
