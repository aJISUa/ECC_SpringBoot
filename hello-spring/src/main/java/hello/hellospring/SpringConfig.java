package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 16강 자바 코드로 직접 스프링 빈 등록하기
// 스프링이 뜰 때 @Configuration 을 읽고 @Bean 메서드가 반환한 객체를 스프링 빈으로 등록함
// 컨트롤러는 여기서 등록하지 않고 @Controller 로 컴포넌트 스캔에 맡김
// 나중에 리포지토리 구현체를 바꿀 때 이 파일만 고치면 됨
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
