package com.example.pratice.controller;

import com.example.pratice.dto.ArticleForm;
import com.example.pratice.entity.Article;
import com.example.pratice.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());
        Article article = form.toEntity();
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "";
    }
}
