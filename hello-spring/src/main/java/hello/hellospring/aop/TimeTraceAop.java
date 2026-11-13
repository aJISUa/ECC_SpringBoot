package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// 27강 시간 측정 AOP
// 시간 측정은 공통 관심 사항이고 회원가입, 회원 조회는 핵심 관심 사항. 둘을 분리함
// @Aspect 를 붙여야 AOP 로 쓸 수 있음
// 스프링 빈으로 등록해야 해서 @Component 를 붙임. SpringConfig 에 @Bean 으로 등록해도 됨
@Component
@Aspect
public class TimeTraceAop {

    // 어디에 적용할지 정하는 부분
    // hello.hellospring 패키지와 그 하위의 모든 메서드에 적용함
    // 서비스에만 걸고 싶으면 execution(* hello.hellospring.service..*(..)) 처럼 좁히면 됨
    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.toString());

        try {
            // 다음 메서드(진짜 대상)로 넘어감
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println("END: " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}
