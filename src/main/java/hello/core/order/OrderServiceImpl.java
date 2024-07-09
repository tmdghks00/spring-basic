package hello.core.order;

import hello.core.annotataion.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
// 롬복 라이브러리가 제공하는 @RequiredArgsConstructor 기능을 사용하면
// final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
 
    private final MemberRepository memberRepository; // 불변 - 생성자 주입
    private final DiscountPolicy discountPolicy; // 불변 - 생성자 주입
//private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//private DiscountPolicy discountPolicy; // 구체화에 의존하지않고 인터페이스에만 의존함

//    @Autowired // 수정자 주입(setter 주입)-> MemberRepository 앞에 final를 지워야함
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired // 수정자 주입(setter 주입)-> MemberRepository 앞에 final를 지워야함
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

// 생성자가 1개이므로 @Autowired를 생략해도 자동주입된다

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,
                            @MainDiscountPolicy DiscountPolicy
                                    discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;

    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원정보조회
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 할인정책에다가 회원을 넘김
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    // 주문생성요청이 오면 회원정보를 먼저 조회하고 할인정책에다가 회원을 넘김
// 그리고 최종생성된 주문을 반환한다

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
