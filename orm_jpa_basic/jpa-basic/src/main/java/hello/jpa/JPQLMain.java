package hello.jpa;

import hello.jpa.entity.Member;
import hello.jpa.entity.ObjectAndTableMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class JPQLMain extends JpaMain {

    public static void main(String[] args) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        try {
            /**
             * 대상이 테이블이 아닌 객체 paging 처리 할 때 편하게 할 수 있도록 메리트가 있음.
             */
            List<Member> result = entityManager.createQuery("select m from Member as m" , Member.class)
                    .setFirstResult(1)
                    .setMaxResults(10)
                            .getResultList();

            for (Member member : result){
                System.out.println("id = " + member.getId());
                System.out.println("name = " + member.getName());
            }

//            Member member1 = new Member(3L , "test3");
//            Member member2 = new Member(4L , "test4");
//            entityManager.persist(member1);
//            entityManager.persist(member2);
//
//            System.out.println("member2 = " + member2);
//            System.out.println("member1 = " + member1);

            /**
             * 컬렉션처럼 돌아가기 때문에 펄시스턴트를 다시 하지않아도 가능하다. 따라서
             * 객체 값만 변경이 된다면 디비도 변경이 됨. 물론 커밋을 해야하는데,
             * 커밋이 되기 전에 플러시를 호출하고
             * 앤티티 - 스냅샷 비교한다.
             * 플러시란 , 영속성 컨텍스트에서 변경된 값을 디비에 반영하는 역할을 한다.
             * 스냅샷이란 , 1차 캐시에 들어왔을 때의 값을 저장해 둔 것.
             *
             * 그런 뒤 커밋이 실행이 된다.
             *
             */
            ObjectAndTableMapping findMember = entityManager.find(ObjectAndTableMapping.class , 3L);
//            System.out.println("findMember = " + findMember.getName());
//            findMember.setName("changeName2");
            Member findMember2 = entityManager.find(Member.class , 3L);

            System.out.println("findMember2 = " + findMember2.getName());
            // 여기서 버퍼링 가능

            // 이런식으로 처리를 하면 안된다.
//            if(findMember.getName().equals("changeName2")){
//                entityManager.persist(findMember);
//            }

            entityTransaction.commit();
        } catch (Exception e){
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }

        entityManagerFactory.close();
    }
}
