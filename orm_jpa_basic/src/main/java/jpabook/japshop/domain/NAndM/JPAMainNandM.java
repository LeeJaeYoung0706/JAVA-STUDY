//package jpabook.japshop.domain.NAndM;
//
//import jpabook.japshop.domain.JPAMain;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//
//public class JPAMainNandM extends JPAMain {
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
