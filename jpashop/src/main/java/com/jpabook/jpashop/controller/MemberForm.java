package com.jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "회원 이름은 필수 입니다") // 값이 비어있으면 오류 발생
    // implementation 'org.springframework.boot:spring-boot-starter-validation'
    // spring 상위 버전 부터는 gradle 에 위 코드 작성 필요!
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
