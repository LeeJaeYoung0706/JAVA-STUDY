package hello.jpa;

import hello.jpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class flush extends JpaMain{

    /**
     *
     * 플러시란
     * 1. 변경을 감지하고
     * 2. 수정된 엔티티 쓰기지연
     * 3. 쓰기지연 저장소 SQL 을 DB 에 전송
     * 직접 호출
     * entityManager.flush(); <- 직접 호출  TEST 코드 작성시 필수
     * entityTransaction.commit(); <- 자동 호출
     * JPQL 실행 <- 자동 호출
     *
     * 영속성 컨텍스트의 변경 내용을 데이터베이스와 동기화라고 생각하면 편하다.
     *
     * */

    /**
     * 플러시를 한다고 해서 1차 캐시에 저장되어 있는 애들이 지워지지는 않는다.
     * @param args
     */
    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();

        try {

            Member member = new Member( 201L , "flushTest");
            em.persist(member);

            /**
             * 강제 호출
             */
            em.flush();
            System.out.println("--------------------------");
            /**
             * 결과를 확인하게 되면,
             * 커밋 보다 쿼리가 먼저 나가는 것을 확인 할 수 있따.
             */
            et.commit();

        } catch (Exception e){
            et.rollback();
        } finally {
            em.close();
        }

        entityManagerFactory.close();
    }


    /**
     * flushMode Option
     *
     * em.setFlushMode(FlushModeType.COMMIT);
     * 커밋 할 때만 플러쉬
     *
     * em.setFlushMode(FlushModeType.AUTO);
     * 커밋이나 쿼리 시 플러쉬
     *
     * 구냥 오토 쓰십쇼
     */

}
