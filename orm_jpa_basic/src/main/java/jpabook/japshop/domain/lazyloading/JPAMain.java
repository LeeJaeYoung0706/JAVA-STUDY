//package jpabook.japshop.domain.lazyloading;//package jpabook.japshop.domain.extendsmapping;
//
//import jpabook.japshop.domain.lazyloading.entity.Member;
//import jpabook.japshop.domain.lazyloading.entity.Team;
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
//           Team team = new Team();
//           entityManager.persist(team);
//
//           Member member = new Member();
//           member.setName("testMember");
//           entityManager.persist(member);
//           member.setTeam(team);
//
//           entityManager.flush();
//           entityManager.clear();
//
////           /**
////            *
////            *   LAZY
////            *       프록시 객체 조회하게 만드는 것 Member 클래스만 DB에서 조회
////            *       @ManyToOne(fetch = FetchType.LAZY)
////            *
////            * Hibernate:
////            *     select
////            *         member0_.MEMBER_ID as MEMBER_I1_0_0_,
////            *         member0_.city as city2_0_0_,
////            *         member0_.name as name3_0_0_,
////            *         member0_.street as street4_0_0_,
////            *         member0_.TEAM_ID as TEAM_ID6_0_0_,
////            *         member0_.zipcode as zipcode5_0_0_
////            *     from
////            *         Member member0_
////            *     where
////            *         member0_.MEMBER_ID=?
////            *      Team 셋팅 안되면 널포인트 익셉션
////            *      java.lang.NullPointerException
////            * 	at jpabook.japshop.domain.lazyloading.JPAMain.main(JPAMain.java:49)
////            * 10월 17, 2022 10:43:23 오전 org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl stop
////            * INFO: HHH10001008: Cleaning up connection pool [jdbc:mysql://219.248.82.98:12304/dev_test?&serverTimezone=UTC&autoReconnect=true&allowMultiQueries=true&characterEncoding=UTF-8]
////            *
////            *
////            *  Team team = new Team();
////            *            entityManager.persist(team);
////            *            넣으면
////            *            m.getTeam().getClass() = class jpabook.japshop.domain.lazyloading.entity.Team$HibernateProxy$KF8XmOy8
////            *            프록시에서 들고옴
////            *
////            *
////            *
////            *
////            *
////            *  EAGER
////            *     select
////            *         member0_.MEMBER_ID as MEMBER_I1_0_0_,
////            *         member0_.city as city2_0_0_,
////            *         member0_.name as name3_0_0_,
////            *         member0_.street as street4_0_0_,
////            *         member0_.TEAM_ID as TEAM_ID6_0_0_,
////            *         member0_.zipcode as zipcode5_0_0_,
////            *         team1_.TEAM_ID as TEAM_ID1_1_1_,
////            *         team1_.name as name2_1_1_
////            *     from
////            *         Member member0_
////            *     left outer join
////            *         Team team1_
////            *             on member0_.TEAM_ID=team1_.TEAM_ID
////            *     where
////            *         member0_.MEMBER_ID=?
////            *
////            *    바로 초기화하기 때문에 team.getName 을 호출 할 때 그냥 호출됨.
////            *    초기화를 다시 하지않음.
////            *
////            */
////           Member m = entityManager.find(Member.class , member.getId());
////           System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass());
//
//           /**
//            * 즉시로딩 문제 N + 1 예제
//            *
//            *
//                    Hibernate:
//                    select
//                    member0_.MEMBER_ID as MEMBER_I1_0_,
//                    member0_.city as city2_0_,
//                    member0_.name as name3_0_,
//                    member0_.street as street4_0_,
//                    member0_.TEAM_ID as TEAM_ID6_0_,
//                    member0_.zipcode as zipcode5_0_
//                    from
//                    Member member0_
//                    Hibernate:
//                    select
//                    team0_.TEAM_ID as TEAM_ID1_1_0_,
//                    team0_.name as name2_1_0_
//                    from
//                    Team team0_
//                    where
//                    team0_.TEAM_ID=?
//
//                    셀렉 결과가 2개인데,
//                    즉시로딩 -> 값이 무조건 다 가져와야하는데,
//                    Memeber 만큼 쿼리를 가져와야하기 때문에,
//                    select  * from member;
//                    가져 왔는데, Team 도 즉시로딩으로 가져와야하네? 하면서
//                    select * from team where TEAM_ID = ""; member 의 값
//                    해서 또 나갑니다.
//
//                    성능 저하 이슈가 생긴다.
//
//            기본쿼리( select  * from member; ) 1 + N 개의 즉시 쿼리(   select * from team where TEAM_ID = ""; )가 된다고 생각하면 된다.
//            하지만 LAZY 로 잡으면 1개의 쿼리만 날림
//
//
//            */
////           List<Member> members = entityManager.createQuery("select m from Member m ", Member.class).getResultList();
//           /**
//            * 실무용 조인 fetch join
//            *
//            * 결과 값
//            *          select
//            *             member0_.MEMBER_ID as MEMBER_I1_0_0_,
//            *             team1_.TEAM_ID as TEAM_ID1_1_1_,
//            *             member0_.city as city2_0_0_,
//            *             member0_.name as name3_0_0_,
//            *             member0_.street as street4_0_0_,
//            *             member0_.TEAM_ID as TEAM_ID6_0_0_,
//            *             member0_.zipcode as zipcode5_0_0_,
//            *             team1_.name as name2_1_1_
//            *         from
//            *             Member member0_
//            *         inner join
//            *             Team team1_
//            *                 on member0_.TEAM_ID=team1_.TEAM_ID
//            */
//           List<Member> members = entityManager.createQuery("select m from Member m join fetch m.team", Member.class).getResultList();
//           entityTransaction.commit();
//       } catch (Exception e){
//           entityTransaction.rollback();
//
//           e.printStackTrace();
//       } finally {
//           entityManager.close();
//       }
//
//       entityManagerFactory.close();
//    }
//}
