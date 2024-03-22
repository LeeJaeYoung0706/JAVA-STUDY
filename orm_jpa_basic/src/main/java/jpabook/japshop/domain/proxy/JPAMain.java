package jpabook.japshop.domain.proxy;//package jpabook.japshop.domain.extendsmapping;

import jpabook.japshop.domain.proxy.entity.Member;

import javax.persistence.*;
import java.util.List;

public class  JPAMain {
    public static EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("jpaBook");
    public static void main(String[] args) {

       EntityManager entityManager = entityManagerFactory.createEntityManager();
       EntityTransaction entityTransaction = entityManager.getTransaction();

       entityTransaction.begin();

       try {
           Member member = new Member();
           member.setName("hello1");
           entityManager.persist(member);

           Member member2 = new Member();
           member.setName("hello2");
           entityManager.persist(member2);

           entityManager.flush();
           entityManager.clear();

           // 영속성 컨텐츠 재시작

//           Member findMember = entityManager.find(Member.class , member.getId());
//           System.out.println("findMember = " + findMember.getName());
//           System.out.println("findMember.getId() = " + findMember.getId());
           /**
            * Hibernate:
            *     select
            *         member0_.MEMBER_ID as MEMBER_I1_9_0_,
            *         member0_.city as city3_9_0_,
            *         member0_.name as name6_9_0_,
            *         member0_.street as street4_9_0_,
            *         member0_.TEAM_ID as TEAM_ID7_9_0_,
            *         member0_.zipcode as zipcode5_9_0_,
            *         team1_.TEAM_ID as TEAM_ID1_13_1_,
            *         team1_.name as name2_13_1_
            *     from
            *         Member member0_
            *     left outer join
            *         Team team1_
            *             on member0_.TEAM_ID=team1_.TEAM_ID
            *     where
            *         member0_.MEMBER_ID=?
            */

           ///////////////////////////////////////////
           /**
            * hibernate 가 가짜 entity 를 주게 됨
            * Proxy 텅텅빈 가짜를 준다.
            * 실제 entity를 상속 받은 proxy
            *
            *
            *
            */
           Member findMember1 = entityManager.getReference(Member.class, member.getId());
           Member findMember2 = entityManager.getReference(Member.class, member2.getId());

           Member findMember3 = entityManager.find(Member.class , member2.getId());

           /**
            * 프록시로 한번 선언하고 나면 find 를 해도 프록시로 리턴함
            * 그렇기 때문에 트루임
            */
           System.out.println(" Type Refer , entity 비교 = " +  (findMember1.getClass() == findMember3.getClass())); // true
           System.out.println(" Type 비교 = " +  (findMember1 == findMember2)); // true
           System.out.println(" Type Refer , entity 비교 = " +  (findMember1 == findMember3)); // false
           System.out.println(" Type Refer , entity 비교 instance = " +  (findMember1 instanceof Member )); // true
           System.out.println(" Type Refer , entity 비교 instance = " +  (findMember3 instanceof Member )); // true
//           System.out.println("findMember.getId() = " + findMember.getId());
//           /**
//            * 영속성 컨텍스트 요청 - > Member target 값이 생기고 -> 그 다음부턴 호출 하면 select 를 안함
//            */
//           System.out.println("findMember.getClass() = " + findMember.getClass());
//           System.out.println("findMember = " + findMember.getName());
//           System.out.println("findMember = " + findMember.getName());
           /**
            * 프록시 초기화 유틸
            * 초기화 안되면 = false
            * 초기화 되면 = true
            */
           /**
            *  초ㅓ기화
            */
//           System.out.println("findMember1.getName() = " + findMember1.getName());
//            boolean check = entityManagerFactory.getPersistenceUnitUtil().isLoaded(findMember1);
//           System.out.println("check = " + check);

           /**
            * 강제 초기화
            */
           org.hibernate.Hibernate.initialize(findMember1);

           boolean check1 = entityManagerFactory.getPersistenceUnitUtil().isLoaded(findMember1);
           System.out.println("check = " + check1);
           entityTransaction.commit();
       } catch (Exception e){
           entityTransaction.rollback();

           e.printStackTrace();
       } finally {
           entityManager.close();
       }

       entityManagerFactory.close();
    }
}
