package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 15강 컴포넌트 스캔과 자동 의존관계 설정
// @Controller 가 있으면 스프링이 뜰 때 이 객체를 만들어서 스프링 컨테이너에 넣고 관리함
@Controller
public class MemberController {

    // new MemberService() 로 직접 만들지 않고 스프링 컨테이너에 등록된 하나를 받아서 씀
    private final MemberService memberService;

    // 생성자에 @Autowired 가 있으면 스프링이 컨테이너에서 MemberService 를 찾아서 넣어줌 (DI)
    // MemberService 가 스프링 빈으로 등록되어 있지 않으면
    // Consider defining a bean of type "hello.hellospring.service.MemberService" 오류가 남
    // 생성자가 1개만 있으면 @Autowired 는 생략할 수 있음
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
