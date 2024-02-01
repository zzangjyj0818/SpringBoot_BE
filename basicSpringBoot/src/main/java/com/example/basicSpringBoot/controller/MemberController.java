package com.example.basicSpringBoot.controller;

import com.example.basicSpringBoot.dto.MemberForm;
import com.example.basicSpringBoot.entity.Member;
import com.example.basicSpringBoot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String singUp(){
        return "/members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm){
        Member member = memberForm.toEntity();
        Member saved = memberRepository.save(member);
        return "";
    }
}
