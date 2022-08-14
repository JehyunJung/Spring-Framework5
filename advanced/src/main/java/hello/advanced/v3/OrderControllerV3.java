package hello.advanced.v3;

import hello.advanced.logtrace.LogTrace;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {
    private final OrderServiceV3 orderService;
    private final LogTrace tracer;

    @GetMapping("/v3/request")

    public String request(String itemId) {

        TraceStatus begin = null;
        try {
            begin = tracer.begin("orderController.request()");
            orderService.orderItem(itemId);
            tracer.end(begin);

        } catch (Exception exception) {
            tracer.exception(begin, exception);
            throw exception;
        }
        return "ok";
    }

}
