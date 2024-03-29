package com.example.basicSpringBoot.service;

import com.example.basicSpringBoot.dto.ArticleForm;
import com.example.basicSpringBoot.entity.Article;
import com.example.basicSpringBoot.repository.ArticleRepository;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Article create(ArticleForm dto){
        return dto.toEntity().getId() != null ? dto.toEntity() : null;
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null || id != article.getId()){
            return null;
        }
        target.patch(article);
        return articleRepository.save(target);
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null) return null;

        articleRepository.delete(target);
        return target;
    }
}
