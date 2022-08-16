package hello.advanced.template;

import hello.advanced.template.code.AbstractTemplate;
import hello.advanced.template.code.SubClassLogic1;
import hello.advanced.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    @Test
    void templateMethodV1(){
        AbstractTemplate abstractTemplate1 = new SubClassLogic1();
        abstractTemplate1.execute();

        AbstractTemplate abstractTemplate2 = new SubClassLogic2();
        abstractTemplate2.execute();

    }

    @Test
    void templateMethodV2(){
        AbstractTemplate abstractTemplate1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 1 실행");
            }
        };
        abstractTemplate1.execute();

        AbstractTemplate abstractTemplate2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직 2 실행");
            }
        };
        abstractTemplate2.execute();
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
}

