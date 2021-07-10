package com.jpabook.jpashop.controller;

import com.jpabook.jpashop.domain.Address;
import com.jpabook.jpashop.domain.Member;
import com.jpabook.jpashop.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new") // form 화면 열기
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        // memberForm이라는 빈 멤버껍데기를 가져간다. == 값 전송
        return "members/createMemberForm";  // html 파일로 전송
    }

    @PostMapping("/members/new") // 데이터 등록
    public String create(@Valid MemberForm form, BindingResult result) {
        // javax.validation, @Valid 기능... 을 통해 MemberForm 안에 @NotEmpty를 읽어와 필수 값 설정
        // Member를 쓰지 않고 MemberForm을 쓰는 이유... form으로 화면을 왔다갔다 할 때
        // Member에 validation 같은 값들을 설정하거나 하면 너무 더러워지고 복잡해진다. => 차라리 따로 만들어서 필요한 것들만 관리하는 것이 좋다.

        if (result.hasErrors()){
            return "members/createMemberForm";
        } // error 가 있다고 전달!

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
    // API를 만들 때는 절대 Entity를 외부로 반환해서는 안된다.
    // 보안성의 문제, 불완전함.
    // Member를 그대로 반환하기 보다는 Member DTO나 DataTransObject를 통해 반환하는 게 제일 깔끔하다.

}
