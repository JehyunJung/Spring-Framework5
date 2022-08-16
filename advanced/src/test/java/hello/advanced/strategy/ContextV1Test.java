package hello.advanced.strategy;

import hello.advanced.strategy.code.strategy.ContextV1;
import hello.advanced.strategy.code.strategy.Strategy;
import hello.advanced.strategy.code.strategy.StrategyLogic1;
import hello.advanced.template.code.AbstractTemplate;
import hello.advanced.template.code.SubClassLogic1;
import hello.advanced.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    public void logic1() {
        Long startTime = System.currentTimeMillis();
        //비즈니스 로직 시작
        log.info("비즈니스 로직 1 시작");
        //비즈니스 로직 종료
        Long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime: {}", resultTime);
    }

    public void logic2() {
        Long startTime = System.currentTimeMillis();
        //비즈니스 로직 시작
        log.info("비즈니스 로직 2 시작");
        //비즈니스 로직 종료
        Long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime: {}", resultTime);
    }

    @Test
    void strategy1() {
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(strategyLogic1);
        contextV1.execute();

        StrategyLogic1 strategyLogic2 = new StrategyLogic1();
        ContextV1 contextV2 = new ContextV1(strategyLogic2);
        contextV1.execute();
    }

    @Test
    void strategy2() {
        ContextV1 contextV1 = new ContextV1(() -> {
            log.info("비즈니스 로직 1 실행");
        });
        contextV1.execute();

        ContextV1 contextV2 = new ContextV1(()->{
            log.info("비즈니스 로직 2 실행");
        });
        contextV2.execute();
    }
}
