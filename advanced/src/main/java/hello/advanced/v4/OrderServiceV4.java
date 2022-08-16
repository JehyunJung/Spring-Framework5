package hello.advanced.v4;

import hello.advanced.logtrace.LogTrace;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {
    private final OrderRepositoryV4 orderRepository;
    private final LogTrace tracer;
    public void orderItem(String itemId) {
        AbstractTemplate<Void> template=new AbstractTemplate<Void>(tracer) {
            @Override
            public Void call() {
                orderRepository.save(itemId);
                return null;
            }
        };
        template.execute("OrderService.save()");
    }

}
