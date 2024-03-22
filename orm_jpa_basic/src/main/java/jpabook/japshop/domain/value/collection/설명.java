package jpabook.japshop.domain.value.collection;

public class 설명 {

    /**
     *   예제 테이블
     *
     *   Member
     *   id : Long
     *   favoriteFoods : Set<String>
     *   addressHistory : List<Address>
     *
     *   FAVORITE_FOOD
     *   Member_id :  pk , fk
     *   food_name : pk
     *
     *   ADDRESS
     *   member_id : pk. fk
     *   city : pk
     *   street : pk
     *   zipCode : pk
     *
     *   구조가 이렇다고 할 때
     *   를 가정하고 만들어 보자.
     *   다 묶어서 pk 를 지정하는 이유는
     *   값 타입이기 때문에 다 묶어서 pk 를 걸어주어야함.
     *   묶어서 pk 로 지정중요
     *   라이프 사이클을 공유하기 때문에 영속성 전이 와 고아 객체를 같이 가지고 있음.*
     *
     *
     *  1 : N 으로 풀고 그냥
     *  객체로 만드세요
     *  컬렉션 타입 ㄴㄴ
     *
     */
}
