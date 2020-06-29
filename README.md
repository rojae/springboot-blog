# springboot-blog [진행 중]
spring boot blog rojae with IntelliJ

___[블로그 개발] Spring data JPA, H2, lombok 사용 -> 글 추가, 리스트로 확인, 글 상세 보기___

HTTP 2.0

SSL 인증서 사용

social security -> security oauth2.0 (진행)

*** 
시작 페이지 

[추가] 글 수정, 삭제, DateTime 수정

http://localhost:8080/blog/index.html

***

[추가] PostgreSql 추가 연동

-> 기존의 H2로는 서비스에 적절하지 못함
-> 무료, 가성비 좋음

***

[추가] Facebook login 완성

-> 관리자 권한 부여 필요 (관리자 페이지)
-> 카카오, 구글, 네이버 개발자 인증만 받으면 구현 가능 (추가 계획)
-> 토큰 데이터베이스 저장

*** 
junit Test

Service 객체의 반복되는 오류를 해결하기 위해서 테스트 작성

/test/*.*


*** 
[추가] 글 쓰기, 글 보기

http://localhost:8080/post/list

http://localhost:8080/post/write?subject=테스트제목&content=테스트내용


***
[추가] 블로그 글쓰기 에디터 추가, 마크다운 형식 지원 추가

http://localhost:8080/post/list

http://localhost:8080/post/write

*** 

___[블로그 관리자 개발]___

*** 

계획 중
