package com.jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable // 이쪽에서도 표시해준다... 내장타입 가지고 있다는 것
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}

// @setter를 없애고 생성자에서 값을 모두 초기화해서 변경 불가능한 클래스를 만듦.
// 생성자는 public이나 protected 중 사용해야만 한다.