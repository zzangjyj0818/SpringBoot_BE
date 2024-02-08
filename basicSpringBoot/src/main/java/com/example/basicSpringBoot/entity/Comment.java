package com.example.basicSpringBoot.entity;

import com.example.basicSpringBoot.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    @Column
    private String nickname;
    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        if(dto.getId() != null) throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야합니다.");
        if(dto.getArticleId() != article.getId()) throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        if(this.id != dto.getId()) throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
        if(dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if(dto.getBody() != null)
            this.body = dto.getBody();
    }
}
