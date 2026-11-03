package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

// 11강 회원 리포지토리 인터페이스
// 아직 데이터 저장소가 정해지지 않아서 인터페이스로 만들고 구현 클래스를 바꿔 끼울 수 있게 함
public interface MemberRepository {
    Member save(Member member);

    // 없으면 null 이 나올 수 있어서 Optional 로 감싸서 반환함
    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    List<Member> findAll();
}
