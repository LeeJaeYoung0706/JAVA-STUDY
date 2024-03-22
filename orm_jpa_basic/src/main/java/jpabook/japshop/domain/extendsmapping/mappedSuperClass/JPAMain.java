//package jpabook.japshop.domain.extendsmapping.mappedSuperClass;
//
//import jpabook.japshop.domain.extendsmapping.entity.extendsentity.Movie;
//import jpabook.japshop.domain.extendsmapping.mappedSuperClass.entity.Actor;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//
//public class JPAMain {
//    public static EntityManagerFactory entityManagerFactory =  Persistence.createEntityManagerFactory("jpaBook");
//    public static void main(String[] args) {
//
//       EntityManager entityManager = entityManagerFactory.createEntityManager();
//       EntityTransaction entityTransaction = entityManager.getTransaction();
//
//       entityTransaction.begin();
//
//       try {
//           Actor actor = new Actor();
//           actor.setName("1");
//           actor.setAge("1");
//           actor.setCity("안양");
//
//           entityManager.persist(actor);
//
//           entityManager.flush();
//           entityManager.clear();
//
//           /**
//            *     create table User (
//            *        id bigint not null,
//            *         age varchar(255),
//            *         city varchar(255),
//            *         name varchar(255),
//            *         primary key (id)
//            *     ) engine=InnoDB
//            */
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
