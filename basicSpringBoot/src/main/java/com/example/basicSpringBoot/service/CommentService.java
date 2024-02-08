package com.example.basicSpringBoot.service;

import com.example.basicSpringBoot.dto.CommentDto;
import com.example.basicSpringBoot.entity.Comment;
import com.example.basicSpringBoot.repository.ArticleRepository;
import com.example.basicSpringBoot.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }
}
