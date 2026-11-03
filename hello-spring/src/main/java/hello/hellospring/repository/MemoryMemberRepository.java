package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
// 11강 회원 리포지토리 메모리 구현체
public class MemoryMemberRepository implements MemberRepository {

    // key 는 회원 id, value 는 회원
    private static Map<Long, Member> store = new HashMap<>();
    // 0, 1, 2 ... key 값을 만들어주는 용도
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        // 이름은 넘어온 상태고 id 는 여기서 정해줌
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 결과가 null 이어도 Optional 로 감싸서 반환함
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 이름이 같은 회원을 하나라도 찾으면 반환, 끝까지 없으면 빈 Optional
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    // 12강 테스트가 끝날 때마다 저장소를 비우는 용도
    public void clearStore() {
        store.clear();
    }
}
