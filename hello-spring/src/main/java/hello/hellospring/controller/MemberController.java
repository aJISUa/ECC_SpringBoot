package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        // 27강: AOP 를 적용하면 진짜 MemberService 가 아니라 프록시(가짜)가 주입됨
        // 찍어보면 MemberService$$SpringCGLIB$$0 처럼 뒤에 뭔가 붙어서 나옴
        System.out.println("memberService = " + memberService.getClass());
    }

    // 18강 회원 등록 폼
    // 주소창에 치고 들어오는 건 GET. templates/members/createMemberForm.html 을 보여줌
    @GetMapping(value = "/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 18강 회원 등록
    // 주소는 같지만 form 의 method="post" 로 넘어와서 이쪽이 호출됨
    // 조회는 GET, 데이터를 등록할 때는 POST
    @PostMapping(value = "/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        // 가입이 끝나면 홈 화면으로 보냄
        return "redirect:/";
    }

    // 19강 회원 조회
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        // 리스트를 통째로 모델에 담아서 화면으로 넘김
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
