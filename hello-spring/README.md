# hello-spring

ECC 스프링 스터디 실습 코드 정리

- 강의: 스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술 (김영한, 인프런)
- 환경: Java 21, Spring Boot 3.5.16, Gradle 8.14 (Groovy), Thymeleaf, H2 2.3.232, JPA (Hibernate)

## 진행

- [x] 섹션 1 강의 소개 (1~2강)
- [x] 섹션 2 프로젝트 환경설정 (3~6강)
- [x] 섹션 3 스프링 웹 개발 기초 (7~9강)
- [x] 섹션 4 회원 관리 예제 - 백엔드 개발 (10~14강)
- [x] 섹션 5 스프링 빈과 의존관계 (15~16강)
- [x] 섹션 6 회원 관리 예제 - 웹 MVC 개발 (17~19강)
- [x] 섹션 7 스프링 DB 접근 기술 (20~25강)
- [ ] 섹션 8 AOP (26~27강)
- [ ] 섹션 9 다음으로 (28강)

## 폴더

- `src/main/java/hello/hellospring` : 자바 코드. `HelloSpringApplication` 이 시작점
- `src/main/java/hello/hellospring/controller` : 컨트롤러, 폼 객체 (`MemberForm`)
- `src/main/java/hello/hellospring/domain` : 회원 도메인
- `src/main/java/hello/hellospring/repository` : 회원 리포지토리 인터페이스와 구현체 5개 (메모리, 순수 JDBC, JdbcTemplate, JPA, 스프링 데이터 JPA)
- `src/main/java/hello/hellospring/service` : 회원 서비스
- `src/main/java/hello/hellospring/SpringConfig.java` : 스프링 빈 직접 등록
- `src/main/resources/static` : 정적 컨텐츠. 파일 그대로 내려감
- `src/main/resources/templates` : thymeleaf 템플릿. 컨트롤러가 반환한 이름으로 찾음
- `src/test` : 테스트 코드. main 과 같은 패키지 구조로 만듦
- `src/main/resources/application.properties` : DB 연결 설정, JPA 설정
- `sql/ddl.sql` : member 테이블 만드는 SQL
- `build.gradle` : 스프링 부트 버전, 라이브러리 설정

1~2강, 4강, 10강은 개념 위주라 실습 파일이 없음.

## DB 준비

20강부터는 H2 데이터베이스가 켜져 있어야 실행도 되고 테스트도 됨.

1. https://www.h2database.com/html/download-archive.html 에서 H2 를 받음. 스프링 부트 3.x 는 2.1.214 이상이어야 하고, 이 프로젝트의 드라이버가 2.3.232 라서 같은 버전으로 맞추는 게 편함
2. `h2/bin` 에서 윈도우는 `h2.bat`, 맥은 `chmod 755 h2.sh` 한 다음 `./h2.sh` 실행
3. 콘솔 주소가 IP 로 열려서 안 되면 앞부분만 `localhost` 로 바꿈. 뒤의 세션 부분은 건드리면 안 됨
4. 최초 한 번만 JDBC URL 을 `jdbc:h2:~/test` 로 연결해서 파일을 만듦. 홈 폴더에 `test.mv.db` 가 생겼는지 확인
5. 그 뒤로는 `jdbc:h2:tcp://localhost/~/test` 로 접속
6. `sql/ddl.sql` 내용을 콘솔에서 실행해서 member 테이블을 만듦

H2 실행 창을 끄면 DB 가 죽어서 연결이 안 됨. 계속 켜둬야 함.
꼬였으면 H2 를 끄고 `test.mv.db` 를 지운 다음 처음부터 다시 하면 됨.

## 실행

`HelloSpringApplication` 의 main 메서드를 실행하거나, 터미널에서 빌드해서 실행함.

```bash
./gradlew build
cd build/libs
java -jar hello-spring-0.0.1-SNAPSHOT.jar
```

윈도우 cmd 에서는 `gradlew build`, 폴더 목록은 `dir`.
잘 안 되면 `./gradlew clean build` 로 build 폴더를 지우고 다시 빌드하면 됨.
IDE 에서 서버를 켜둔 채로 jar 를 실행하면 8080 포트가 겹쳐서 에러 남. 둘 중 하나만 켜야 함.

