package com.login.login.with.jwt.domain;

import com.login.login.with.jwt.domain.enums.Role;
import com.login.login.with.jwt.dto.request.UserRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class User extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String email;

    @Column(length = 50, nullable = false, unique = true)
    private String contact;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 100, nullable = false, unique = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Auth auth;

    @Builder
    public User(Long id, String email, String contact, String username, String password, Role role) {
        this.id = id;
        this.email = email;
        this.contact = contact;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void update(UserRequestDto requestDto) {
        if(requestDto.getEmail() != null)
            this.email = requestDto.getEmail();
        if(requestDto.getContact() != null)
            this.contact = requestDto.getContact();
        if(requestDto.getUsername() != null)
            this.username = requestDto.getUsername();
        if(requestDto.getPassword() != null)
            this.password = requestDto.getPassword();
        if(requestDto.getRole() != null)
            this.role = requestDto.getRole();
    }
}
