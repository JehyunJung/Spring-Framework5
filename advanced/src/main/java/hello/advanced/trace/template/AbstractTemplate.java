package hello.advanced.trace.template;


import hello.advanced.logtrace.LogTrace;
import hello.advanced.trace.TraceStatus;
import lombok.RequiredArgsConstructor;

public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    public AbstractTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message){
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin(message);

            T result = call();

            trace.end(traceStatus);
            return result;
        }catch(Exception e){
            trace.exception(traceStatus, e);
            throw e;
        }

    }

    public abstract T call();
}
