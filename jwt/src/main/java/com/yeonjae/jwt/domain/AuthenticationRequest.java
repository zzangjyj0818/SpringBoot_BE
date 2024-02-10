package com.yeonjae.jwt.domain;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;

}
