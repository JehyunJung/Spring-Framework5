package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.format.annotation.NumberFormat;

@Import(CallLogAspect.class)
@SpringBootTest(properties = "spring.main.allow-circular-references=true") //JDK Proxy
@Slf4j
class CallServiceV1Test {
    @Autowired
    CallServiceV1 callService;

    @Test
    void external() {
        callService.external();
    }

    @Test
    void internal() {
        callService.internal();
    }
}