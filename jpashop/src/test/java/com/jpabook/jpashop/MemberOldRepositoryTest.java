//package com.jpabook.jpashop;
//
//import com.jpabook.jpashop.study.MemberRepository_Old;
//import com.jpabook.jpashop.study.Member_Old;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.transaction.Transactional;
//
//// @Runwith 또한 import가 안되는 오류가 발생, 업데이트 되면서 사라진 듯 하다. JUnit5로 실행했음
//
//@SpringBootTest
//class MemberOldRepositoryTest {
//
//    @Autowired
//    MemberRepository_Old memberRepositoryOld;
//
//    @Test
//    @Transactional
//    // 해당 오류가 발생, 어노테이션을 붙혀준다.
//    // 데이터 넣고 롤백하는 어노테이션, 하기 싫다면 @Rollback(false) 를 통해 막을 수 있다.
//    public void testMember() throws Exception {
//        //given
//        Member_Old memberOld = new Member_Old();
//        memberOld.setUsername("memberA");
//
//        //when
//        Long saveId = memberRepositoryOld.save(memberOld);
//        Member_Old findMemberOld = memberRepositoryOld.find(saveId);
//
//        //then
//        Assertions.assertThat(findMemberOld.getId()).isEqualTo(memberOld.getId());
//        Assertions.assertThat(findMemberOld.getUsername()).isEqualTo(memberOld.getUsername());
//        Assertions.assertThat(findMemberOld).isEqualTo(memberOld); // True
//        System.out.println("findMember == member" + (findMemberOld == memberOld));
//
//        // 영속성 컨텍스트 내에서 id 값이 같기 때문에 true
//        // 엔티티와 DB 사이에서 엔티티를 관리하고 DB에 저장도 해주는 것이다.
//
//        // 커맨드로 테스트 실행 gradlew 실행하면 됨, gradlew clean build
//        // java -jar jpashop-0.0.1-SNAPSHOT.jar 로 파일 실행
//    }
//
//}