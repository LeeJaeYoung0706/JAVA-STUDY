//package jpabook.japshop.domain.ManyAndOne;
//
//import jpabook.japshop.domain.JPAMain;
//import jpabook.japshop.domain.OneAndMany.entity.Member;
//import jpabook.japshop.domain.OneAndMany.entity.Team;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
//
//public class JPAMainManyAndOne extends JPAMain {
//
//    public static void main(String[] args) {
//
//       EntityManager entityManager = entityManagerFactory.createEntityManager();
//       EntityTransaction entityTransaction = entityManager.getTransaction();
//
//       entityTransaction.begin();
//
//       try {
//
//
//
//           entityTransaction.commit();
//       } catch (Exception e){
//           entityTransaction.rollback();
//       } finally {
//           entityManager.close();
//       }
//
//       entityManagerFactory.close();
//    }
//}
