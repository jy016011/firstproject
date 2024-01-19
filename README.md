# 📖 스프링부트3 자바 백엔드 개발 입문

참고 교재: [코딩 자율학습 스프링 부트 3 자바 백엔드 개발 입문](https://github.com/gilbutITbook/080354)

---

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

---

## 🎯 2일차: MVC 패턴 이해와 실습

### 1. 뷰 템플릿 생성

- 뷰 템플릿: 변수를 삽입하여 보여주고자 하는 웹페이지를 하나의 틀로 만들고, 각기 다른 변수 값으로 서로 다른 페이지를 보여주는 동적인 기술(어제 실습한 html은 정적이다.)
- 뷰 템플릿은 `src/main/resources/templates`에서 만들며, 여기서는 `mustache`파일을 이용하여 템플릿을 생성한다.
- `mustache`는 두겹의 중괄호(`{{변수 이름}}`) 으로 변수를 표현한다.

### 2. MVC 패턴의 이해

- Model(모델): 뷰 템플릿에 사용되는 데이터 관리
- View(뷰, 뷰 템플릿): 사용자가 보는 화면을 담당
- Controller(컨트롤러): 클라이언트의 요청을 받아 처리
- 따라서 사용자가 웹 브라우저를 통해 웹서버에 요청을 보내면, 웹서버의 `컨트롤러`가 이를 받아들여 `모델`의 데이터를 가져와 `뷰`에 적용하여 클라이언트에게 반환한다.

### 3. Spring Boot 에서의 MVC 패턴 실습

- **1.** 에서 생성한`src/main/resources/templates` 내부의 템플릿(mustache파일)이 뷰의 역할을 수행
- `src/main/java/com/example/firstproject/controller`내부에 컨트롤러 파일을 생성
    - `@Controller` 어노테이션을 통해 컨트롤러임을 명시(이러면 스프링 부트에서
      알아서 컨트롤러로 씀)
- 생성한 컨트롤러 클래스 내부에 클라이언트의 요청에 응답할 함수를 작성
    - `@GetMapping` 어노테이션을 통해 응답할 URL을 지정(이러면 지정한 URL에 응답한다.)
- 컨트롤러 함수는 모델을 인자로 가지며, 모델 객체는 `addAttribute()`를 통해 템플릿에 사용할 변수 값을 넣어준다.
- 컨트롤러 함수는 반환 값에 템플릿 이름만 기입하고, 이는 클라이언트에게 동일한 이름의 템플릿을 반환하는 일을 수행하게 된다.

### 4. 뷰 템플릿에 레이아웃 적용하기

- 레이아웃: 화면에 요소를 배치하는 일
- 오늘 실습 에서는 헤더-푸터 레이아웃 이용
- 부트스트랩(BootStrap): 웹 페이지를 쉽게 만들 수 있도록 웹 페이지의 요소들을 미리 작성해 놓은 코드 모음.
    - 실습 간에는 `5.0.2` 버전 이용
- `src/main/resources/templates` 내부에 `layouts` 디렉터리를 생성하여 이 내부에 헤더, 푸터와 같은 html 코드들을 템플릿 화 하여 관리
    - 각기 다른 뷰 템플릿에서 이들을 호출하여 코드의 중복을 방지하고, 코드를 간결하게 함.

---

## 📝 3일차: 게시판 만들고 새 글 작성하기(Create)

### 1. 폼데이터와 DTO

- 폼데이터: HTML요소인 `<form>`태그에 실려 전송되는 데이터
    - action 속성을 통해 서버의 어디로 데이터를 보낼지 지정
    - method 속성을 통해 `get`, `post` 중에서 방식을 지정할 수 있음.
- DTO: 폼데이터를 받은 컨트롤러는 이를 객체에 저장하는데 이를 DTO라고 함.
    - DTO의 데이터는 최종적으로 데이터베이스에 저장

### 2. 폼데이터 처리

- 컨트롤러를 통해 폼데이터를 처리
    - 폼데이터의 `method` 값에 따라 `GetMapping`혹은 `PostMapping` 하여 action의 경로 값과 동일하게 맵핑해야함
    - 폼데이터를 저장할 DTO는 필드로 폼데이터 내부의 저장할 데이터 이름과 같아야함.
        - 폼데이터에서 저장될 데이터 이름은 `name="변수명"`으로 기술

### 3. Entity와 Repository

- JPA: 자바 언어로 DB에 명령을 내리는 도구
- Entity: JPA의 요소로 자바 객체를 DB가 이해할 수 있도록 만든 클래스로 이를 기반으로 DB내부에 테이블이 만들어짐
    - `@Entity` 어노테이션으료 표시
    - `@Column` 표시한 필드 값이 테이블의 속성(열)이 됨.
    - `@Id` 표시한 필드가 행을 구분할 대표값(식별자)이 됨.
- Repository: 엔터티가 DB속 테이블에 저장 및 관리될 수 있게하는 인터페이스
    - 금일 실습에서는 `CrudRepository<T, ID>` 인터페이스를 상속받아 데이터를 저장
        - 여기서 `T`는 저장할 엔티터의 클래스 타입, `ÌD`는 엔터티의 대표값의 자료형
    - 여기서 인터페이스인 Repository의 구현체를 따로 구현할 필요 없이 `@Autowired`를 통해 스프링에서 스스로 생성한 객체와 연결해줌. 이를 의존성주입(DI)라고 함
        - 의존성 주입: 외부에서 만들어진 객체를 필요한 곳에 가져다 주는 것
        - CrudRepository 인터페이스는 이미 Spring Data JPA 패키지 안에 `SimpleJpaReposiroty.java`로 구현되어있음.
            - 이를 `@Autowired`로 의존성 주입해주는 것임.

### 4. 데이터 조회하기

- `src/main/resources/apllication.properties` 파일에 내용을 추가하여 스프링의 DB에 웹브라우저를 통해 접근가능
    - 실습간에는 `H2`데이터베이스(메모리에 저장)이므로 접속시 DB의 메모리 주소 필요
        - 서버 시작시마다 완전히 초기화되어(값도 날아감) 주소가 매번 바뀜
- `SELECT 속성명1, 속성명2... FROM 테이블이름` 구문을 통해 테이블 내부의 원하는 속성 값들을 불러올 수 있다.
- `INSERT INTO 테이블명(속성명1, 속성명2...) VALUES(값1, 값2..)`를 통해 테이블에 행을 추가할 수 있다.

---

## 🛠️ 4일차: 롬복과 리팩터링

### 1. 롬복이란?

- 롬복: 코드를 간소화 해주는 라이브러리
    - 롬복이 코드를 간소화하는 순서

            1. java 컴파일러가 소스파일을 파싱하여 AST트리를 만듬
            2. 롬복은 AnnotationProcessor에 따라 AST트리를 동적으로 수정하고 새 노드를 추가하고 바이트 코드를 분석 및 생성
            3. java 컴파일러는 롬복에 의해 수정된 AST를 기반으로 바이트 코드를 생성

    - 로깅도 가능하다.
        - 로깅: 프로그램의 수행 과정을 기록으로 남기는 것
        - `Sytem.out.println()`(줄여서 sout)으로 수정과정을 보는 것은 서버의 성능을 저하시키고 기록도 안되므로 로깅을 하자.

### 2. 롬복 설치

- `build.gradle` 파일에 `dependencies{}` 부분을 수정한 후 `Load Gradle Changes`를 통해 설치
    - `@Slf4j`의 로깅 이용간 로깅 함수를 찾을 수 없다는 에외 발생하였음.
        - IDE의 Settings -> Plugins -> Marketplace 에서 롬복을 설치하여 해결

### 3. 리팩터링

- 기존 코드에서의 생성자는 `@AllArgsConstructor` 1줄로 대체
- 기존 코드에서의 `toString()`은 `@ToString` 1줄로 대체
- 기존 코드에서의 수행과정을 임시로 보기위해 쓴 sout은 `@Slf4j`의 `log.info()`로 대체

---

## 🔍 5일차: 게시글 읽기(Read)

### 1. 데이터 조회 과정

    1. 사용자가 웹 페이지를 통해 URL 요청을 보낸다.
    2. 서버의 컨트롤러가 요청을 받아 URL에서 찾으려는 정보를 Repository에 전달한다.
    3. Repository는 해당되는 정보를 DB에 조회 요청한다.
    4. DB는 해당 데이터를 찾아 Entity로 반환한다.
    5. 반환된 엔터티는 모델을 통해 뷰 템플릿으로 전달한다.
    6. 뷰 페이지가 완성되어 사용자에게 전달되어 화면에 출력한다.

### 2. 데이터 조회하기

- 원하는 정보를 얻어야하므로 URL에 정보를 식별할 값을 넣어줌
    - 실습에서는 `Id`로 식별
- 컨트롤러에서는 `@xxMapping`의 URL 경로 값에 중괄호(`{변수명}`)로 감싸 식별자를 표시
- 또한, 컨트롤러 함수 인자에 `@PathVariable`을 추가하여 중괄호 안에 들어간 식별자를 함수 인자로 받도록 한다.
- Repository를 통해 `findById()`로 원하는 정보를 가진 엔터티를 받는다.
    - `findById()`는 `Optional<T>` 형태로 반환하므로 형변환을 하거나, `orElse()`를 붙인다.
- Entity를 Model의 `addAttribute()`를 통해 모델에 적용한다.
- 모델에 적용된 Entity의 정보는 뷰 템플릿에서 이중 중괄호(`{{entity명}}`)로 명시된다.
    - `{{#entity}}` ~ `{{/entity}}`로 Entity 정보를 이용할 영역을 명시한다.
    - 해당 영역 내에서 Entity의 `@Column`인 필드를 이중 중괄호(`{{필드명}}`)로 사용할 수 있다.
    - 모델에 담긴 Entity 정보가 `List<T>`와 같은 데이터 묶음일 경우 `{{#entity}}` ~ `{{/entity}}` 영역 내의 코드가 데이터 묶음만큼 반복된다.

---

## 🚪 6일차: 게시판 내 페이지 이동하기

### 1. 링크와 다이렉트

- 링크: 미리 정해 놓은 요청을 간편히 전송하는 기능
    - 페이지 이동을 위해 사용
    - HTML의 `<a>` 혹은 `<form>` 태그로 작성
- 리다이렉트: 클라이언트가 보낸 요청을 마친 후 계속해서 처리할 다음 요청 주소를 재지시하는 것
    - 리다이렉트를 받은 클라이언트는 해당 주소로 다시 요청을 보내고 서버는 이에 대한 결과를 응답
    - 페이지 리다이렉트에 관련된 HTTP 상태 코드는 주로 300번대에 존재
        - 그 중 302번 상태 코드가 제일 많이 사용되며, 이는 클라이언트가 요청한 리소스에 대한 답으로 서버가 설정한 URL로 리다이렉트 됐음을 나타냄

### 2. 링크를 통해 페이지 연결하기

- 이동이 필요한 페이지의 뷰 템플릿에 `<a>` 혹은 `<form>`태그에 이동하고자 하는 페이지 URL을 집어넣음으로써 다른 페이지로의 연결을 형성

### 3. 리다이렉트를 통해 페이지 연결

- 지금까지는 글 작성 후 POST 요청을 통해 DB에 글을 저장하지만, 잘 저장되었다고 알려주는 웹 페이지가 없었음
    - 이를 위해 POST 요청을 담당하는 컨트롤러 함수의 반환 값에 `return "redirect:URL주소"`를 넣음으로써 리다이렉트함.
    - 실습 간에는 글의 상세 페이지를 리턴하기위해 `/articles/show/{id}`로 리다이렉트함.
    - 클라이언트는 `/articles/show/{id}`로 다시 요청하라는 응답을 받아 `/articles/show/{id}`로 GET 요청을 보냄
    - 서버는 GET요청을 받아 해당하는 뷰를 반환

---

## 🖍️ 7일차: 게시글 수정하기(Update)

### 1. 데이터 수정 과정

- 기존 데이터를 불러와 수정 페이지 반환하기
    1. `show` 페이지에서 `Èdit` 버튼 클릭
    2. 요청을 받은 컨트롤러는 해당 글의 Id로 데이터를 찾아 불러옴
    3. 불러온 데이터를 컨트롤러가 모델에 등록
    4. 모델에 등록된 데이터를 수정 페이지에 적용하여 보여줌
- 수정한 데이터를 DB에 반영 후 반영 결과를 `show` 페이지로 리다이렉트하여 보여주기
    1. 폼 데이터(수정 요청 데이터)를 DTO에 담아 컨트롤러에서 받음
    2. DTO를 엔터티로 변환
    3. DB에서 기존 데이터를 수정해서 갱신
    4. `show` 페이지로 리다이렉트

### 2. 수정 페이지 반환하기

- 기존의 `show` 템플릿에 `articles/{{id}}/edit` 경로로 `Edit`버튼 추가
- 컨트롤러는 `articles/{{id}}/edit` 경로애 대응되는 `edit()` 함수를 통해 응답
    - 경로를 통해 받은 Id값을 이용하여 DB로부터 해당하는 Article 데이터를 불러옴
    - 불러온 데이터를 모델에 적용
    - 모델의 데이터는 `edit` 템플릿에 반영되고, 사용자에게 반환됨

### 3. DB에 수정된 데이터 적용하기

- HTTP 메서드에는 데이터 생성(`POST`), 데이터 조회(`GET`), 데이터 수정(`PATCH`), 데이터 삭제(`DELETE`)가 있음.
- 하지만 `<form>`태그에는 `GET`과 `POST`만 있어 이번 실습간에는 `POST`메서드 이용
- 사용자는 `edit` 페이지의 `Submit`버튼을 클릭하여 `/articles/update`경로로 `POST`요청 전송
- 컨트롤러는 `@PostMapping("/articles/update")`를 통해 요청에 대응
    - DTO를 통해 데이터를 받음
    - DTO를 엔터티화함
    - 엔터티의 Id를 통해 DB로부터 값을 불러옴
    - 불러온 값이 `null`이 아니라면 `save()`를 통해 해당 Id의 값을 업데이트함
    - 반영된 정보를 보여주기 위해 `show` 페이지로 리다이렉트 응답을 보냄
- SQL 업데이트 문법: `UPDATE 테이블 명 SET 속성명 = 속성값 WHERE 조건;`

### 4. 실습간 발생한 오류 및 해결

- 편의를 위해 `data.sql`을 추가하여 `INSERT`구문으로 데이터를 직접추가하였었음. 그리고 웹 브라우저를 통해 새 게시글을 작성하는 `Create`하였으나, 오류 발생
    - 앞에 데이터를 직접 넣는 `INSERT`에서 식별자인 `id`값을 임의로 넣어주어 DB에서 이후에 생성되는 데이터의 `id`를 자동증가로 넣지 않아 원래 있던 값의 `id`와 충돌
    - `INSERT`구문에서 id값 구문들(속성명, 값)을 전부 삭제하고, 엔터티의 식별자위 어노테이션 `@GeneratedValue`에 `(strategy = GenrationType.IDENTITY)`를
      추가하여 해결
    - 여기서 `GenrationType.IDENTITY`는 자동생성 전략(strategy)을 DB에게 알아서 하도록 위임한다는 말임. 그럼 DB는 자동 증가로 식별자를 생성

---

## ❌ 8일차: 게시글 삭제하기(Delete)

### 1. 데이터 삭제 과정

1. 클라이언트가 HTTP 메소드로 특정 데이터 삭제 요청을 보냄
2. 요청을 받은 컨트롤러는 해당하는 데이터가 DB에 있는지 확인하고, 있으면 DB에서 데이터를 삭제함.
3. 삭제가 완료되었다면, 결과 화면을 보여주기 위해 `index`페이지로 반환하고, 삭제가 완료되었다는 알림창도 같이 띄워줌

### 2. 데이터 삭제하기

- `show`템플릿에 `/articles/{{articles.id}}/delete`경로로 GET 요청을 보내는 삭제 버튼을 추가함.
    - HTML은 GET과 POST 요청만 되어 실습 간에는 DELETE 요청은 수행하지 않고 GET을 이용
- `/articles/{id}/delete`경로로 `GetMapping`하는 `delete()`함수를 추가하여 해당 요청을 수행
    - 경로로 들어온 `ìd`에 해당하는 데이터가 DB에 있는지 확인
    - 있는 데이터라면 DB에서 삭제하고, `RedirectAttibutes`객체의 `addFlashAttribute()`를 통해 삭제가 완료됐다는 내용의 휘발성 데이터를 담음
    - 담겨진 휘발성 데이터는 리다이렉트될 `index`페이지에 적용되어 사용자는 삭제 후 삭제가 되었다는 알림창과 함께 글목록 페이지(`index`페이지)를 보게됨.

### 3. SQL문으로 직접 데이터 삭제

- `DELETE (FROM) 테이블 명 WHERE 조건;` ()는 생략가능 문구

---

## 💾 9일차: CRUD와 SQL쿼리 종합

### 1. JPA 로깅 설정하기

- 로깅 레벨 7단계: 설정한 출력 레벨 이상의 모든 로그가 출력된다. 실습에서는 `DEBUG`단계로 설정.
    - `TRACE`(레벨 1): `DEBUG`보다 상세한 정보
    - `DEBUG`(레벨 2): 응용 프로그램을 디버깅하는데 필요한 세부 정보
    - `INFO`(레벨 3): 응용 프로그램의 순조로운 진행정보(`spring.jpa.show-sql=true`로 설정시 기본값이 됨)
    - `WARN`(레벨 4): 잠재적으로 유해한 상황정보
    - `ERROR`(레벨 5): 응용 프로그램이 수행할 수 있는 정도의 오류 정보
    - `FATAL`(레벨 6): 응옹 프로그램이 중단될 만한 심각한 오류 정보
    - `OFF`(레벨 7): 로깅 해제
- `format_sql=true`: SQL 쿼리에 줄바꿈 적용
- `sql.BasicBinder=TRACE`: JPA 쿼리에서 DB로 넘어가는 매개변수 값 확인

### 2. 쿼리로그 확인하기

- id 자동 생성 전략 추가: 엔터티의 Primary key에 `@GeneratedValue(strategy = GenerationType=IDENTITY)`를 추가
    - GenerationType 5가지
        - `TABLE`: DB에 키 생성 전용 테이블을 만들고 이를 이용하여 키 생성
        - `SEQUENCE`: DB의 특별한 시퀀스 오브젝트를 이용(지원안하는 DB도 있음)
        - `IDENTITY`: DB에 기본 키 생성을 위임함(MySQL은 AUTO_INCREMENT 사용)
        - `AUTO`: `@GeneratedValue`에 옵션 추가 안할 시 기본 값
        - `UUID`: RFC 4122 UUID를 생성
- 서버 시작시 `@Entity`에 해당하는 클래스를 기반으로 테이블들을 생성
    - 로그상의 `CREATE`문으로 생성하는 것 확인 가능
- 데이터 추가시에는 `INSERT`문
- 데이터 조회시에는 `SELECTE`문
- 데이터 수정시에는 `UPDATE`문
- 데이터 삭제시에는 `DELETE`문

### 3. 기본 SQL 쿼리 작성하기

- 테이블 생성:

```
CREATE 테이블명(
  속성명1 자료형,
  속셩명2 자료형,
  ...
  PRIMARY KEY(기본키)
);
```

- 데이터 추가:

```
INSERT 
INTO 
    테이블명
    (속성명1, 속성명2, ...)  
VALUES
    (속성값1, 속성값2,...);
```

- 데이터 조회

```
SELECT
    속성명1, 속성명2, ...
FROM
    테이블명
WHERE
    조건;
```

- 데이터 수정

```
UPDATE 
    테이블명
SET
    (속성명1=속성값1, 속성명2=속성값2 ...)
WHERE
    조건;
```

- 데이터 삭제

```
DELETE
(FROM) - 생략 가능
    테이블명
WHERE
    조건;
```

---

## ✉️ 10일차: REST API와 JSON

### 1. REST API와 JSON

- REST API: 서버의 자원을 클라이언트에 구애받지 않고 사용할 수 있게하는 설계 방식
    - HTTP 요청에 대한 응답으로 모든 기기에 통용되는 데이터인 JSON을 전송
    - JSON: 키와 값으로 구성된 정렬되지 않은 속성의 집합
        - 예시
      ```
      {
        "name": "망고",
        "breeds": "골든 리트리버",
        "age": 2
      }
      ```

### 2. REST API 동작 살펴보기

- REST API는 모든 HTTP 메서드 지원
- GET은 HTTP 메소드의 기본 속성으로 생략 가능
- PUT과 PATCH의 차이점
    - PUT: 기존 데이터를 전부 새 데이터로 변경, 기존 데이터 없을 시 새로 생성
    - PATCH: 기존 데이터의 일부 내용을 변경
- HTTP 상태코드
    - `1XX`(정보): 요청이 수신되어 처리 중
    - `2XX`(성공): 요청이 정상적으로 처리됨
    - `3XX`(리다이렉션 메시지): 요청을 완료하려면 추가 행동이 필요
    - `4XX`(클라이언트 요청 오류): 클라이언트의 요청이 잘못되었습니다.
    - `5XX`(서버 응답 오류): 서버 내부 오류 발생
- HTTP 메시지: HTTP 요청 및 응답에 쓰이는 텍스트 형식의 메시지, 다음 내용으로 구성
    - 시작라인: HTTP 요청 또는 응답 내용, 항상 한줄로 끝남
    - 헤더: HTTP 전송에 필요한 부가 정보(어디로 보낼지 등)
    - 빈라인: 헤더의 끝을 알리는 줄, 다음줄부터 본분
    - 본문: 실제 전송 데이터

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

## ⚙️ 12일차: 서비스 계층과 트랜잭션

### 1. 서비스와 트랜잭션의 개념

- 서비스: 컨트롤러와 리퍼지터리 사이에 위치하는 계층으로 서버의 비즈니스 로직(핵심 기능)을 처리하는 순서를 총괄함
    - 일반적으로 서비스는 `트랜잭션(transaction)`단위로 진행됨
- 트랜잭션: 모두 성공해야하는 쪼갤 수 없는 업무의 최소 단위
    - 트랜잭션 중간에 실패하면 이전의 트랜잭션 과정들은 모두 롤백됨

### 2. 서비스 계층 만들기

- `@Service` 어노테이션은 해당 클래스를 서비스로 인식해 스프링 부트가 서비스 객체를 생성
    - 컨트롤러는 `@Autowired`를 통해 서비스 객체를 이용할 수 있음.
- 컨트롤러는 클라이언트의 요청을 접수하고 응답하는 역할만 수행
- 서비스는 비즈니스 로직을 수행하고, 그 과정에서 필요한 데이터를 리파지터리를 통해 DB로부터 불러옴.
- 서비스는 로직을 수행한 후 엔터티 객체(혹은 `null`)을 반환
- 컨트롤러는 서비스의 반환 값에 따라 HTTP 상태 코드를 담은 응답(`ResponseEntity`)을 반환

### 3. 트랜잭션 맛보기

- 보통의 트랜잭션은 서비스에서 관리
- 서비스의 메서드에 `@Transactional`을 붙이면 해당 메서드는 하나의 트랜잭션으로 취급됨.
    - 클래스에 선언시 클래스의 모든 메서드에 각각의 트랜잭션이 부여됨.
- 중간에 작업이 실패하면 작업 시작 전 상태로 `롤백`

---