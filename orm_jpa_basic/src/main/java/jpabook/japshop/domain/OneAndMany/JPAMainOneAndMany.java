package jpabook.japshop.domain.OneAndMany;

import jpabook.japshop.domain.JPAMain;
import jpabook.japshop.domain.OneAndMany.entity.Member;
import jpabook.japshop.domain.OneAndMany.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JPAMainOneAndMany extends JPAMain {

    public static void main(String[] args) {

       EntityManager entityManager = entityManagerFactory.createEntityManager();
       EntityTransaction entityTransaction = entityManager.getTransaction();

       entityTransaction.begin();

       try {

           Member member = new Member();
           member.setUserName("member1");
           entityManager.persist(member);

           Team team = new Team();
           team.setName("TeamA");

           /**
            * 이렇게 되면 업데이트문이 한번 더 나가게 된다.
            * 아렇게 되면
            * 1. 성능상의 문제 다만, 크게 문제가 되지는 않는다.
            * 2. 해당 객체의 테이블이 아닌 다른 테이블을 조작함으로써 문제가 발생할 수 있다.
            * @개념설명 참조
            */
           team.getMembers().add(member);


           entityManager.persist(team);


           entityTransaction.commit();
       } catch (Exception e){
           entityTransaction.rollback();
       } finally {
           entityManager.close();
       }

       entityManagerFactory.close();
    }
}
