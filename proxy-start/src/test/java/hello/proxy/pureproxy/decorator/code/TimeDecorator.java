package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component{

    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        log.info("TimeDecorator 실행");
        Long start = System.currentTimeMillis();

        String result= component.operation();

        Long end = System.currentTimeMillis();
        Long executionTime = end - start;
        log.info("Time Decorator 종료, resultTime:{}ms", executionTime);
        return result;
    }
}
