package hello.proxy.pureproxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeProxy extends ConcreteLogic{
    private final ConcreteLogic realLogic;

    public TimeProxy(ConcreteLogic realLogic) {
        this.realLogic = realLogic;
    }

    @Override
    public String operation() {
        log.info("Time Decorator 실행");
        Long start = System.currentTimeMillis();

        String result = realLogic.operation();

        Long end = System.currentTimeMillis();
        Long executionTime = end - start;

        log.info("Time Decorator 종료, 실행: {}ms", executionTime);
        return result;

    }
}
