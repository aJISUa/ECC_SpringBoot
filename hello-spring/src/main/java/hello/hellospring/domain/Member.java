package hello.hellospring.domain;

// 11강 회원 도메인
// 요구사항의 데이터는 회원ID, 이름 두 개뿐
public class Member {

    // id 는 고객이 정하는 값이 아니라 시스템이 저장할 때 정해주는 값
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
