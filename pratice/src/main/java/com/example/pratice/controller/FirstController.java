package com.example.pratice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        // 모델에서 변수를 등록할 때는 addAttribute(var, value)를 사용함
        model.addAttribute("username", "정연재");
        return "greetings";
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "정연재");
        return "goodbye";
    }
}
