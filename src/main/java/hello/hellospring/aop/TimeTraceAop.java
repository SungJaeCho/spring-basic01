package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//@Component
public class TimeTraceAop {

//    @Around("execution(* hello.hellospring..*(..))")
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)") //순환참조 오류방지
    public Object exxcute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toLongString());
        try{
            //다음 메서드로 진행
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("FINISH: " + joinPoint.toLongString() + " " + timeMs + "ms");
        }
    }
}
