package hello.aop.pointcut;

import hello.aop.member.MemberService;
import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAOP;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;


@Slf4j
@SpringBootTest
@Import(ParameterTest.ParameterAspect.class)
public class ParameterTest {
    @Autowired
    MemberService memberService;

    @Test
    void success() {
        log.info("memberService proxy:{}", memberService.getClass());
        memberService.hello("helloA");
    }

    @Slf4j
    @Aspect
    static class ParameterAspect {
        @Pointcut("execution(* hello.aop.member..*.*(..))")
        private void allMember() {

        }

        @Around("allMember()")
        public Object logArgs1(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            Object args1 = proceedingJoinPoint.getArgs()[0];
            log.info("[logArgs1]{},args={}", proceedingJoinPoint.getSignature(), args1);
            return proceedingJoinPoint.proceed();
        }

        @Around("allMember() && args(arg,..)")
        public Object logArgs1(ProceedingJoinPoint proceedingJoinPoint, Object arg) throws Throwable {
            log.info("[logArgs2]{},args={}", proceedingJoinPoint.getSignature(), arg);
            return proceedingJoinPoint.proceed();
        }

        @Before("allMember() && args(arg,..)")
        public void logArgs3(String arg) {
            log.info("[logArgs3] args={}", arg);
        }

        @Before("allMember() && this(obj)")
        public void thisArgs(JoinPoint joinPoint,MemberService obj) {
            log.info("[this]{} obj={}",joinPoint.getSignature(),obj.getClass());
        }

        @Before("allMember() && target(obj)")
        public void targetArgs(JoinPoint joinPoint,MemberService obj) {
            log.info("[target]{} obj={}",joinPoint.getSignature(),obj.getClass());
        }

        @Before("allMember() && @target(annotation)")
        public void targetArgs(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[@target]{} obj={}",joinPoint.getSignature(),annotation);
        }

        @Before("allMember() && @within(annotation)")
        public void withinArgs(JoinPoint joinPoint, ClassAop annotation) {
            log.info("[@within]{} obj={}",joinPoint.getSignature(),annotation);
        }
        @Before("allMember() && @annotation(annotation)")
        public void annotationArgs(JoinPoint joinPoint, MethodAOP annotation) {
            log.info("[@annotation]{} annotationValue={}",joinPoint.getSignature(),annotation.value());
        }

    }




}
