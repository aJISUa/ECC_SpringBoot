package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 17강 회원 웹 기능 - 홈 화면 추가
@Controller
public class HomeController {

    // localhost:8080 으로 들어오면 templates/home.html 을 보여줌
    // 컨트롤러가 정적 파일보다 우선순위가 높아서 static/index.html 은 이제 안 나옴
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
