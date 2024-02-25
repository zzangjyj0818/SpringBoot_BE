package com.login.login.with.jwt.controller;

import com.login.login.with.jwt.dto.request.AuthRequestDto;
import com.login.login.with.jwt.dto.request.UserRequestDto;
import com.login.login.with.jwt.dto.response.AuthResponseDto;
import com.login.login.with.jwt.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {
    @Autowired
    private final AuthService authService;

    /** 로그인 API */
    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto requestDto) {
        AuthResponseDto responseDto = authService.login(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    /** 회원가입 API */
    @PostMapping("/api/v1/auth/signup")
    public ResponseEntity<?> singUp(@RequestBody UserRequestDto requestDto) {
        log.info("Request DTO : " + requestDto.getPassword());
        authService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(requestDto);
    }

    /** 토큰갱신 API */
    @GetMapping("/api/v1/auth/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("REFRESH_TOKEN") String refreshToken) {
        String newAccessToken = this.authService.refreshToken(refreshToken);
        return ResponseEntity.status(HttpStatus.OK).body(newAccessToken);
    }
}