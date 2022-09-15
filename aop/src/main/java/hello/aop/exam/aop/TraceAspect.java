package hello.aop.exam.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class TraceAspect {
    @Before("@annotation(hello.aop.exam.annotation.Trace) && args(args)")
    public void doTrace(JoinPoint joinPoint,String args) {
        log.info("[trace] {} args={}", joinPoint.getSignature(), args);
    }
}
