package com.yeonjae.jwt.controller;

import com.yeonjae.jwt.constants.SecurityConstants;
import com.yeonjae.jwt.domain.AuthenticationRequest;
import com.yeonjae.jwt.prop.JwtProp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class LoginController {
    @Autowired
    private JwtProp jwtProp;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request){
        String username = request.getUsername();
        String password = request.getPassword();

        log.info("username : " + username);
        log.info("password : " + password);

        // 사용자 권한
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");

        // 시크릿키 -> 바이트 변환
        byte[] signingKey = jwtProp.getSecretKey().getBytes();
        // 토큰 생성
        String jwt = Jwts.builder()
//                .signWith(시크릿 키, 알고리즘)
                // 시그니처에 사용할 비밀키, 알고리즘을 설정함.
                .signWith(Keys.hmacShaKeyFor(signingKey), Jwts.SIG.HS512)
                // 헤더에 들어갈 정보 작성
                .header()
                // 토큰 타입 기재
                .add("typ", SecurityConstants.TOKEN_TYPE)
                .and()
                // 토큰 만료 시간
                .expiration(new Date(System.currentTimeMillis()+ 1000*60*60*24*5))
                // 정보들을 헤더에 추가
                // PAYLOAD - Uid(Username), Rol(roles)
                .claim("uid", username)
//                .claim("password", password)
                .claim("rol", roles)
                .compact();

        log.info("jwt : " + jwt);

        return new ResponseEntity<String>(jwt, HttpStatus.OK);
    }

    // 토큰 해석
    @GetMapping("/uesr/info")
    // 헤더의 값을 꺼내옴 -> RequestHeader를 사용하여
    public ResponseEntity<?> userInfo(@RequestHeader(name="Authorization") String header){
        log.info("==== header ====");
        log.info("Authorization : " + header);

        // Authorization : Bearer ${jwt}
        // Bearer를 걷어내야지 jwt를 가져옴
        // 아래 구문은 토큰만을 가져오는 구문임.
        String jwt = header.replace(SecurityConstants.TOKEN_PREFIX, "");

        byte[] signingKey = jwtProp.getSecretKey().getBytes();

        Jws<Claims> parsedToken =
                Jwts.parser()
                .verifyWith( Keys.hmacShaKeyFor(signingKey))
                .build()
                .parseSignedClaims(jwt);

        // uid : user
        String username = parsedToken.getPayload().get("uid").toString();
        log.info("username : " + username);

        Claims claims = parsedToken.getPayload();
        Object roles = claims.get("rol");
        log.info("roles : " + roles);

        return new ResponseEntity<String>(parsedToken.toString(), HttpStatus.OK);
    }
}
