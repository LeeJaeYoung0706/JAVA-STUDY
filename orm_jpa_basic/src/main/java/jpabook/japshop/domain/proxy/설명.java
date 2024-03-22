package jpabook.japshop.domain.proxy;

public class 설명 {

    /**
     * em.find() vs em.getReference()
     *
     * find : 데이터베이스를 통해서 실제 값 조회
     * getReference : 데이터베이스 조회를 미루는 가짜엔티티 객체 조회
     *
     *
     * 특징
     *
     * 1. 프록시 객체는 실제 객체의 참조(target)을 보관
     * 2. 프록시 객체를 호출하면 프록시 객체는 실제 객체의 메소드를 호출
     *
     * Member member= entityManager.getReference(Member.class , member.getId());
     * member.getName();
     * 을 하게 되면
     *
     * 클라이언트 getName()-> 프록시객체-> 초기화요청 -> 영속성 컨텍스트 -> DB 조회
     * -> 실제 Entity 생성 -> Object
     * proxy -> target.getName() -> Object
     *
     * 1. memberProxy 에 값이 없다면,
     * 2. 영속성 컨텍스트에 요청 진짜 멤버 객체 가져와
     * 3. 영속성 컨텍스트가 DB 조회함
     * 4. 실제 entity를 만들어주고
     * 5. memberProxy 의 Member target 에 entity 를 맵핑해줌
     * 6. 그걸 리턴
     *
     *
     * 프록시 객체 특징
     * 1.프록시 객체는 처음 사용할 때 한 번만 초기화
     * 2.프록시 객체를 초기화 할 때 프록시 객체가 실제 엔티티로 바뀌는 것은 아님, 초기화 되면 프록시 객체를 통해서 실제 엔티티에 접근 가능
     * 3.프록시 객체는 원본 엔티티를 상속 받음 타입체크시 == 가 아닌 instance of 사용할 것  *********** 중요
     * 4.영속성 컨텍스트에 찾는 엔티티가 이미 있으면 entityManager.getReference(Member.class , member.getId()) 를 호출해도 실제 entity 반환\
     * 5.
     */
}
