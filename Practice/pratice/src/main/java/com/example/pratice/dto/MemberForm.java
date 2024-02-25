package com.example.pratice.dto;

import com.example.pratice.entity.Member;
import lombok.AllArgsConstructor;
import lombok.ToString;

// DTO : Form Object
// DTO 클래스 안에 엔티티로 변환하는 메서드가 존재함 => toEntity
@AllArgsConstructor
@ToString
public class MemberForm {
    private Long id;
    private String email;
    private String password;

    public Member toEntity() {
        return new Member(id, email, password);
    }
}
