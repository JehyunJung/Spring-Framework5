package hello.advanced.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {
    public void execute(){
        Long startTime = System.currentTimeMillis();
        //비즈니스 로직 시작
        call();
        //비즈니스 로직 종료
        Long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime: {}", resultTime);
    }

    protected abstract void call();
}
