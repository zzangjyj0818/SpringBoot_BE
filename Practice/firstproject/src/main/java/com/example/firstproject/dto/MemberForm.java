package com.example.firstproject.dto;

import com.example.firstproject.entity.Member;

public class MemberForm {
    // 이메일과 비밀번호를 받을 필드
    private String email;
    private String password;

    // 전송받은 이메일과 비밀번호를 필드에 저장하는 생성자를 추가하였음.
    public MemberForm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberForm{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Member toEntity() {
        return new Member(null, email, password);
    }
}
