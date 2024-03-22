//package jpabook.japshop.domain.OneAndOne;
//
//import jpabook.japshop.domain.JPAMain;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
//
//
//public class JPAMainOneAndOne extends JPAMain {
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
