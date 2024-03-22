package hello.jpa;

import hello.jpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * 가장 중요
 *
 * 1. JPA 데이터의 변경처리 즉 , 셀렉트 문을 제외한 것들은 트랜잭션 안에서 작동되어야 함.
 * 2. 앤티티 매니저 팩토리는 애플리케이션 내에서 1개만 존재하여야 함.
 * 3. 앤티티 매니저는 쓰레드간에 공유를 하면 안됨.
 */
public class JpaMain {

    /** DB 연결이 되고, 1개만 만들어야함.
     */
    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

    public static void main(String[] args) {


        /**
         * 기능 단위 처리를 할 때마다 한개 씩 생성
         */
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        /**
         * 트랜잭션 생성
         */
        EntityTransaction entityTransaction = entityManager.getTransaction();
        /**
         * 트랜잭션 시작
         */
        entityTransaction.begin();


//        /**
//         * 트랜잭션 처리
//         */
//        try {
//            Member member = new Member();
//
//            member.setId(2L);
//            member.setName("test2");
//            // 매니저에 저장 영속성 컨텍스트에 저장됨.
//            entityManager.persist(member);
//            /**
//             * 트랜잭션 커밋
//             */
//            entityTransaction.commit();
//        } catch (Exception e){
//            entityTransaction.rollback();
//        } finally {
//            // 생성된 매니저 닫기
//            // 무조건 닫아주셔야 합니다 처리 후에
//            entityManager.close();
//
//        }
//        entityManagerFactory.close();

        /**
         *  멤버 가져오기
         */

        try{
            Member findMember = entityManager.find(Member.class , 1L);
            System.out.println("findMember id = " + findMember.getId());
            System.out.println("findMember name = " + findMember.getName());


            /**
             *
             *   * update
             *      hello.jpa.entity.Member update
             *      Member
             *      set
             *      name =?
             *      where
             *      id =?
             *
             *      객체 셋 메소드로만 바로 변경이 가능한 이유는 트랜잭션 커밋을 할 때
             *      객체를 비교하고 업데이트문을 자동으로 날려주기 때문
             */
            findMember.setName("testJPA");
            /**
             *  삭제
             */
            //entityManager.remove(findMember);
            entityTransaction.commit();
        }catch (Exception e){
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();


    }
}
