//package jpabook.japshop.domain;
//
//
//import javax.persistence.*;
//
///**
// * 단방향 으로 설계를 다해놓고
// * 개발하면서 꼭 필요한 양방향일 경우에만 사용
// */
//@Entity
//public class Member1 {
//
//    @Id @GeneratedValue
//    private Long id;
//    @Column(name = "USERNAME")
//    private String userName;
//    /**
//     * DATABASE 와 Mapping 할 떄
//     * Member 다 : 1 TEAM 인데
//     * Member 입장에서는 다 기 때문에 Many가 먼저 나와야한다.
//     *
//     *
//     */
//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public Team getTeam() {
//        return team;
//    }
//
//    /**
//     * 양방향 객체 이기 때문에 ,
//     * 여기서 팀에 대한 값을 넣어주어야 한다.
//     * @param team
//     */
//    public void changeSetTeam(Team team) {
//        this.team = team;
//        team.getMembers().add(this);
//    }
//}
