package jpabook.japshop.domain.extendsmapping;

import javax.persistence.*;

public class 설명 {
    /**
     * 객체 - > 상속관계 존재
     * RDB - > 상속관계 없음
     * 그나마 비슷한 것은
     * 슈퍼타입 , 서브타입 이라는 모델링 기법이 존재
     * 객체 상속과 구조 == DB 슈퍼타입 , 서브타입 으로 변환
     *
     * 방법이 3가지 있는데
     * * 조인전략
     * 부모 SuperType
     * Item_id 같은 pk 가 존재할 때
     *
     * 자식 SubType 에도
     * Item_id 를 pk , fk 로 가지고 있는다.
     *
     * * 단일테이블 전략
     * 모든 컬럼을 다 넣고 SubTable 을 Type 컬럼을 추가하여 생성
     *
     * * Subtype 객채를 모두 하나씩 구현하고
     * extend 받을 변수들을 다 구현
     *
     *
     * 주요 어노테이션
     * @Inheritance(strategy = InheritanceType.XXX)
     * 1. JOINED : 조인 전략
     * 2. SINGLE_TABLE : 단일 테이블
     * 3. TABLE_PER_CLASS : 모두 하나씩 구현 쓰지 마세요.
     *
     * @DiscriminatorColumn(name="SubTableType")
     *
     * @DiscriminatorValue("XXX")
     *
     * @Entity패키지 참조
     */

    /**
     * 기본 셋팅으로 진행할 경우 (@Entity) 및 extends 만 활용
     */

     /**
     * 아무것도 셋팅을 하지 않은 상태에서 extends Item 만 걸을 경우
     *
     *     create table Item (
     *        DTYPE varchar(31) not null,
     *         id bigint not null,
     *         name varchar(255),
     *         price integer not null,
     *         ITEM_ID bigint not null,
     *         author varchar(255),
     *         isbn varchar(255),
     *         artist varchar(255),
     *         actor varchar(255),
     *         director varchar(255),
     *         primary key (ITEM_ID)
     *     ) engine=InnoDB
     *
     *     이런 식으로 단일 테이블 전략을 따라 생성 된다.
     *
     * @Entity
     * public class Movie extends Item {
     *
     *     private String director;
     *     private String actor;
     * }
     *
     * @Entity
     * public class Book extends Item {
     *
     *     private String author;
     *     private String isbn;
     * }
     *
     * @Entity
     * public class Album extends Item {
     *
     *     private String artist;
     * }
      *
      * 단일 테이블 전략
      *
      *  * 단일 테이블 전략
      *  * @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
      *  * 이 때는
      *  * @DiscriminatorColumn
      *  * 이 없어도 자동으로 들어가서 생성됨.
      *  하지만 생성 해두어야함.
      *  장점
      *  1. 조인이 필요 없어서 쿼리가 심플하게 나가기 때문에 좋음.
      *  단점
      *  1. 자식 엔티티가 매핑한 컬럼은 모두 null 허용
      *  2. 테이블이 커질 수 있다 상황에 따라 오히려 성능저하
      *
      *
      * */


    /**
     *      @Entity
     *      * 조인 전략 ( 기본으로 생각해야 할 전략 )
     *      @Inheritance(strategy = InheritanceType.JOINED)
     *      public class Item {
     *
     *          @Id
     *          @GeneratedValue
     *          private Long id;
     *
     *          private String name;
     *          private int price;
     *
     *      }
     *
     *          alter table Movie
     *        add constraint FK5sq6d5agrc34ithpdfs0umo9g
     *        foreign key (id)
     *        references Item (ITEM_ID)
     *
     *        Hibernate:
     *
     *     create table Team (
     *        TEAM_ID bigint not null,
     *         name varchar(255),
     *         primary key (TEAM_ID)
     *     ) engine=InnoDB
     * Hibernate:
     *
     *     alter table Album
     *        add constraint FKcve1ph6vw9ihye8rbk26h5jm9
     *        foreign key (id)
     *        references Item (ITEM_ID)
     * Hibernate:
     *
     *     alter table Book
     *        add constraint FKbwwc3a7ch631uyv1b5o9tvysi
     *        foreign key (id)
     *        references Item (ITEM_ID)
     *
     *        Hibernate:
     *
     *     create table Movie (
     *        actor varchar(255),
     *         director varchar(255),
     *         id bigint not null,
     *         primary key (id)
     *     ) engine=InnoDB
     *
     *     이런 식으로 여러개의 테이블이 생성되고 pk와 kf 가 설정 됨.
     *
     *     조인전략 장점
     *     1. 테이블의 정규화
     *     2. 외래키 참조 무결성 제약조건 활용
     *     3. 저장공관 효율화
     *     단점
     *     1. 조인을 많이 사용 성능 저하
     *     2. 조회 쿼리 복잡
     *     3. 데이터 저장시 insert 쿼리 2번
     *     이건 근데 크게 단점이 되지 않음.
     *
     */

}
