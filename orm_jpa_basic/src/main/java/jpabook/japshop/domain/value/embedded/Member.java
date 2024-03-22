//package jpabook.japshop.domain.value.embedded;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//public class Member {
//
//    @Id
//    @GeneratedValue
//    @Column(name = "MEMBER_ID")
//    private Long id;
//    @Column(name = "USERNAME")
//    private String userName;
//
//    //기간 Period
//    @Embedded
//    private Period workPeriod;
//
//    //주소
//    @Embedded
//    private Address homeAddress;
//
//    /**
//     *     @Embedded
//     *     private Address workAddress;
//     *     넣으면 에러
//     *     Caused by: javax.persistence.PersistenceException: [PersistenceUnit: jpaBook] Unable to build Hibernate SessionFactory
//     * 	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.persistenceException(EntityManagerFactoryBuilderImpl.java:1016)
//     * 	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:942)
//     * 	at org.hibernate.jpa.HibernatePersistenceProvider.createEntityManagerFactory(HibernatePersistenceProvider.java:56)
//     * 	 이때 넣어야하는 건
//     *
//     *                @AttributeOverrides({
//     *             @AttributeOverride(name = "city" , column = @Column(name = "WORK_CITY")),
//     *             @AttributeOverride(name = "street" , column = @Column(name = "WORK_STREET")),
//     *             @AttributeOverride(name = "zipCode" , column = @Column(name = "WORK_ZIPCODE"))
//     *     })
//     *     이거고
//     *
//     *     이것에 따른 테이블 생성 코드는
//     *        create table Member (
//     *        MEMBER_ID bigint not null,
//     *         city varchar(255),
//     *         street varchar(255),
//     *         ZIPCODE varchar(255),
//     *         USERNAME varchar(255),
//     *         WORK_CITY varchar(255),
//     *         WORK_STREET varchar(255),
//     *         WORK_ZIPCODE varchar(255),
//     *         endDate datetime,
//     *         startDate datetime,
//     *         primary key (MEMBER_ID)
//     *     ) engine=InnoDB
//     *
//     *     이렇게 되며, 컬럼이 추가 된다.
//     *
//     */
//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "city" , column = @Column(name = "WORK_CITY")),
//            @AttributeOverride(name = "street" , column = @Column(name = "WORK_STREET")),
//            @AttributeOverride(name = "zipCode" , column = @Column(name = "WORK_ZIPCODE"))
//    })
//    private Address workAddress = null;
//
//    public Member() {
//    }
//
//    public Member(Long id, String userName, Period workPeriod, Address homeAddress) {
//        this.id = id;
//        this.userName = userName;
//        this.workPeriod = workPeriod;
//        this.homeAddress = homeAddress;
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
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public Period getWorkPeriod() {
//        return workPeriod;
//    }
//
//    public void setWorkPeriod(Period workPeriod) {
//        this.workPeriod = workPeriod;
//    }
//
//    public Address getHomeAddress() {
//        return homeAddress;
//    }
//
//    public void setHomeAddress(Address homeAddress) {
//        this.homeAddress = homeAddress;
//    }
//}