| 강 | 주소 | 파일 | 결과 |
| --- | --- | --- | --- |
| 5강 | `localhost:8080/index.html` | `static/index.html` | Welcome Page. 17강부터 `localhost:8080` 은 홈 화면이 나와서 Welcome Page 는 `/index.html` 로 봐야 함 |
| 5강 | `localhost:8080/hello` | `HelloController.hello`, `templates/hello.html` | 안녕하세요. hello!! |
| 7강 | `localhost:8080/hello-static.html` | `static/hello-static.html` | 정적 컨텐츠 입니다. |
| 8강 | `localhost:8080/hello-mvc?name=spring` | `HelloController.helloMvc`, `templates/hello-template.html` | hello spring |
| 9강 | `localhost:8080/hello-string?name=spring` | `HelloController.helloString` | hello spring (HTML 태그 없음) |
| 9강 | `localhost:8080/hello-api?name=spring` | `HelloController.helloApi` | `{"name":"spring"}` |
| 17강 | `localhost:8080` | `HomeController.home`, `templates/home.html` | 회원 가입, 회원 목록 링크 |
| 18강 | `localhost:8080/members/new` | `MemberController.createForm`, `create`, `templates/members/createMemberForm.html` | 이름 입력하고 등록하면 홈으로 돌아옴 |
| 19강 | `localhost:8080/members` | `MemberController.list`, `templates/members/memberList.html` | 등록한 회원 목록 |

회원 데이터는 19강까지는 메모리에 저장해서 서버를 껐다 켜면 다 사라졌음.
21강부터는 DB 에 저장해서 서버를 다시 켜도 남아 있음.
화면을 열 때마다 콘솔에 Hibernate SQL 이 나옴.
같은 이름으로 두 번 가입하면 `IllegalStateException` 이 나면서 500 에러 화면이 나옴.

## 테스트

```bash
./gradlew test
```

| 강 | 파일 | 내용 |
| --- | --- | --- |
| 12강 | `repository/MemoryMemberRepositoryTest` | save, findByName, findAll |
| 14강 | `service/MemberServiceTest` | 회원가입, 중복_회원_예외 |
| 22강 | `service/MemberServiceIntegrationTest` | 스프링과 DB 까지 연결한 통합 테스트. H2 가 켜져 있어야 함 |

12강, 14강 테스트는 스프링 없이 도는 단위 테스트라 금방 끝남.
22강 테스트는 `@Transactional` 이라 끝나면 롤백돼서 DB 에 데이터가 안 남음. 그래도 id 번호는 계속 올라감.
`./gradlew build` 도 테스트를 같이 돌려서 H2 가 꺼져 있으면 실패함.

IntelliJ 에서는 클래스나 패키지 단위로 한꺼번에 돌릴 수 있음.

## 정리

