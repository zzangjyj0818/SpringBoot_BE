package com.example.basicSpringBoot.service;

import com.example.basicSpringBoot.repository.ArticleRepository;
import com.example.basicSpringBoot.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
}
