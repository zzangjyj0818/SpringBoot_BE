package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


// DTO : 서버의 컨트롤러가 객체에 담아내는 것을 말함.
@Controller
public class MemberController {
    // DI 의존성 주입
    @Autowired
    private MemberRepository memberRepository;
    // URL 요청 점수
    @GetMapping("/signup")
    public String newMemberForm() {
        return "members/new";
    }

    @PostMapping("/join")
    // Form data를 DTO로 받음
    public String createMember(MemberForm form){
        System.out.println(form.toString());
        // Exchange object to DTO
        // DTO를 엔티티로 변환하기 위해 form 객체의 toEntity()라는 메서드를 호출해서
        // 그 반환 값을 Member 타입의 member 엔티티에 저장함.
        Member member = form.toEntity();
        // Exchange DTO to Entity
        Member saved = memberRepository.save(member);
        return "";
    }
}
