package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    //1. call AppConfig.memberService 가 처음에 호출
    //그 다음으로 new 하면서 memberRepository()기 호출되서
    //2. call AppConfig.memberRepository 가 출력될 것이다
    //3. call AppConfig.memberRepository
    //4. call AppConfig.orderService
    //5. call AppConfig.memberRepository가 마지막으로 호출
    // >> 결과적으로는 memberRepository가 3번 호출되야 한다. 테스트를 돌려보자

//    그런데 출력 결과는 모두 1번만 호출되고 memberRepository도 1번만 호출된다
//    call AppConfig.memberService
//    call AppConfig.memberRepository
//    call AppConfig.orderService
// >> 위를 통해서 스프링은 어느방법을 써서라도 싱글톤을 보장해준다는것을 알수있다.

    @Bean
    public MemberService memberService() {
        //1번
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //2번? 3번?
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        //1번
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}

