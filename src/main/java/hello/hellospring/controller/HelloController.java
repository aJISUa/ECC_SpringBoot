package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 컨트롤러는 @Controller 를 붙여야 함
@Controller
public class HelloController {

    // 5강 View 환경설정
    // localhost:8080/hello 로 GET 요청이 오면 이 메서드가 호출됨
    @GetMapping("hello")
    public String hello(Model model) {
        // model 에 key=data, value=hello!! 로 담음. 템플릿에서 ${data} 로 꺼냄
        model.addAttribute("data", "hello!!");
        // 문자를 반환하면 viewResolver 가 resources/templates/hello.html 을 찾아서 처리함
        // resources:templates/ + {ViewName} + .html
        return "hello";
    }

    // 8강 MVC와 템플릿 엔진
    // localhost:8080/hello-mvc?name=spring
    // @RequestParam 은 required 기본값이 true 라서 ?name= 을 안 넘기면 400 에러 남
    // 안 넘겨도 되게 하려면 @RequestParam(value = "name", required = false)
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // URL 파라미터로 받은 name 을 model 에 그대로 담아서 뷰로 넘김
        model.addAttribute("name", name);
        return "hello-template";
    }

    // 9강 API - 문자 반환
    // localhost:8080/hello-string?name=spring
    // @ResponseBody 는 HTML 의 <body> 가 아니라 HTTP 응답의 body 를 말함
    // viewResolver 를 안 거치고 반환값을 HTTP body 에 그대로 넣음
    // 소스 보기 하면 HTML 태그 없이 hello spring 문자만 있음
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // 9강 API - 객체 반환
    // localhost:8080/hello-api?name=spring  ->  {"name":"spring"}
    // @ResponseBody 에서 객체를 반환하면 HttpMessageConverter 가 JSON 으로 바꿔줌
    // 문자는 StringHttpMessageConverter, 객체는 MappingJackson2HttpMessageConverter
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    // static 클래스로 만들면 HelloController.Hello 로 쓸 수 있음
    // 필드가 private 이라 getter, setter 로 접근함 (자바빈 규약, 프로퍼티 접근 방식)
    // JSON 의 key 는 getter 이름(getName -> name)에서 나옴
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
