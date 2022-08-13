package hello.advanced.v1;

import hello.advanced.hellotrace.HelloTraceV1;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerV1 {
    private final OrderServiceV1 orderService;
    private final HelloTraceV1 helloTrace;

    @GetMapping("/v1/request")

    public String request(String itemId) {

        TraceStatus begin = null;
        try {
            begin = helloTrace.begin("orderController.request()");
            orderService.orderItem(itemId);
            helloTrace.end(begin);

        } catch (Exception exception) {
            helloTrace.exception(begin, exception);
            throw exception;
        }
        return "ok";
    }

}
