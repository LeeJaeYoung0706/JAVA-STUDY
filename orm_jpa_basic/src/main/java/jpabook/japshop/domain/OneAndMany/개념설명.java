package jpabook.japshop.domain.OneAndMany;

public class 개념설명 {
    /**
     *      * 1 : N 일 대 다
     *      * 객체 입장에는 가능한데,
     *      * db 설계상 fk 는 다에 존재해야하고,
     *      * 그럼 N 에 1의 테이블 PK 가 FK 로 존재해야 한다.
     *      * 그렇게 되면 ?
     *      * 연관관계 주인이 Team 에 있는데, Member에 있어아 하는 것과 대조되게 된다.
     *      * 따라서, 설계 상으로 좋지 않다.
     *      *
     *      * 좋은 연관관계 설계
     *      * 연관관계의 주인이 N에 존재
     *      * @ManyToOne
     *      * @JoinColumn("")
     *      *
     *      * 나쁜 연관관계 설계
     *      * 연관관계의 주인이 1에 존재
     *      * @OneToMany
     *      * @JoinColum("")
     *      * 이렇게 되면 ,
     *      * Insert 하고, Update Query 를 한번 더 날려야하는 상황이 되는데,
     *      * 그 이유는 외래키가 N 에 존재하기 때문과,
     *      * team.getMembers().add(member);
     *      * 를 실행 할 때 Update 문이 한번 더 나가게 된다.
     *      *
     *      * 성능상 큰 이슈는 되지 않지만, 손해
     *      *
     *      * 2번째 이유
     *      * Team 객체를 조정 했는데,
     *      * Member table 을 연이어서 Update 가 나가게 되는 상황이
     *      * 많이 꼬였을 때, 좋지 않음.
     *      *
     *      * 이럴 때 해결방안,
     *      * 다 대 일 양방향 처럼 변경하면 된다.
     *      *
     *      * 주의 할 점
     *      * @JoinColumn 을 넣지않으면 조인 테이블 방식을 사용하게 되서 쓸 대 없는 테이블이
     *      * 생성된다.
     *      * 장점이 있지만, 운영 , 성능 상 단점이 존재한다.
     *      * default = > join table create
     *      *
     *      * 일 대 다 보다는 좀 손해 보더라도 다 대 일 양방향을 사용할 것,
     *      *
     *      *
     *      * 일대다 양방향 사용시
     *      * N 의 객체에 연관관계 주인처럼 만든 다음
     *      * @ManyToOne
     *      * @JoinColum(name = "" , insertable = false , updatable = false)
     *      * 1의 객체에
     *      * @OneToMany
     *      * @JoinColum(name = "")
     *      *
     *      * 을 만드는데,
     *      * insertable - false 인설트문 처리 하지 않고,
     *      * updatable - false 업데이트 문 처리를 하지 않아서
     *      * readOnly 상태로 만든다.
     *      *
     */
}
