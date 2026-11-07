package hello.hellospring.controller;

// 18강 웹 등록 화면에서 데이터를 전달 받을 폼 객체
// createMemberForm.html 의 input name="name" 과 필드 이름이 같아야 함
// 스프링이 setName 을 호출해서 값을 넣어줌
public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
