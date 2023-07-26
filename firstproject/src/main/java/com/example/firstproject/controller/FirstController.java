package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
// 이 파일이 컨트롤러임을 @Controller 라는 어노테이션을 사용하여 선언
public class FirstController {
    @GetMapping("/hi")
    // 클라이언트로부터 "/hi"라는 요청을 받아 접수
    public String niceToMeetYou(Model model){
        // 뷰 템플릿 페이지에서 사용할 변수를 등록하기 위해 모델 객체를 매개 변수로 가져옴.
        model.addAttribute("username", "정연재");
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "정연재");
        return "goodbye";
    }
}
