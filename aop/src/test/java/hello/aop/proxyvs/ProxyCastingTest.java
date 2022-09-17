package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Proxy;

@Slf4j
public class ProxyCastingTest {
    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false); // JDK proxy

        // Proxy -> MemberService Casting
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        log.info("proxy={}", memberServiceProxy.getClass());

        //Proxy -> MemberServiceImpl Casting
        Assertions.assertThatThrownBy(()->
                        {
                            MemberServiceImpl memberServiceImplProxy = (MemberServiceImpl) proxyFactory.getProxy();
                        })
                .isInstanceOf(ClassCastException.class);

    }
    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true); // CGLIB proxy

        // Proxy -> MemberService Casting
        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();
        log.info("MemberService proxy={}", memberServiceProxy.getClass());

        //Proxy -> MemberServiceImpl Casting
        MemberService memberServiceImplProxy = (MemberServiceImpl) proxyFactory.getProxy();
        log.info("MemberServiceImpl proxy={}", memberServiceImplProxy.getClass());
    }
}
