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

// @Slf4j를 사용하여 로깅
// @Controller 를 사용하여 정적 페이지를 반환하는 컨트롤러임을 명시
@Slf4j
@Controller
public class MemberController {
    // 의존성 주입 (Dependency Injection)
    // DI를 통하여 Repository를 가져옴
    @Autowired
    private MemberRepository memberRepository;
    // GetMapping으로 /signup을 라우팅
    // new 페이지 반환
    @GetMapping("/signup")
    public String signUp() {
        return "/members/new";
    }

    // memberForm으로 들어온 form 객체를 엔티티로 변환
    // DTO -> Entity를 repository에 저장
    //
    @PostMapping("/join")
    public String join(MemberForm memberForm) {
        log.info(memberForm.toString());
        Member member = memberForm.toEntity();
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/"+saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/show";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "/members/edit";
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id){
        Member target = memberRepository.findById(id).orElse(null);
        if(target != null){
            memberRepository.delete(target);
        }
        return "redirect:/members";
    }

    @GetMapping("/members")
    public String index(Model model){
        ArrayList<Member> memberEntityList = memberRepository.findAll();
        model.addAttribute("memberEntityList", memberEntityList);
        return "members/index";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form){
        Member memberEntity = form.toEntity();
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        if(target != null){
            memberRepository.save(memberEntity);
        }
        return "redirect:/members/" + memberEntity.getId();
    }
}
