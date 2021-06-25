package com.jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // 스프링 부트 thymeleaf 의 viewName 매핑 ex) hello.html으로 찾아감
    public String hello(Model model) { // 데이터를 뷰에 넘길 수 있도록 도와주는 모델 인터페이스
        model.addAttribute("data", "hello, Spring!!");
        return "hello";
    }
}
