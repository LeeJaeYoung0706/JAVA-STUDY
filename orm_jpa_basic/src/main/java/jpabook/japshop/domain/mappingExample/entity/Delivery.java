//package jpabook.japshop.domain.mappingExample.entity;
//
//import javax.persistence.*;
//
//@Entity
//public class Delivery {
//
//    @Id @GeneratedValue
//    @Column(name = "DELIVERY_ID")
//    private Long id;
//    private String city;
//    private String street;
//    private String zipCode;
//    private DeliveryStatus deliveryStatus;
//
//    /**
//     * MEMBER 랑 양방향을 하고 싶다면 mappedBy 설정
//     */
//    @OneToOne(mappedBy = "delivery")
//    private Order order;
//}
