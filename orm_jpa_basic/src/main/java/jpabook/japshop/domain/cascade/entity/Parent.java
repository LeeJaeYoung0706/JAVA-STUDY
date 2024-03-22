package jpabook.japshop.domain.cascade.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String name;

    @OneToMany(mappedBy = "parent" , cascade = CascadeType.ALL)
    private List<Child> childList = new ArrayList<>();

    /**
     * 양방향 연관관계
     * @param child
     */
    public void addChild(Child child){
        childList.add(child);
        child.setParent(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
