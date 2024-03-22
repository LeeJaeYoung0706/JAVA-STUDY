package jpabook.japshop.domain.NAndM;

public class 개념설명 {

    /**
     *      * N : M 실무에선 안쓰니깐
     *      * 쓰지말기.
     *      * RDB 에서는 조인테이블을 생성하여 N : M 관계를 만든다.
     *      * 관계테이블을 생성한다는 것
     *      *
     *      *
     *      * 객체 - > 컬렉션을 활용하기에 다대다 관계 가능 하다.
     *      * @ManyToMany 사용
     *      *
     *      * 편해보이는데, 문제점이 있다.
     *      *
     *      * 연결테이블은 단순히 연결만 하겠다는 것이 아니다.
     *      * 주문 시간 , 수량 같은 데이터가 들어가는데,
     *      * 그 것 을 활용 할 수 없다.
     *      * 따라서 활용하기 힘들다.
     *      *
     *      * query 가 중간테이블 활용하고 조인해서 나와야해서 query 가 이상하게 나옴
     *      *
     *      * 해결방안
     *      *
     *      * 중간테이블을 객체(Entity) 로 만들고
     *      * @OneToMany , @ManyToOne 을 사용해서 만든다.
     *      * Member = MemberProduct = Product 를 체크하면 된다.
     *      *
     */
}
