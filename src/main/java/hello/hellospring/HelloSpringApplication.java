package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 3강 프로젝트 생성
// main 메서드만 실행하면 내장 톰캣이 같이 떠서 8080 포트로 서버가 올라옴
// 톰캣을 따로 설치할 필요 없음
@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
