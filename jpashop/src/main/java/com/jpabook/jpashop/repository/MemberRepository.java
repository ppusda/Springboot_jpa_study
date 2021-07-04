package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository // 스프링 빈(리포지토리)으로 자동으로 등록 해준다.
@RequiredArgsConstructor
public class MemberRepository {

    // @PersistenceContext // EntityManager를 주입 받는 데 사용되는 어노테이션
    // PersistenceInit을 이용하면 EntityManagerFactory를 주입 받을 수 있다.
    // EntityManager는 Spring Data JPA 가 없으면 이와 같이(Autowired 사용이나 lombok 사용)사용할 수는 없고,
    // 위에 쓰여있는 PersistenceContext를 사용해야한다.
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("selelct m from Member m", Member.class) // 여기서도 from의 대상이 Entity이다.
                .getResultList();
    }
    //JPQL - sql로 번역이 되어 사용되기에 본질은 같지만, 엔티티 객체를 대상으로 쿼리를 쓴다는 것에 차이가 있다.

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
