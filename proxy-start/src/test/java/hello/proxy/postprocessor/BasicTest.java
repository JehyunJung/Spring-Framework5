package hello.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicTest {
    @Test
    void basicConfig() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(BasicConfig.class);

        A a = ac.getBean(A.class);
        a.helloA();

        //assertThrows(NoSuchBeanDefinitionException.class, () -> ac.getBean(B.class));
        assertThatThrownBy(() -> ac.getBean(B.class)).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Slf4j
    @Configuration
    static class BasicConfig{
        @Bean(name= "beanA")
        public A a(){
            return new A();
        }
    }
    @Slf4j
    static class A{
        public void helloA(){
            log.info("hello A");
        }

    }

    @Slf4j
    static class B{
        public void helloB(){
            log.info("hello B");
        }

    }
}