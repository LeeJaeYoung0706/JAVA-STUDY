package jpabook.japshop.domain.lazyloading;

public class 설명 {

/**
 * Member  --- Team  객체에서
 *
 *     @ManyToOne(fetch = FetchType.LAZY)
 * 넣게 된다면,
 * LAZY 로 적용되고 Team 객체를 DB가 아닌 Proxy 객체로 대체함
 *
 * 단,
 * Team team = member.getTeam();
 * team.getName(); 초기화 할 때
 * 즉 사용할 때 DB 쿼리가 날라가게 됩니다.
 *
 * getTeam 이 아니라
 * team.getName 일 때 쿼리가 날라가는거에요 초기화 할 때처럼
 * target의 값을 가져올 때 초기화가 일어납니다.
 *
 *
 * Member , Team 을 함계 조회하는 것이 많다면,
 *  @ManyToOne(fetch = FetchType.EAGER)
 *
 *  즉시로딩
 *
 *
 *   *** 매우 중요 ***
 *
 *   * 실무에서는 가급적 지연 로딩만 사용할 것.
 *   * 즉시 로딩을 적용하면 예상하지 못한 SQL 이 작동
 *   * 즉시 로딩은 JPQL 에서 N + 1 문제를 일으킨다.
 *      - 이게 무슨 말이냐면,
 *      @JPAMain 참조
 *
 * @ManyToOne , @OneToOne 은 기본이 즉시로딩이기 때문에 LAZY 처리를 해야하고
 * @OneToMany , @ManyToMany  기본이 지연로딩이라 설정하지 않아도 됨
 */
}
