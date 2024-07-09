package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        // 위에서 ClientBean과 PrototypeBean 둘다 컴포넌트스캔을 해주므로 자동 빈등록이 된다

        ClientBean clientBean1 = ac.getBean(ClientBean.class); //클라이언트빈 조회
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {

//        private final PrototypeBean prototypeBean; // 생성시점에 주입

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }
// 클라이언트 1번이 logic()를 호출하면 이미 생성시점에 주입된 prototypeBean 를 사용한다
        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new
                AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
