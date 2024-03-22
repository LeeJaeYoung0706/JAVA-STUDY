//package jpabook.japshop.domain;
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
////            Team team = new Team();
////            team.setName("team1");
////            entityManager.persist(team);
////
////            Member1 member = new Member1();
////            member.changeSetTeam(team);
////            member.setUserName("member1");
////            entityManager.persist(member);
////
////
////            entityManager.flush();
////            entityManager.clear();
////
////            Member1 findMember = entityManager.find(Member1.class , member.getId());
////
////            Team getTeam = findMember.getTeam();
////            System.out.println("getTeam = " + getTeam.getName());
////
////            List<Member1> members = getTeam.getMembers();
////            for(Member1 member1 : members) {
////                System.out.println("members = " + member1.getUserName());
////            }
//
////           Order order = new Order();
////           //order.addOrderItem(new OrderItem());
////
////           /**
////            * 단방향으로 만들어도 문제는 없으나 개발의 편의를 위해 만드는 것
////            * 객체지향을 위해 양방향을 거는 것일 뿐,
////            * 핵심은 단방향 설계를 얼마나 잘하는가,
////            *
////            */
////           OrderItem orderItem = new OrderItem();
////           orderItem.setOrder(order);
////
////           entityManager.persist(orderItem);
////
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
