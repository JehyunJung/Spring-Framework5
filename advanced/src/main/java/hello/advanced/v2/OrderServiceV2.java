package hello.advanced.v2;

import hello.advanced.hellotrace.HelloTraceV1;
import hello.advanced.hellotrace.HelloTraceV2;
import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 helloTrace;
    public void orderItem(TraceId previoudId,String itemId) {
        TraceStatus begin = null;
        try {
            begin = helloTrace.begin_sync(previoudId,"OrderService.request()");
            orderRepository.save(begin.getTraceId(),itemId);
            helloTrace.end(begin);

        } catch (Exception exception) {
            helloTrace.exception(begin, exception);
            throw exception;
        }
    }

}
