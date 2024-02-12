package com.login.login.with.jwt.controller;

import com.login.login.with.jwt.config.JwtTokenProvider;
import com.login.login.with.jwt.dto.request.UserRequestDto;
import com.login.login.with.jwt.dto.response.UserResponseDto;
import com.login.login.with.jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    /** 회원정보 조회 API */
    @GetMapping("/api/v1/user")
    public ResponseEntity<?> findUser(@RequestHeader("Authorization") String accessToken) {
        Long id = this.jwtTokenProvider.getUserIdFromToken(accessToken.substring(7));
        UserResponseDto userResponseDto = this.userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    /** 회원정보 수정 API */
    @PutMapping("/api/v1/user")
    public ResponseEntity<?> updateUser(@RequestHeader("Authorization") String accessToken,
                                        @RequestBody UserRequestDto requestDto) {
        Long id = this.jwtTokenProvider.getUserIdFromToken(accessToken.substring(7));
        this.userService.update(id, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /** 회원정보 삭제 API */
    @DeleteMapping("/api/v1/user")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String accessToken) {
        Long id = this.jwtTokenProvider.getUserIdFromToken(accessToken.substring(7));
        this.userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
