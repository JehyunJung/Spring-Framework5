package hello.advanced.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloTraceV2Test {

    @Test
    void begin_end() {
        HelloTraceV2 helloTraceV2 = new HelloTraceV2();
        TraceStatus status1 = helloTraceV2.begin("Hello1");
        TraceStatus status2 = helloTraceV2.begin_sync(status1.getTraceId(), "Hello2");

        helloTraceV2.end(status2);
        helloTraceV2.end(status1);
    }

    @Test
    void begin_exception() {
        HelloTraceV2 helloTraceV2 = new HelloTraceV2();
        TraceStatus status1 = helloTraceV2.begin("Hello1");
        TraceStatus status2 = helloTraceV2.begin_sync(status1.getTraceId(), "Hello2");

        helloTraceV2.exception(status2,new IllegalStateException());
        helloTraceV2.exception(status1,new IllegalStateException());
    }

}