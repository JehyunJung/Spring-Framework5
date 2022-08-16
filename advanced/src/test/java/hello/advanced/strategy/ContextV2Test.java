package hello.advanced.strategy;

import hello.advanced.strategy.code.strategy.ContextV1;
import hello.advanced.strategy.code.strategy.ContextV2;
import hello.advanced.strategy.code.strategy.StrategyLogic1;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {
    @Test
    void strategy1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic1());

    }
    @Test
    void strategy2() {
        ContextV2 context = new ContextV2();
        context.execute(() -> {
            log.info("비즈니스 로직 1 실행");
        });

        context.execute(() -> {
            log.info("비즈니스 로직 2 실행");
        });

    }
}
