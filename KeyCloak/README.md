KeyCloak 구현 시 테스트 한 코드들 


yml

spring:
  security:
    oauth2:
      client:   # prefix
        registration:    # 클라이어트 설정
          keycloak:    # OAuth2ClientProperties -> Map 의 Key 에 들어가게 됨.
            authorization-grant-type: authorization_code  # 권한 부여 요청을 할 때 어떤 거, password , 클라이언트 크레덴셜셜            client-id: keti-client-test-app # ??? ???? ??? ????? ???
            client-name: test # 클라이언트 이름
            client-id: test # 클라이언트 아이디 서비스 곱급자에 등록된 것
            client-secret: Trpb8jpg0ScwCa0S9v5WLkN7ItPjTLOa # 클라이언트 시크릿 키
            redirect-uri: http://localhost:8080/login/oauth2/code/keycloak # ???? ?? ?? ??? ?????? ?????
            client-authentication-method: client_secret_basic, client_secret_post, none # ????? ?? ?? ?? ??
            scope: openid,profile,email # ??? ?? ?? ??
        provider:   # 공급자 설정
          keycloak:   # OAuth2ClientProperties -> Map 의 Key 에 들어가게 됨.
            authorization-uri: http://localhost:8081/realms/test/protocol/openid-connect/auth # 코드 요청 엔드포인트
            issuer-uri: http://localhost:8081/realms/test # 서비스 공급자 위치 -> 메타데이터들을 가져올 수 있음.
            jwk-set-uri: http://localhost:8081/realms/test/protocol/openid-connect/certs # jwkSetUri OAuth2 jwk 엔드 포인트 클라이언트 -> 리소스 서버 데이터 요청 -> 억세스 토큰 전달 -> 토큰 검증 -> 인가서버에서 서명 되어 있는 상태 -> 서명된 토큰 검증 -> 퍼블릭키로 검증 -> 엔드포인트로 요청하면 -> 퍼블릭 키를 받기위한 유알엘
            token-uri: http://localhost:8081/realms/test/protocol/openid-connect/token # token
            user-info-uri: http://localhost:8081/realms/test/protocol/openid-connect/userinfo # userinfo -> 클라이언트 -> 사용자 대신해서 권한 부여 -> 인증처리를 위해
            user-name-attribute: preferred_username # ??? ? ?? ???
#
keycloak:
  enabled: true
  realm: ## realm 이름
  auth-server-url: ## KeyCloak 서버 url
  ssl-required: ## SSL 사용
  resource: ## 클라이언트 이름
  credentials:
    secret: ## 클라이언트 비밀번호
  use-resource-role-mappings: true # 롤 권한
  bearer-only: true # bearer 토큰 요청 허가 여부
