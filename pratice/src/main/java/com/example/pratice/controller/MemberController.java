package com.example.pratice.controller;

import com.example.pratice.dto.MemberForm;
import com.example.pratice.entity.Member;
import com.example.pratice.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/signup")
    public String signUp(Model model) {
        return "/members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm) {
        System.out.println(memberForm.toString());
        Member member = memberForm.toEntity();
        Member saved = memberRepository.save(member);
        System.out.println(saved.toString());
        return "";
    }
}
