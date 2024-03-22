package jpabook.japshop.domain.orphan;

public class 설명 {
    /**
     * 고아객체란
     *  부모 엔티티와 연관관계가 끊어진 자식 엔티티
     *  인데 그걸 자동으로 삭제
     *  orphanRemoval = true
     * 주의점
     *      *  1. 참조하는 곳이 하나일 때 사용
     *      *  2. 특정 엔티티가 개인 소유
     *      *  3. @OneToOne , @OneToMany 만 사용 가능
     *      *  4. DB cascade 활용과 같다고 생각하면 됨.
     *
     *
     */
}
