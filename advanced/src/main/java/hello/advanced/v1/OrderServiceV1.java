package hello.advanced.v1;

import hello.advanced.hellotrace.HelloTraceV1;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {
    private final OrderRepositoryV1 orderRepository;
    private final HelloTraceV1 helloTrace;
    public void orderItem(String itemId) {
        TraceStatus begin = null;
        try {
            begin = helloTrace.begin("OrderService.request()");
            orderRepository.save(itemId);
            helloTrace.end(begin);

        } catch (Exception exception) {
            helloTrace.exception(begin, exception);
            throw exception;
        }
    }

}
