package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 22강 회원 서비스 스프링 통합 테스트
// MemberServiceTest 는 스프링 없이 자바 코드만으로 도는 단위 테스트
// 이 테스트는 스프링 컨테이너를 띄우고 DB 까지 연결해서 돌림. H2 가 켜져 있어야 함
//
// @SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행함
// @Transactional : 테스트 시작 전에 트랜잭션을 시작하고 테스트가 끝나면 항상 롤백함
//                  DB 에 데이터가 남지 않아서 @AfterEach 로 지울 필요가 없음
@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    // 테스트는 다른 데서 가져다 쓸 게 아니라서 편하게 필드 주입을 씀
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //Given
        Member member = new Member();
        member.setName("hello");

        //When
        Long saveId = memberService.join(member);

        //Then
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //When
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
