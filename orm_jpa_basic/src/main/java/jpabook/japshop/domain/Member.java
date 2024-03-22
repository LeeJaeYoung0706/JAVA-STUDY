//package jpabook.japshop.domain;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Entity
//public class Member {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "MEMBER_ID")
//    private Long id;
//    @Column(length = 10)
//    private String name;
//
//    /**
//     * 예제 상으로 양방향 예를 들 뿐, 설계상으로 Order를 가지고 있으면 안됨.
//     */
//    @OneToMany(mappedBy = "member")
//    private List<Order> orders = new ArrayList<>();
//
//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;
//    private String city;
//    private String street;
//    private String zipcode;
//
////    /**
////     * 조인 테이블 생성
////     * 다대다이기 때문에 조인 테이블을 만들고,
////     * name = 조인 테이블 이름을 만든다.
////     */
////    @ManyToMany
////    @JoinTable(name = "MEMBER_PRODUCT")
////    private List<Product> products = new ArrayList<>();
//
//
//    /**
//     * 조인 테이블 생성
//     * 다대다이기 때문에 조인 테이블을 만들고,
//     * name = 조인 테이블 이름을 만든다.
//     */
//    @OneToMany(mappedBy = "member")
//    private List<MemberProduct> mebmerProducts = new ArrayList<>();
//
//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
//    }
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
//}
