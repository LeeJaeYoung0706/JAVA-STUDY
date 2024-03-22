//package jpabook.japshop.domain.mappingExample.entity;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class Item {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "ITEM_ID")
//    private Long id;
//    private String name;
//    private int price;
//    private int stockQuantity;
//
//    /**
//     * 다대다 양방향을 위한 조회용 mappedBy 설정
//     */
//    @ManyToMany(mappedBy = "items")
//    private List<Category> categories = new ArrayList<>();
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
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//
//    public int getStockQuantity() {
//        return stockQuantity;
//    }
//
//    public void setStockQuantity(int stockQuantity) {
//        this.stockQuantity = stockQuantity;
//    }
//
//    public List<Category> getCategories() {
//        return categories;
//    }
//
//    public void setCategories(List<Category> categories) {
//        this.categories = categories;
//    }
//}
