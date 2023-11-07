package com.example.pratice.controller;

import com.example.pratice.dto.MemberForm;
import com.example.pratice.entity.Member;
import com.example.pratice.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Slf4j
@Controller
public class MemberController {
    // 의존성 주입 (Dependency Injection)
    @Autowired
    private MemberRepository memberRepository;
    @GetMapping("/signup")
    public String signUp(Model model) {
        return "/members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm) {
        log.info(memberForm.toString());
        Member member = memberForm.toEntity();
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "";
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        ArrayList<Member> memberEntityList = memberRepository.findAll();
        model.addAttribute("memberEntityList", memberEntityList);
        return "members/index";
    }
}
