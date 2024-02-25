package com.login.login.with.jwt.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 각각의 필드는 다음과 같은 역할을 함
 * tokenType: JWT 사용시 추후 Bearer 타입을 가지게 됩니다.
 * accessToken: 액세스 요청을 위한 수명이 짧은 Token
 * refreshToken: 새 accessToken을 발급받기 위한 수명이 긴 Token
 * expirationDate: accessToken, refreshToken 만료 일자
 */
@NoArgsConstructor
@Entity
@Getter
public class Auth extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tokenType;

    @Column(nullable = false)
    private String accessToken;

    @Column(nullable = false)
    private String refreshToken;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Auth(User user, String tokenType, String accessToken, String refreshToken) {
        this.user = user;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public void updateAccessToken(String accessToken) {
        if(accessToken != null)
            this.accessToken =accessToken;
    }

    public void updateRefreshToken(String refreshToken) {
        if(refreshToken != null)
            this.refreshToken = refreshToken;
    }
}

/**
 * Access Token vs Refresh Token 차이
 * Access Token은 인증에 성공한 후 사용자에게 발급되는 단기 토큰입니다.
 * 일반적으로 보호된 리소스 또는 서비스에 대한 요청을 인증하는 데 사용됩니다.
 * 액세스 토큰은 도난당하고 악의적으로 사용될 위험을 최소화하기 위해 수명이 짧습니다.
 * 액세스 토큰이 만료되면 사용자는 새 토큰을 얻기 위해 다시 인증해야 합니다.
 *
 * 반면, Refresh Token은 새 액세스 토큰을 얻는 데 사용되는 수명이 긴 토큰입니다.
 * 액세스 토큰이 만료되면 사용자는 새로 고침 토큰을 사용하여 재인증 없이 새 액세스 토큰을 얻을 수 있습니다.
 * 이것은 인증 요청의 빈도를 줄이고 시스템을 보다 사용자 친화적으로 만듭니다.
 */