package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)// 기본적으로 JPA 서비스에서는 트랜잭션이 있는 것이 좋다.
@RequiredArgsConstructor // 생성자 주입을 이용하자!
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 가입
    @Transactional
    public long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }// 멤버 수를 세서 0보다 크면 동작하는 로직이 더 최적화에는 좋을 수도?
    } // 만약 실무에서는 회원가입을 같은이름으로 동시에 등록하는 일도 발생할 수 있기에 이것으로만 처리하려고 하지말고
    // DB 내에서도 최후로 unique 제약조건으로 잡아주는 것이 좋다.

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}

// 조회하는 경우에는 readOnly를 사용하면 좋다.
// 전체적으로 readOnly를 붙히고, 쓰기가 필요한(등록) 곳에도 Transaction을 붙혀 쓰기를 가능하게 우선권을 준다.
// default 값은 false임....