package hello.jpa;

import hello.jpa.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


/**
 *  영속상태 인 엔티티가 영속성 컨텍스트로 부트 분리가 되는 상태
 *
 */
public class Detach extends JpaMain{

    public static void main(String[] args) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = em.getTransaction();

        et.begin();
        try {
            /**
             *  em.find 라는 메소드를 활용했기 때문에
             *  영속성 컨텍스트에 이미 올라가 있는 상태
             */
            Member member = em.find(Member.class , 1L);
            member.setName("detachTEST");
            /**
             * 준영속 , JPA 에서 관리하지 않겠다.
             * 돌려 보면 업데이트 문이 나가지 않는다.
             */
            //em.detach(member);

            /**
             * em.clear();
             * 영속성 컨텍스트를 완전히 초기화
             * 예를 들면,
             */

            em.clear();

            Member member2 = em.find(Member.class , 1L);

            /**
             * 이렇게 되면 영속성 컨텍스트에 올리기 위해 2번 쿼리문을 날리게 되는 것을 확인할 수 있따.
             * 물론 TEST 코드를 작성할 때 편하고 좋음
             */
            et.commit();
        } catch (Exception e){
            et.rollback();
        } finally {
            /**
             * 영속성 컨텍스트 종료
             */
            em.close();
        }

        entityManagerFactory.close();
    }
}
