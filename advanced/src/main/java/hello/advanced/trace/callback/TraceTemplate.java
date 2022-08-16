package hello.advanced.trace.callback;

import hello.advanced.logtrace.LogTrace;
import hello.advanced.trace.TraceStatus;

public class TraceTemplate {
    private final LogTrace trace;

    public TraceTemplate(LogTrace trace) {
        this.trace = trace;
    }

    public <T> T execute(String message,TraceCallBack<T> callBack){
        TraceStatus traceStatus = null;
        try {
            traceStatus = trace.begin(message);

            T result= callBack.call();

            trace.end(traceStatus);
            return result;
        }catch(Exception e){
            trace.exception(traceStatus, e);
            throw e;
        }

    }
}
