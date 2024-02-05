package com.example.basicSpringBoot.dto;

import com.example.basicSpringBoot.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class MemberForm {
    private Long id;
    private String email;
    private String password;

    public Member toEntity(){
        return new Member(id, email, password);
    }
}
