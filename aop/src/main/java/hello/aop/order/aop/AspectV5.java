package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
@Aspect
public class AspectV5 {
    @Aspect
    @Order(2)
    public static class logAspect {
        @Around("hello.aop.order.aop.Pointcuts.allOrder()")
        public Object doLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            log.info("[Log] {}", proceedingJoinPoint.getSignature());
            return proceedingJoinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    public static class txAspect {
        @Around("hello.aop.order.aop.Pointcuts.allOrderAndService()")
        public Object doTransaction(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
            try {
                log.info("[트랜잭션 시작]", proceedingJoinPoint.getSignature());
                Object result = proceedingJoinPoint.proceed();
                log.info("[트랜잭션 커밋]", proceedingJoinPoint.getSignature());
                return result;
            } catch (Exception e) {
                log.info("[트랜잭션 롤백]", proceedingJoinPoint.getSignature());
                throw e;
            } finally {
                log.info("[리소스 릴리즈]", proceedingJoinPoint.getSignature());
            }
        }
    }
}
