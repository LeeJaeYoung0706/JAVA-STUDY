//package jpabook.japshop.domain.extendsmapping.entity;
//
//import javax.persistence.*;
//
//
//@Entity
///**
// * 조인 전략
// */
//@Inheritance(strategy = InheritanceType.JOINED)
///**
// * 단일 테이블 전략
// * @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// * 이 때는
// * @DiscriminatorColumn
// * 이 없어도 자동으로 들어가서 생성됨.
// * 하지만 생성해두는 것이 좋음.
// */
//
///**
// * 상속 할 부모 객체는 생성되지 않음.
// * 추상화로 public abstract class Item 으로 생성해야함.
// * @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// * 이 때는
// * @DiscriminatorColumn
// * 이 없어도 자동으로 들어가서 생성됨.
// * 하지만 생성해두는 것이 좋음.
// *
// * @JPAMain 에 단점 정리 /&& 표시
// */
///**
// * 추가 하게 되면 크리에이트 테이블에서 DTYPE 라는 컬럼이 생성 됨.
// *
// * Hibernate:
// *
// *     create table Items (
// *        DTYPE varchar(31) not null,
// *         id bigint not null,
// *         name varchar(255),
// *         price integer not null,
// *         primary key (id)
// *     ) engine=InnoDB
// *
// *    디폴트 값으로 DTYPE 컬럼 생성
// *
// *   DB 에는
// *   Movie,1,이름,10000 이런식으로 객체명 저장됨. 디폴트로,
// *   바꿔야 할 경우에는는 *
// *
// *   @Entity
// * @DiscriminatorValue("A")
// * public class Album extends Items {
// *
// * 이런식으로 벨류 사용
// */
//@DiscriminatorColumn
//public abstract class Items {
//
//    @Id @GeneratedValue
//    private Long id;
//
//    private String name;
//    private int price;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//}
