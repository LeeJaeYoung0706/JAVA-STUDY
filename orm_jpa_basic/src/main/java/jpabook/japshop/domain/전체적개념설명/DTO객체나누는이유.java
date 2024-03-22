package jpabook.japshop.domain.전체적개념설명;

public class DTO객체나누는이유 {

    /**
     * Entity 객체는 Controller 로 보내면 안되기 때문에 나누는 것인데,
     *
     * 그 이유에 대해 설명하자면,
     * ToString 으로 서로 호출하여 무한 루프에 갇히거나,
     * Request에 대한 요청을 처리하면서 JSON 을 호출할 때 문제가 발생하기 때문인데,
     * 이 때문에
     * Request , Response , Domain 을 나누어서 DTO 객체를 따로 관리해야 한다.
     *
     */
}
