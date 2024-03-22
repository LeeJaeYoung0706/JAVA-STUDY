package hello.jpa;


/**
 * 영속성 컨텍스트에 대한 설명
 * 영속성 컨텍스트란 ?
 * 엔티티를 영구적으로 저장하는 환경
 * EntityManager.persist(Member)  <-- 객체를 엔티티 매니저에 저장한다는 것은 엔티티를 영속화 한다는것, DB에 저장하는 것이아니라 영속성 컨텍스트에 저장
 * EntityManager 는 영속성 컨텍스트에 접근하기 위한 매체 라고 생각하면 됨.
 *
 * 환경에 따라 다른데
 * J2SE - > EntityManager = PersistenceContext 1 대 1 매칭
 *
 * J2EE , SPRING = > EntityManager N = 1 PersistenceContext  다 대 1 매칭
 */
public class PersistenceContext {

    /**                                 웹 어플리케이션
     *                           |  요청
     *              사용자1   ---- | ---- >   EntityManager   <--- 생성             EntityManagerFactory ( 1개 존재 )
     *              사용자2   ---- | ---- >   EntityManager   <--- 생성
     *                           |                 |
     *                           |                 |  사용
     *                           |                 |
     *                           |                 V
     *                           |               커넥션풀  <--->  DB
     *                           |
     *
     *
     *
     */


    /**
     * 앤티티의 생명 주기
     * 비영속 - > 영속성 상태와 전혀 상관없는 상태
     * Member member = new Member();
     * member.setId(1);
     * member.setName("비영속");
     *
     * 객체만 생성한 상태 일 경우를 뜻함함     *
     *
     *
     * 영속 - > 영속성 컨텍스트에서 관리되는 상태
     *   Member member = new Member();
     *   member.setId(1);
     *   member.setName("비영속");
     *   EntityManager em = entityManagerFactory.createEntityManager();
     *   em.getTransaction().begin();
     *   객체를 저장한다. 영속상태로 만들기.
     *   em.persist(member);
     *
     * 객체를 생성하여 persist 한 상태. DB에는 저장되진 않음. 커밋해야 쿼리가 날라감
     *
     * 준영속 -> 영속성 컨텍스트에서 헤제
     *   Member member = new Member();
     *   member.setId(1);
     *   member.setName("비영속");
     *   EntityManager em = entityManagerFactory.createEntityManager();
     *   em.getTransaction().begin();
     *    영속상태에서 해제하기
     *   em.detach(member);
     *
     * 삭제 ->
     *  em.remove(member);
     */


    /**
     * JPA 를 쓰는 이유에 대해 장점.
     *
     * 1. 1차 캐시의 존재
     *
     * package - entity에 있는 Member 클래스를  확인해 보면
     * @Id 라는 값이 있을 겁니다. 그 값은 테이블의 기본키를 뜻하지만,
     * 1차캐시에서 객체를 맵핑하는데 사용하는 키 역할을 합니다 예를 들어,
     * 컬렉션 Map 과 비교하여 설명하자면,
     * Map 에 { 1 , member1 } , { 2 , member2 } 라는 것이 있을 때 1 이라는 키로 검색하면 member1 이라는 값을 찾을 수 있는 것처럼
     * key 값으로 설정된 값으로 객체를 찾을 수 있습니다. 여기서,
     * 1차 캐시에 그 값이 존재하게 된다면, JPA 에서는 디비가 아닌 1차 캐시에서 값을 도출하게 됩니다.
     *
     * 1차캐시에 저장하는 방법은
     * em.persist(member);
     *
     * 조회는
     * Member member1 = em.find( Member.class , 1L);
     *
     *
     * 1차캐시에 존재하지 않는다면, DB 조회를 해서 그 결과 값을 1차 캐시에 저장한 후 리턴합니다.
     *
     * 하지만 기능적인 단위로 처리하고 삭제하기 때문에 여러 처리할 경우에는 어렵다.
     *
     */
}
