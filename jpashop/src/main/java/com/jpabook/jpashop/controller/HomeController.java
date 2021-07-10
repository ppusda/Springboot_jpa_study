package com.jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    // Logger log = LoggerFactory.getLogger(getClass()); 이런식으로 로그를 뽑을 수 있지만
    // Lombok을 이용해서 뽑아보자 -> Slf4j

    @RequestMapping("/") // 첫번째 화면으로 가면 맵핑
    public String home() {
        log.info("home controller");
        return "home"; // 이렇게 하면 home.html 로 찾아간다.
    }



}
