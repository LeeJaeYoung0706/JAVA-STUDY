package jpabook.japshop.domain.cascade;

public class 설명 {
/**
 * CASCADE 의 종류
 *
 * 1. ALL : 모두 적용 : 모든 라이프 사이클을 사용
 * 2. PERSIST : 영속 : 저장만 할 때 라이프 사이클 맞출것.
 * 3. REMOVE : 삭제
 * 4. MERGE : 병합
 * 5. REFRESH : Refresh
 * 6. DETACH : Detach
 *
 * 게시판 - 첨부파일 같은 경우에는 활용 가능하지만,
 * 파일을 다른 곳에서도 관리한다면 사용하면 안됨.
 * 다른 연관관계에 child 가 있다면 활용하면 안됨.
 * 소유자가 1개 일때만 사용가능
 *
 */
}
