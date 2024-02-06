package com.example.basicSpringBoot.service;

import com.example.basicSpringBoot.dto.ArticleForm;
import com.example.basicSpringBoot.entity.Article;
import com.example.basicSpringBoot.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public ResponseEntity<List<Article>> index(){
        return ResponseEntity.ok(articleRepository.findAll());
    }

    public ResponseEntity<Article> show(Long id){
        return ResponseEntity.ok(articleRepository.findById(id).orElse(null));
    }

    public ResponseEntity<Article> create(ArticleForm dto){
        return ResponseEntity.ok(articleRepository.save(dto.toEntity()));
    }

}
