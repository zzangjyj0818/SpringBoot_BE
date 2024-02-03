package com.example.basicSpringBoot.controller;

import com.example.basicSpringBoot.dto.MemberForm;
import com.example.basicSpringBoot.entity.Member;
import com.example.basicSpringBoot.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/signup")
    public String singUp(){
        return "/members/new";
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        List<Member> memberEntityList = memberRepository.findAll();
        model.addAttribute("memberList", memberEntityList);
        return "/members/index";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm){
        Member member = memberForm.toEntity();
        Member saved = memberRepository.save(member);
        return "";
    }
}
