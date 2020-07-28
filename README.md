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

![스크린샷 2020-06-30 오전 1 04 54](https://user-images.githubusercontent.com/41769568/86032872-fc4a7e80-ba72-11ea-8e42-3c163583b8f7.png)


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

[추가] 회원정보, 카카오 사용자 정보


![스크린샷 2020-06-30 오전 1 08 50](https://user-images.githubusercontent.com/41769568/86032880-02d8f600-ba73-11ea-9f22-cde63d12d167.png)



[추가] 글과 사용자간의 종속 관계 구현
- 나의 글 수정 삭제 가능
- 재 로그인시에도 기능
- 다른 계정으로 접근 시, 오류 창 출력




___[블로그 관리자 개발]___

*** 

계획 중
