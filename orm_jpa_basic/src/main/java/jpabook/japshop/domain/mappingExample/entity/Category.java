//package jpabook.japshop.domain.mappingExample.entity;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class Category {
//
//    @Id @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "CATEGORY_ID")
//    private Long id;
//    private String name;
//
//    /**
//     * 카테고리 쭈우우욱 내려가게 만드는 것
//     */
//    @ManyToOne
//    @JoinColumn(name = "PARENT_ID")
//    private Category parent;
//
//    @OneToMany(mappedBy = "parent")
//    private List<Category> child = new ArrayList<>();
//
//    /**
//     * Item 과 다대다
//     * 이러면 조인테이블 이 생성되니까,
//     * 생성한 것인데,
//     * JoinTable 이 생성되었을 때 FK 를 지정해주는 것과 같다.
//     * this 내 자신이 조인해야하는 컬럼은
//     *  joinColumns = @JoinColumn(name = "CATEGORY_ID")
//     * 반대편이 조인해야하는 컬럼은
//     *  inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
//     */
//    @ManyToMany
//    @JoinTable(name = "CATEGORY_ITEM" ,
//            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
//            inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
//    private List<Item> items = new ArrayList<>();
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
//    public Category getParent() {
//        return parent;
//    }
//
//    public void setParent(Category parent) {
//        this.parent = parent;
//    }
//
//    public List<Category> getChild() {
//        return child;
//    }
//
//    public void setChild(List<Category> child) {
//        this.child = child;
//    }
//
//    public List<Item> getItems() {
//        return items;
//    }
//
//    public void setItems(List<Item> items) {
//        this.items = items;
//    }
//}
