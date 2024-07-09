package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    @Service
    static class TestBean {
// required = false를 해두면 의존관계가 없으므로 아래 메소드 자체가 호출이 안된다
        @Autowired(required = false)
        public void setBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }
// @Nullable은 호출은 되지만 null로 들어온다
        @Autowired(required = false)
        public void setBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }
// Optional<>는 자동 주입할 대상이 없으면 Optional.empty가 입력된다
        @Autowired(required = false)
        public void setBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }

    }
}
