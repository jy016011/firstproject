# 📖 스프링부트3 자바 백엔드 개발 입문 3주차

---

## 💡 11일차: HTTP와 REST 컨트롤러

### 1. REST API의 동작 이해하기

- JSON의 값으로 내부에 또 다른 JSON을 넣거나, 배열을 넣을 수 있음.
- REST: HTTP URL로 서버의 자원을 명시하고, HTTP 메서드로 해당 자원에 대해 CRUD하는 것
- API: 클라이언트가 서버의 자원을 요청할 수 있도록 서버에서 제공하는 인터페이스
- REST API는 즉, REST를 기반으로 API를 구현한 것임.
    - 클라이언트가 기기에 구애받지 않고 서버의 자원 이용 가능
    - 서버 프로그램의 재사용성과 확장성이 좋아짐

### 2. REST API 구현하기

- REST API를 구현하는 컨트롤러에는 `@RestController` 어노테이션을 붙임
- REST 컨트롤러는 JSON이나 텍스트 같은 데이터를 반환(일반 컨트롤러는 뷰 페이지를 반환)
- REST API에서는 dto 매개변수 앞에 `@RequestBody`라는 어노테이션을 붙여야 요청으로 오는 JSON 데이터를 받을 수 있음.
- `ResponseEntity`클래스는 REST API의 응답을 위해 사용하는 클래스
    - REST API 요청을 받아 응답할 때 이 클래스에 HTTP 상태코드, 헤더, 본문을 실어 보낼 수 있음.
- `HttpStatus`클래스는 HTTP 상태코드를 관리하는 클래스.
    - 다양한 Enum타입과 관련 메서드를 가짐
        - `HttpStatus.OK`: HTTP 200
        - `HttpStatus.CREATED`: HTTP 201
        - `HttpStatus.BAD_REQUEST`: HTTP 400
- `PATCH`메서드의 경우 일부 데이터만 수정할 수도 있으므로 null 데이터가 덮어 쓰여지지 않도록 값이 있는 데이터만 수정되도록 주의하여 코딩할 것
    - 요청으로 들어온 수정 데이터 중 null 값이 아닌 데이터만 새로 수정한다는 지 등

---