- 1강: 스프링은 너무 방대해서 이론(IoC, DI, AOP)부터 하면 길을 잃음. 간단한 웹 애플리케이션을 직접 만들면서 큰 그림부터 잡는 강의
- 2강: 강의 자료. 스프링 부트 3.0 이상, 자바 17 이상을 써야 함. 3.x 부터 `javax` 패키지가 `jakarta` 로 바뀜
- 3강: start.spring.io 에서 프로젝트 생성. Gradle - Groovy, Java, group `hello`, artifact `hello-spring`, Spring Web 과 Thymeleaf 선택. main 메서드만 실행하면 내장 톰캣이 8080 으로 뜸. 처음엔 Whitelabel Error Page 가 나오면 성공
- 4강: 라이브러리 두 개만 골랐는데 Gradle 이 의존관계를 따라가며 나머지를 다 받아옴. `spring-boot-starter-web` 안에 톰캣과 스프링 웹 MVC, `spring-boot-starter` 안에 스프링 부트, 스프링 코어, 로깅(logback, slf4j). 테스트는 JUnit5, mockito, assertj, spring-test
- 5강: `static/index.html` 을 두면 Welcome Page 가 됨. 컨트롤러가 문자를 반환하면 viewResolver 가 `resources:templates/` + 이름 + `.html` 을 찾아서 thymeleaf 가 처리함. `model.addAttribute` 로 넣은 값을 템플릿에서 `${data}` 로 꺼냄
- 6강: `./gradlew build` 하면 `build/libs` 에 jar 가 생김. `java -jar` 로 실행. 서버에 jar 파일 하나만 올리면 돼서 톰캣을 따로 설치할 필요 없음
- 7강: 정적 컨텐츠. 컨트롤러를 먼저 찾아보고 없으면 `resources/static` 에서 파일을 찾아 그대로 반환함
- 8강: MVC 와 템플릿 엔진. 뷰는 화면만, 컨트롤러는 로직만 담당하게 나눈 것. `@RequestParam` 으로 URL 파라미터를 받는데 `required` 기본값이 true 라서 안 넘기면 400 에러. thymeleaf 는 서버 없이 파일을 열어도 태그 안의 기본 글자(hello! empty)가 보임
- 9강: `@ResponseBody` 를 붙이면 viewResolver 대신 HttpMessageConverter 가 동작해서 반환값을 HTTP body 에 직접 넣음. 문자는 `StringHttpMessageConverter` 가 그대로, 객체는 `MappingJackson2HttpMessageConverter` 가 JSON 으로 바꿔서 내려줌
- 10강: 데이터는 회원ID, 이름. 기능은 회원 등록, 조회. 컨트롤러, 서비스, 리포지토리, 도메인으로 나누는 계층 구조. 저장소가 아직 안 정해졌다고 가정해서 리포지토리는 인터페이스로 만들고 구현체는 메모리로 시작
- 11강: `Member`, `MemberRepository`, `MemoryMemberRepository`. null 이 나올 수 있는 조회는 `Optional` 로 감싸서 반환. 실무에서는 동시성 때문에 `ConcurrentHashMap`, `AtomicLong` 을 고려해야 함
- 12강: JUnit 으로 테스트 작성. `assertThat(result).isEqualTo(member)`. 테스트 실행 순서는 보장되지 않아서 `@AfterEach` 로 매번 저장소를 비워야 함. 테스트는 서로 독립적이어야 함
- 13강: `MemberService`. 같은 이름이 있으면 `ifPresent` 에서 `IllegalStateException` 을 던짐. 서비스 메서드 이름은 `join` 처럼 비즈니스 용어로 지음
- 14강: given, when, then 으로 나눠서 작성. 예외는 `assertThrows` 로 검증. 서비스가 리포지토리를 직접 new 하지 않고 생성자로 받게 바꿈 (DI). `@BeforeEach` 에서 매번 새로 만들어 넣어줌
- 15강: `@Controller`, `@Service`, `@Repository` 는 `@Component` 를 포함해서 컴포넌트 스캔으로 스프링 빈이 됨. 생성자에 `@Autowired` 가 있으면 스프링이 컨테이너에서 찾아서 넣어줌. 스캔 범위는 메인 클래스 패키지와 그 하위. 스프링 빈은 기본이 싱글톤
- 16강: `@Configuration` 클래스의 `@Bean` 메서드로 직접 등록. 구현 클래스를 바꿔야 하면 이 방식이 좋음. 설정 파일만 고치면 됨. DI 는 필드, setter, 생성자 주입이 있는데 생성자 주입을 권장. `@Autowired` 는 스프링이 관리하는 객체에서만 동작함
- 17강: `HomeController` 가 `/` 를 받아서 `home.html` 을 보여줌. 컨트롤러가 정적 파일보다 우선순위가 높아서 `static/index.html` 은 무시됨
- 18강: GET `/members/new` 는 폼 화면, POST `/members/new` 는 등록. 폼의 `name="name"` 값이 `MemberForm` 에 담겨서 들어옴. 등록 후 `redirect:/`
- 19강: `findMembers()` 결과를 모델에 `members` 로 담고 템플릿에서 `th:each` 로 반복 출력. `${member.name}` 은 `getName()` 으로 값을 가져옴
- 20강: H2 설치. 최초 한 번은 `jdbc:h2:~/test` 로 파일을 만들고 그 뒤로는 `jdbc:h2:tcp://localhost/~/test` 로 접속. 파일로 직접 붙으면 콘솔과 애플리케이션이 동시에 못 붙어서 그럼. `generated by default as identity` 는 id 를 DB 가 알아서 채워준다는 뜻
- 21강: 순수 JDBC. `spring.datasource` 설정을 넣으면 스프링 부트가 DataSource 를 빈으로 만들어 둠. 커넥션 얻기, SQL 실행, 결과 매핑, 자원 닫기를 전부 직접 해야 함. `SpringConfig` 에서 구현체만 바꾸면 다른 코드는 안 건드려도 됨. 개방-폐쇄 원칙(OCP)
- 22강: `@SpringBootTest` 는 스프링 컨테이너를 띄워서 테스트함. 테스트에 `@Transactional` 을 붙이면 끝날 때 항상 롤백. 스프링 없이 도는 단위 테스트가 더 좋은 테스트일 확률이 높음
- 23강: JdbcTemplate. 반복 코드는 없어지지만 SQL 은 직접 써야 함. `query(SQL, RowMapper, 파라미터)`, insert 는 `SimpleJdbcInsert`
- 24강: JPA. `@Entity`, `@Id`, `@GeneratedValue(IDENTITY)` 로 매핑하면 SQL 을 JPA 가 만들어줌. `EntityManager` 의 `persist`, `find` 를 쓰고 PK 가 아닌 조회는 JPQL. 데이터 변경은 트랜잭션 안에서 해야 해서 서비스에 `@Transactional`
- 25강: 스프링 데이터 JPA. `JpaRepository<Member, Long>` 을 상속한 인터페이스만 만들면 구현체를 만들어서 빈으로 등록해줌. `findByName` 처럼 메서드 이름만으로 조회. JPA 를 먼저 공부하고 써야 함

