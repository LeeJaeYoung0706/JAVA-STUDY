//package jpabook.japshop.domain.extendsmapping;
//
//import jpabook.japshop.domain.extendsmapping.entity.Items;
//import jpabook.japshop.domain.extendsmapping.entity.extendsentity.Movie;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.EntityTransaction;
//import javax.persistence.Persistence;
//import java.util.List;
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
//           Movie movie = new Movie();
//           movie.setDirector("감독");
//           movie.setActor("배우");
//           movie.setName("이름");
//           movie.setPrice(10000);
//
//           entityManager.persist(movie);
//
//           entityManager.flush();
//           entityManager.clear();
//
//
//           Movie movie1 = entityManager.find(Movie.class , movie.getId());
//           /**
//            *   &&
//            *  Items items = entityManager.find(Items.class , movie.getId());
//            *  유니온 올 을 써서 다 찾아야해서 성능 적인 문제, 쿼리의 복잡함 문제가 생김
//            */
//
//           System.out.println("movie1 = " + movie1);
//
//           /**
//            * 이런식으로 상속관계가 있다면 알아서 조인해서 들고옴
//            *
//            * Hibernate:
//            *     select
//            *         movie0_.id as id1_6_0_,
//            *         movie0_1_.name as name2_6_0_,
//            *         movie0_1_.price as price3_6_0_,
//            *         movie0_.actor as actor1_11_0_,
//            *         movie0_.director as director2_11_0_
//            *     from
//            *         Movie movie0_
//            *     inner join
//            *         Items movie0_1_
//            *             on movie0_.id=movie0_1_.id
//            *     where
//            *         movie0_.id=?
//            * movie1 = jpabook.japshop.domain.extendsmapping.entity.extendsentity.Movie@383864d5
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
