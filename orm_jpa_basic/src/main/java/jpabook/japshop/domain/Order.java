//package jpabook.japshop.domain;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * 객체 맵핑
// * 방향 = > 단방향 , 양방향
// * 1. 연관관계의 주인 중요
// * 2. 객체 양방향 연관관계는 관리인이 필요
// * 3. 다중성
// *
// * 관계와 객체 에 따른 이해가 필요하다.
// *
// * 비지니스 로직상
// * Member - Order 의 연관관계를 가질 때
// * Member 를 찾아서 오더를 찾게 되면 관심사에 대한 설계가 잘 못 됐다고 생각된다.
// * Member 는 Member 만을 가지고 있고,
// * Member 가 Order에 대한 정보를 가지고 있지 않아도 된다.
// * Order가 중요한 비지니스 로직이 될 경우에
// */
//@Entity
//@Table(name="ORDERS")
//public class Order {
//    @Id @GeneratedValue
//    @Column(name = "ORDER_ID")
//    private Long id;
//    /**
//     * 연관관계 맵핑
//     * JoinColumn 설정
//     * 연관관계의 주인
//     * 외래키 MEMBER_ID 를 관리한다고 지정을 했음.
//     *
//     */
//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;
//    /**
//     * 외래키인 ORDER_ID 가 잡힌 애를
//     * mappedBy 잡아준다 변수명을 작성해야함.
//     *
//     */
//    @OneToMany(mappedBy = "order")
//    private List<OrderItem> orderItems = new ArrayList<>();
//    private LocalDateTime orderDate;
//    @Enumerated(EnumType.STRING)
//    private OrderStatus status;
//
//    /**
//     * 양방향 맵핑을 위해서 반대편도 셋을 해주는 작업
//     * @param orderItem
//     */
//    public void addOrderItem(OrderItem orderItem){
//        orderItems.add(orderItem);
//        orderItem.setOrder(this);
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
//    public OrderStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(OrderStatus status) {
//        this.status = status;
//    }
//
//    public Member getMember() {
//        return member;
//    }
//
//    public void setMember(Member member) {
//        this.member = member;
//    }
//
//    public LocalDateTime getOrderDate() {
//        return orderDate;
//    }
//
//    public void setOrderDate(LocalDateTime orderDate) {
//        this.orderDate = orderDate;
//    }
//
//    public List<OrderItem> getOrderItems() {
//        return orderItems;
//    }
//
//    public void setOrderItems(List<OrderItem> orderItems) {
//        this.orderItems = orderItems;
//    }
//}
