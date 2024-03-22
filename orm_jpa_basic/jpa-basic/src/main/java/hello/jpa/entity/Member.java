package hello.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *  DB Table 과 맵핑 해주기 위한 어노테이션
 */
@Entity
public class Member {

    /**
     * PK 가 무엇인지 알려주는 어노테이션
     * @return
     */
    @Id
    private Long id;
    /**
     * 컬럼 명이 다를 때 맞춰주는 역할
     */
    @Column(name = "name")
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public Member() {

    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
