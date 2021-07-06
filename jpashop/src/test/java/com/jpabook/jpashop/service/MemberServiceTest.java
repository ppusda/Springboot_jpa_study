package com.jpabook.jpashop.service;

import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;


    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long savedId = memberService.join(member);

        //then
        em.flush(); // rollback 되는 insert 문 까지 볼 수 있는 코드
        assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test // (expected = IllegalStateException.class) JUnit4 에서 사용한 방법
    public void 중복_회원_예제() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);

//        memberService.join(member1);
//        try {
//            memberService.join(member2); // 예외가 발생하는 구간
//        } catch (IllegalStateException e) {
//            return;
//        } 너무 복잡하기에 다른 방법을 사용해보자.

        //then
        Assertions.assertThrows(IllegalStateException.class,
                () -> memberService.join(member2)); // 해당 코드를 실행했을 때 이 예외가 발생해야함.
    }

}

// DB 트랜잭션은 커밋이 이루어지는 순간에 저장이 되는 형식이지만
// Spring의 트랜잭션은 기본적으로 롤백을 통해 작동된다.