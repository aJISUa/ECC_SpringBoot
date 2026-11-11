package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

// 24강 JPA 회원 리포지토리
// 반복 코드뿐 아니라 기본적인 SQL 도 JPA 가 만들어서 실행해줌
public class JpaMemberRepository implements MemberRepository {

    // JPA 는 EntityManager 로 모든 게 동작함
    // data-jpa 라이브러리를 넣으면 스프링 부트가 만들어 두니까 주입받기만 하면 됨
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        // insert 쿼리를 만들어서 넣고 id 까지 member 에 채워줌
        em.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        // PK 로 찾을 때는 타입과 식별자만 넘기면 됨
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        // PK 가 아닌 조건으로 찾거나 여러 건을 찾을 때는 JPQL 을 써야 함
        // 테이블이 아니라 엔티티(Member)를 대상으로 쿼리를 날림. select m 은 객체 자체를 조회한다는 뜻
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }
}
