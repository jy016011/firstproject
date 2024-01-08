# 📖 스프링부트3 자바 백엔드 개발 입문

## 💡 1일차: 스프링부트 시작하기

### 1. 스프링부트 설치
- 교재에서는 스프링부터 3.1.0 버전, JDK 17버전 기준으로 함.
- 따라서 설치되어있던 JDK 20을 JDK 17버전으로 변경 후 설치

### 2. 프로젝트 실행
- `firstproject/src/main/java/com/example/firstproject` 내부의 `FirstprojectApplication`파일을 실행하여 실행
- 웹서버는 Tomcat을 이용하여 실행되며, 기본 8080포트에서 실행됨.

### 3. 브라우저를 통해 접속해보기
- `localhost:8080`을 입력하여 접속, 해당 주소에 어떤 파일도 없기 때문에 오류 발생
- `src/main/resources/static` 내부에 `hello.html` 파일 생성 후 `localhost:8080/hello.html`로 접속
- 스프링부트는 기본적으로 `src/main/resources/static` 을 기준으로 파일을 찾음


