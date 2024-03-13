package com.housing.back.controller;


import com.housing.back.dto.request.auth.*;
import com.housing.back.dto.response.auth.*;
import com.housing.back.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;


    // ID 중복 검사
    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck (
            @RequestBody @Valid IdCheckRequestDto requestBody
            ){
        return authService.idCheck(requestBody);
    }

    // 이메일 인증번호 전송
    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
            @RequestBody @Valid EmailCertificationRequestDto requestDto
            ) {
        return authService.emailCertification(requestDto);
    }

    // 이메일 인증번호 확인
    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
            @RequestBody @Valid CheckCertificationRequestDto requestBody
            ) {
        return authService.checkCertification(requestBody);
    }

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp (
            @RequestBody @Valid SignUpRequestDto requestBody
            ) {
        return authService.signUp(requestBody);
    }

    // 로그인
    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn (
            @RequestBody @Valid SignInRequestDto requestBody
            ) {
        return authService.signIn(requestBody);
    }
}
