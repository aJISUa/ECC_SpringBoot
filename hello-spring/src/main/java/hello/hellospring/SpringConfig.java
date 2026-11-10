package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 16강 자바 코드로 직접 스프링 빈 등록하기
// 스프링이 뜰 때 @Configuration 을 읽고 @Bean 메서드가 반환한 객체를 스프링 빈으로 등록함
// 컨트롤러는 여기서 등록하지 않고 @Controller 로 컴포넌트 스캔에 맡김
//
// 21강: 리포지토리 구현체를 바꿀 때 이 파일만 고침. MemberService 나 컨트롤러는 손대지 않음
// 개방-폐쇄 원칙(OCP). 확장에는 열려있고 수정, 변경에는 닫혀있음
@Configuration
public class SpringConfig {

    // 스프링 부트가 application.properties 의 접속 정보로 DataSource 를 만들어서 빈으로 등록해 둠
    // @Configuration 클래스도 스프링 빈이라 생성자로 주입받을 수 있음
    // javax.sql.DataSource. 스프링 부트 3.x 에서도 javax 그대로
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();                // 16강 메모리
        return new JdbcMemberRepository(dataSource);          // 21강 순수 JDBC
    }
}
