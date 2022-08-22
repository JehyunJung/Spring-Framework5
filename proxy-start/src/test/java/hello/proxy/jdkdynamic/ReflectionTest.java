package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0(){
        Hello hello = new Hello();

        //공통 로직 1 시작
        log.info("start1");
        String result1 = hello.callA();
        log.info("result: {}", result1);
        //공통 로직 1 종료


        //공통 로직 2 시작
        log.info("start2");
        String result2 = hello.callB();
        log.info("result: {}", result2);
        //공통 로직 2 종료
    }

    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();

        //callA 메소드 정보
        Method callA = aClass.getMethod("callA");
        Object result1 = callA.invoke(target);
        log.info("result: {}", result1);

        //callA 메소드 정보
        Method callB = aClass.getMethod("callB");
        Object result2 = callB.invoke(target);
        log.info("result: {}", result2);


    }

    @Test
    void reflection2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> aClass = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
        Hello target = new Hello();

        //callA 메소드 정보
        Method callA = aClass.getMethod("callA");
        dynamicCall(callA, target);

        //callA 메소드 정보
        Method callB = aClass.getMethod("callB");
        dynamicCall(callB, target);
    }

    private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {

        //공통 로직 1 시작
        log.info("start1");
        String result1 = (String) method.invoke(target);
        log.info("result: {}", result1);
        //공통 로직 1 종료
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
