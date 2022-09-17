package hello.aop.internalcall;

import hello.aop.exam.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CallServiceV2 {
    @Autowired
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public void external() {
        log.info("call external");
        CallServiceV2 callService = callServiceProvider.getObject();
        callService.internal();
    }
    public void internal() {
        log.info("call internal");
    }
}

