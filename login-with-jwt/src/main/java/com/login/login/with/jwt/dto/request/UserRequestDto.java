package com.login.login.with.jwt.dto.request;


import com.login.login.with.jwt.domain.User;
import com.login.login.with.jwt.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    private Role role;
    private String email;
    private String contact;
    private String username;
    private String password;

    public User toEntity() {
        return User.builder()
                .role(this.role)
                .email(this.email)
                .contact(this.contact)
                .username(this.username)
                .password(this.password)
                .build();
    }
}
