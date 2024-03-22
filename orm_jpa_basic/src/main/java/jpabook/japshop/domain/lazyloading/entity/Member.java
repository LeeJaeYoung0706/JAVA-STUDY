//package jpabook.japshop.domain.lazyloading.entity;
//
//
//
//
//
//import javax.persistence.*;
//
//@Entity
//public class Member {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "MEMBER_ID")
//    private Long id;
//    @Column(length = 10)
//    private String name;
//
//    private String city;
//    private String street;
//    private String zipcode;
//
////    /**
////     * 프록시 객체 조회하게 만드는 것 Member 클래스만 DB에서 조회
////     *  @ManyToOne(fetch = FetchType.LAZY)
////     */
////    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "TEAM_ID")
////    private Team team;
//
//    /**
//     *  즉시로딩
//     *  JPA 는 가능하면 조인해서 한번에 들고오는 것이 좋다.
//     *  다만, 설명을 참조하면 알듯이 즉시로딩은 지양하는 것 이 좋다.
//     *  @ManyToOne(fetch = FetchType.EAGER)
//     */
//    @ManyToOne(fetch = FetchType.EAGER)
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getStreet() {
//        return street;
//    }
//
//    public void setStreet(String street) {
//        this.street = street;
//    }
//
//    public String getZipcode() {
//        return zipcode;
//    }
//
//    public void setZipcode(String zipcode) {
//        this.zipcode = zipcode;
//    }
//
//    public Team getTeam() {
//        return team;
//    }
//
//    public void setTeam(Team team) {
//        this.team = team;
//    }
//}
