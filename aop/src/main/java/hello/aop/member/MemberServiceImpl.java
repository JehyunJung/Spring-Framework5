package hello.aop.member;

import hello.aop.member.MemberService;
import hello.aop.member.annotation.ClassAop;
import hello.aop.member.annotation.MethodAOP;
import org.springframework.stereotype.Component;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {
    @MethodAOP("test value")
    public String hello(String param) {
        return "ok";
    }
    public String internal(String param) {
        return "ok";
    }
}
