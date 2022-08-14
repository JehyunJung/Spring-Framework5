package hello.advanced;

import hello.advanced.logtrace.FieldLogTracer;
import hello.advanced.logtrace.LogTrace;
import hello.advanced.logtrace.ThreadLocalLogTracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
//        return new FieldLogTracer();
        return new ThreadLocalLogTracer();
    }
}
