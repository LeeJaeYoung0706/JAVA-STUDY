package jpabook.japshop.domain.ManyAndOne.entity.양방향;

import jpabook.japshop.domain.ManyAndOne.entity.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name="TEAM_ID")
    private Long id;
    private String name;
    /**
     * 양방향 설계시 조회만 가능한 반대편 사이드
     */
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();

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
