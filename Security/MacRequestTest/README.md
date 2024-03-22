# MacRequestTest
Security의 Mac 요청 기법에 대해서 테스트했습니다.

SecretKey로 토큰 검증을 처리 합니다.
KeyCloak과 같은 인가 서버는 jwkUri로 검증하는 것과 다릅니다. 

SignatureConfig 는 서명, 검증, Mac 및 RSA 암호화 JWK등의 빈을 생성합니다.
토큰 발행 필터와 토큰 검증 필터 2가지가 있습니다. 발행을 할 경우 UsernamePasswordAuthenticationFilter를 상속받습니다. 

Signer
토큰을 발행하는 추상 클래스
