package com.example.basicSpringBoot.repository;

import com.example.basicSpringBoot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "SELECT * FROM  comment WHERE article_id = :articleId", nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    @Query(value = "SELECT * FROM  comment WHERE nickname = :nickname", nativeQuery = true)
    List<Comment> findByNickname(String nickname);
}
