package hello.advanced.v2;

import hello.advanced.hellotrace.HelloTraceV1;
import hello.advanced.hellotrace.HelloTraceV2;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {
    private final OrderServiceV2 orderService;
    private final HelloTraceV2 helloTrace;

    @GetMapping("/v2/request")

    public String request(String itemId) {

        TraceStatus begin = null;
        try {
            begin = helloTrace.begin("orderController.request()");
            orderService.orderItem(begin.getTraceId(),itemId);
            helloTrace.end(begin);

        } catch (Exception exception) {
            helloTrace.exception(begin, exception);
            throw exception;
        }
        return "ok";
    }

}
