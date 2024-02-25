package com.example.ndStudyProject.api;

import com.example.ndStudyProject.entity.Article;
import com.example.ndStudyProject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/api/articles")
    public ResponseEntity<ArrayList<Article>> index(){
        return ResponseEntity.status(HttpStatus.OK).body(articleRepository.findAll());
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<Article> show(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(articleRepository.findById(id).orElse(null));
    }
}
