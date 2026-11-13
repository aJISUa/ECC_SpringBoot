package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 13강 회원 서비스
// 리포지토리는 save, findById 처럼 저장소에 넣고 빼는 이름을 쓰고
// 서비스는 join, findMembers 처럼 비즈니스에 가까운 이름을 씀
//
// 15강 컴포넌트 스캔 방식에서는 클래스에 @Service, 생성자에 @Autowired 를 붙였음
// 16강부터는 SpringConfig 에서 @Bean 으로 직접 등록해서 애노테이션을 뺌
//
// 24강 JPA 를 통한 모든 데이터 변경은 트랜잭션 안에서 실행해야 함
// @Transactional 이 있으면 메서드를 실행할 때 트랜잭션을 시작하고, 정상 종료되면 커밋함
// 런타임 예외가 발생하면 롤백함
@Transactional
public class MemberService {

    // 13강에서는 new MemoryMemberRepository() 로 직접 만들었음
    // 14강에서 밖에서 넣어주게 바꿈 (DI)
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 26강에서는 여기에 시간 측정 코드를 직접 넣었음
        //   long start = System.currentTimeMillis();
        //   try { ... } finally { System.out.println("join " + (finish - start) + "ms"); }
        // 핵심 로직과 섞여서 유지보수가 어렵고, 메서드마다 다 넣어야 함
        // 27강에서 TimeTraceAop 로 빼내고 여기는 원래대로 되돌림
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 같은 이름이 있는 중복 회원은 안 됨
    // findByName 이 Optional 이라 ifPresent 로 값이 있을 때만 예외를 던짐
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
