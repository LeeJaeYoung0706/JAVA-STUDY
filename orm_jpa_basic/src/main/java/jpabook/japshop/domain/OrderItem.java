//package jpabook.japshop.domain;
//
//import javax.persistence.*;
//
//@Entity
//public class OrderItem {
//
//    @Id @GeneratedValue
//    @Column(name = "ORDER_ITEM_ID")
//    private Long id;
////    @Column(name = "ORDER_ID")
////    private Long orderId;
////    @Column(name = "ITEM_ID")
////    private Long itemId;
//    /**
//     * 연관관계 맵핑
//     * JoinColumn 설정
//     * 단방향으로 설계 할 것,
//     */
//    @ManyToOne
//    @JoinColumn(name = "ORDER_ID")
//    private Order order;
//    /**
//     * 연관관계 맵핑
//     * JoinColumn 설정
//     * 단방향으로 설계 할 것,
//     */
//    @ManyToOne
//    @JoinColumn(name = "ITEM_ID")
//    private Item item;
//
//    @Column(name = "ORDER_PRICE")
//    private int orderPrice;
//    private int count;
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
//
//    public Item getItem() {
//        return item;
//    }
//
//    public void setItem(Item item) {
//        this.item = item;
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
//    public int getOrderPrice() {
//        return orderPrice;
//    }
//
//    public void setOrderPrice(int orderPrice) {
//        this.orderPrice = orderPrice;
//    }
//
//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
//}
