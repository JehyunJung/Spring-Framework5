package hello.advanced.v3;

import hello.advanced.hellotrace.HelloTraceV2;
import hello.advanced.logtrace.LogTrace;
import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {
    private final LogTrace tracer;
    public void save(String itemId) {
        TraceStatus begin = null;
        try {
            begin = tracer.begin("OrderRepository.request()");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            tracer.end(begin);

        } catch (Exception exception) {
            tracer.exception(begin, exception);
            throw exception;
        }

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
