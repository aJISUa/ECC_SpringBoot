package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 25강 스프링 데이터 JPA 회원 리포지토리
// 인터페이스만 만들면 됨. 구현 클래스는 스프링 데이터 JPA 가 만들어서 스프링 빈으로 등록해줌
// JpaRepository<엔티티, PK 타입> 을 상속하면 save, findById, findAll 같은 기본 CRUD 가 다 들어 있음
// 인터페이스끼리는 extends 로 여러 개를 상속할 수 있음
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 공통으로 제공할 수 없는 건 메서드 이름 규칙으로 만듦
    // findByName 이라고 쓰면 select m from Member m where m.name = ? 을 만들어줌
    Optional<Member> findByName(String name);
}
