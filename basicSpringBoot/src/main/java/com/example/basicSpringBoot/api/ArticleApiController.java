package com.example.basicSpringBoot.api;

import com.example.basicSpringBoot.dto.ArticleForm;
import com.example.basicSpringBoot.entity.Article;
import com.example.basicSpringBoot.repository.ArticleRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
    //POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }
    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null || id != article.getId()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article target = articleRepository.findById(id).orElse(null);
        if(target == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
