package jpabook.japshop.domain.OneAndOne.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {

    @Id @GeneratedValue
    private Long id;
    private String lockerName;

    /**
     * 읽기 전용 Member
     * 양방향
     *
     */
    @OneToOne(mappedBy = "locker")
    private Member member;
}
