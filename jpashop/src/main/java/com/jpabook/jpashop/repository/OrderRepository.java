package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        // Query DSL을 사용할 예정이다.
        List<Order> list = null;
        return list;
    }
}
// 정적 쿼리가 아니라 동적 쿼리를 작성해야하는 상황이 왔다...
//        em.createQuery("select o from Order o join o.member m" +
//                "where o.status = :status" +
//                "and m.name like :name")
//                .setParameter("status", orderSearch.getOrderStatus())
//                .setParameter("name", orderSearch.getMemberName())
//                .setMaxResults(1000) // 최대 1000건
//                .getResultList();

// 동적 쿼리를 작성해보자
// 동적 쿼리의 안 좋은 예...
// 1.
/*        String jpql = "select o from Order o join o.member m";
        boolean isFirstCondition = true;

        // 주문 상태 검색
        if(orderSearch.getOrderStatus() != null) {
            if(isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else{
                jpql += " and";
            }
            jpql += "m.name like :status";
        }

        // 회원 이름 검색
        if(StringUtils.hasText(orderSearch.getMemberName())) {
            if(isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else{
                jpql += " and";
            }
            jpql += "m.name like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class)
                .setMaxResults(1000);

        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if(StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }

        return query.getResultList();*/

// 문자열을 더하는 것에는 버그도 많고 한계가 있다.
// 코드 작성하는데만 한 면이 쓰이기도 하므로 쓰면 안된다.

//2.
/*  // JPA Criteria로 해결하는 방법
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Object, Object> m = o.join("member", JoinType.INNER);

        List<Predicate> criteria = new ArrayList<>();

        // 주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        if (StringUtils.hasText(orderSearch.getMemberName())) {
           Predicate name =
                   cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
           criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000);
        return query.getResultList();
    }*/
// 1번의 상위호환... 코드도 줄고 성능도 좋지만... 너무 어렵고 유지보수가 힘들다.
// 하지만 JPA 표준스펙이기도 한 만큼... 좋은 기술이기는 하지만 실무에서 쓰기 힘든 코드이다.