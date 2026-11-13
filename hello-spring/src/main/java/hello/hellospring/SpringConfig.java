package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 16강 자바 코드로 직접 스프링 빈 등록하기
// 스프링이 뜰 때 @Configuration 을 읽고 @Bean 메서드가 반환한 객체를 스프링 빈으로 등록함
// 컨트롤러는 여기서 등록하지 않고 @Controller 로 컴포넌트 스캔에 맡김
//
// 21~25강: 리포지토리 구현체를 바꿀 때 이 파일만 고침. MemberService 나 컨트롤러는 손대지 않음
// 개방-폐쇄 원칙(OCP). 확장에는 열려있고 수정, 변경에는 닫혀있음
@Configuration
public class SpringConfig {

    // 25강: 스프링 데이터 JPA 가 SpringDataJpaMemberRepository 구현체를 만들어서 빈으로 등록해 둠
    // MemberRepository 타입의 빈이 그것 하나라서 그대로 주입받으면 됨
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // 16~24강에서는 리포지토리도 여기서 직접 등록했음
    // 21, 23강은 DataSource 를, 24강은 EntityManager 를 생성자로 주입받아서 넘김
    // 아래 주석을 풀려면 위의 memberRepository 필드와 생성자를 dataSource, em 을 받는 것으로 바꿔야 함
    //
    // private final DataSource dataSource;   // javax.sql.DataSource. 스프링 부트 3.x 에서도 javax 그대로
    // private final EntityManager em;        // jakarta.persistence.EntityManager
    //
    // public SpringConfig(DataSource dataSource, EntityManager em) {
    //     this.dataSource = dataSource;
    //     this.em = em;
    // }
    //
    // @Bean
    // public MemberService memberService() {
    //     return new MemberService(memberRepository());
    // }
    //
    // @Bean
    // public MemberRepository memberRepository() {
    //     return new MemoryMemberRepository();                    // 16강 메모리
    //     return new JdbcMemberRepository(dataSource);            // 21강 순수 JDBC
    //     return new JdbcTemplateMemberRepository(dataSource);    // 23강 JdbcTemplate
    //     return new JpaMemberRepository(em);                     // 24강 JPA
    // }

    // 27강: AOP 를 @Component 대신 여기서 @Bean 으로 등록할 수도 있음
    // 지금은 TimeTraceAop 에 @Component 를 붙여서 컴포넌트 스캔으로 등록함
}