## 강의와 다른 부분

강의 영상은 스프링 부트 2.3.1, 자바 11 기준이라 지금 버전과 다른 부분이 있음.

- 스프링 부트 2.x 는 start.spring.io 에서 더 이상 고를 수 없어서 3.5.16 으로 만듦. 자바는 17 이상이어야 함
- `build.gradle` 의 `sourceCompatibility = '11'` 대신 `java { toolchain { ... } }` 로 자바 버전을 정함
- 테스트 의존성에서 `junit-vintage-engine` 을 exclude 하던 부분이 없어짐. 지금은 JUnit5 만 들어옴
- `/hello-mvc` 를 파라미터 없이 호출하면 화면에는 400 Bad Request 만 나오고, 원인은 콘솔 로그에 나옴. 메시지도 `Required request parameter 'name' for method parameter type String is not present` 로 강의와 조금 다름
- 지금 start.spring.io 에서 artifact 를 `hello-spring` 으로 만들면 패키지가 `hello.hello_spring` 으로 잡힘. 강의와 맞추려고 `hello.hellospring` 으로 씀
- 교안의 `MemberServiceTest` 에는 `AfterEach` import 가 빠져 있어서 추가함
- 15강에서 붙였던 `@Service`, `@Repository`, 서비스 생성자의 `@Autowired` 는 16강에서 `SpringConfig` 로 바꾸면서 뺌. 코드는 16강 이후 상태고, 뺀 자리는 주석으로 남겨둠
- 교안에는 홈 화면 파일이 `hello.html` 로 적혀 있는데 컨트롤러가 `home` 을 반환해서 `home.html` 로 만듦
- 강의는 H2 1.4.200 을 쓰는데 스프링 부트 3.x 는 2.1.214 이상이어야 함
- JPA 관련 import 는 `javax.persistence` 가 아니라 `jakarta.persistence`. `javax.sql.DataSource` 는 그대로
- `SpringConfig` 는 25강 이후 상태. 16~24강 버전은 주석으로 남겨둠
