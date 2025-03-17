package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // 해당 클래스를 스프링 컨테이너에 빈(Bean)으로 등록
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; //1000원 할인

    @Override // 부모 클래스(또는 인터페이스)의 메서드를 재정의
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { //  등급이 VIP
            return discountFixAmount; // VIP면 1000원 할인
        }
        else {
            return 0;
        }
    }
}