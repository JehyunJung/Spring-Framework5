package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component{

    private Component component;

    public MessageDecorator(Component realComponent) {
        this.component = realComponent;
    }

    @Override
    public String operation() {
        log.info("MessageDecorator 실행");
        String operation= component.operation();
        String decoResult = "****" + operation + "****";

        log.info("before:{}, after: {}", operation, decoResult);
        return decoResult;
    }
}
