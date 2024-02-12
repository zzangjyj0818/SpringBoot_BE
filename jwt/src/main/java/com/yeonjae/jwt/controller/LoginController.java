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
    @GetMapping("/user/info")
    // 헤더의 값을 꺼내옴 -> RequestHeader를 사용하여...
    // 어떠한 값을 요청할 때, 헤더에 JWT를 같이 실어서 보내면됨
    // 이렇게 온 JWT는 스프링 시큐리티 필터에서 요청 메세지를 확인하고
    // 헤더에 토큰이 있는지, 유효한지를 검사하여 컨트롤러와의 흐름을 존재함
    // 인증된 경우에는 컨트롤러로 넘어가게됨.
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
        // parsing된 토큰으로부터 payload를 가져온 후, uid를 꺼내오는 작업임
        String username = parsedToken.getPayload().get("uid").toString();
        log.info("username : " + username);

        Claims claims = parsedToken.getPayload();
        Object roles = claims.get("rol");
        log.info("roles : " + roles);

        return new ResponseEntity<String>(parsedToken.toString(), HttpStatus.OK);
    }
}
