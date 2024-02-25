package com.example.pratice.dto;

import com.example.pratice.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

// DTO : Form Object
// DTO 클래스 안에 엔티티로 변환하는 메서드가 존재함 => toEntity
@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(id, title, content);
    }
}
