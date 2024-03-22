package jpabook.japshop.domain.전체적개념설명;

public class 영속성전이와고아객체그리고생명주기 {

    /**
     * CascadeType.ALL + orphanRemoval = true
     *
     * 스스로 생명주기를 관리하는 엔티티는 em.persist 로 영속화
     * em.remove 로 제거
     *
     * 두 옵션을 모두 활성화 할 경우 부모 엔티티를 통해 자식 엔티티의 생명 주기를 관리
     *
     * 도메인 주도 설계 Aggregate Root 개념을 구현할 때 좋음
     *
     */
}
