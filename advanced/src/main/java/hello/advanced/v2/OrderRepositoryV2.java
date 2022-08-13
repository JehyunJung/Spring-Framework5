package hello.advanced.v2;

import hello.advanced.hellotrace.HelloTraceV1;
import hello.advanced.hellotrace.HelloTraceV2;
import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {
    private final HelloTraceV2 helloTrace;
    public void save(TraceId previousId, String itemId) {
        TraceStatus begin = null;
        try {
            begin = helloTrace.begin_sync(previousId,"OrderRepository.request()");
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생");
            }
            sleep(1000);
            helloTrace.end(begin);

        } catch (Exception exception) {
            helloTrace.exception(begin, exception);
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
