package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import org.junit.jupiter.api.Test;

public class DecoratorPatternTest {

    @Test
    void noDecorator(){
        Component realComponent = new RealComponent();
        DecoraterPatternClient decoraterPatternClient = new DecoraterPatternClient(realComponent);
        decoraterPatternClient.execute();
    }

    @Test
    void decorator1() {
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        DecoraterPatternClient client = new DecoraterPatternClient(messageDecorator);

        client.execute();
    }

    @Test
    void decorator2() {
        Component realComponent = new RealComponent();
        Component timeDecorator = new TimeDecorator(realComponent);
        Component messageDecorator = new MessageDecorator(timeDecorator);
        DecoraterPatternClient client = new DecoraterPatternClient(messageDecorator);

        client.execute();
    }

}
