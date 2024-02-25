package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// 이 클래스가 Entity 임을 선언하기 위해 어노테이션을 붙임
@Entity
public class Member {
    // 대표값을 id로  선언하고 @ID 어노테이션을 붙임
    // 그 다음 @GeneratedValue 어노테이션도 붙여서 대표값을 자동으로 생성하게 함
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String email;
    @Column
    private String password;

    public Member(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
