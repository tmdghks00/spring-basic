package hello.core.member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component // 스프링 빈으로 등록
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); // 회원 정보를 저장하는 HashMap

    @Override
    public void save(Member member) { // 회원 정보 저장
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) { // 회원 ID로 회원 정보 조회
        return store.get(memberId);
    }
}