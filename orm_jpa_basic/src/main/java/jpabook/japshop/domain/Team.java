//package jpabook.japshop.domain;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class Team {
//
//    @Id @GeneratedValue
//    @Column(name = "TEAM_ID")
//    private Long id;
//    private String name;
//    // 반대편에 있는 객체의 변수명
//    /**
//     * 객체와 테이블의 연관관계를 맺는 차이를 이해하면 mappedBy 활용을 할 수 있음.
//     * 객체 연관관계는
//     * 회원 -> 팀 단방향 1
//     * 팀 -> 회원 단방향 1
//     * 참조는 양쪽 다 존재해야함.
//     * 테이블 연관관계는
//     * 양방향 관계 1 회원 <-> 팀 PK , FK 하나로 연관관계가 끝나지만
//     *
//     * 연관관계의 주인
//     * 외래키가 있는 곳을 주인으로 생각하는 것이 편함.
//     * 일대 다일 때 다쪽이 연관관계의 주인이자 외래키를 가짐
//     * mappedBy = 맵핑이 되어버렸다. 주인이 아니다. 조회 가능.
//     */
//    /**
//     * 셋 메소드의 경우에 새로 생성 하여 add 형태나 , change 로 변경하여 외래키 변수를 지정하는 것이 편하다.
//     * @param id
//     */
//    @OneToMany(mappedBy = "team")
//    private List<Member1> members = new ArrayList<>();
//    public Long getId() {
//        return id;
//    }
//
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
//
//    public List<Member1> getMembers() {
//        return members;
//    }
//
//    public void setMembers(List<Member1> members) {
//        this.members = members;
//    }
//}
