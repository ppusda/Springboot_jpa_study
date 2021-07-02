package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long Id;

    private String name;

    @Embedded // 내장 타입을 가지고 있다는 뜻
    private Address address;

    @OneToMany(mappedBy = "member") // 일대다 관계 명시, Member 와 Order 와의 관계
    private List<Order> orders = new ArrayList<>();

}

// Member는 Orders를 list로 가지고 있다
// 반대쪽도 마찬가지기에 우리는 연관관계의 주인을 설정해야한다.
// 이는 foreign key가 가까운 곳으로 해야한다. == Order Entity

