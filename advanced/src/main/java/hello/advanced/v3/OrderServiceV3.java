package hello.advanced.v3;

import hello.advanced.hellotrace.HelloTraceV2;
import hello.advanced.logtrace.LogTrace;
import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {
    private final OrderRepositoryV3 orderRepository;
    private final LogTrace tracer;
    public void orderItem(String itemId) {
        TraceStatus begin = null;
        try {
            begin = tracer.begin("OrderService.request()");
            orderRepository.save(itemId);
            tracer.end(begin);

        } catch (Exception exception) {
            tracer.exception(begin, exception);
            throw exception;
        }
    }

}
