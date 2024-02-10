package com.example.ndStudyProject.controller;

import com.example.ndStudyProject.dto.ArticleDto;
import com.example.ndStudyProject.entity.Article;
import com.example.ndStudyProject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.slf4j.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;


@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticle(){
        return "/articles/new";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. Repository
        ArrayList<Article> articlesList = articleRepository.findAll();
        // 2. Model
        model.addAttribute("articleList", articlesList);
        // 3. return ViewPage
        return "/articles/index";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "/articles/show";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(Model model, @PathVariable Long id){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        if(articleEntity != null) {
            articleRepository.delete(articleEntity);
            rttr.addFlashAttribute("msg", "delete it");
        }
        return "redirect:/articles";
    }

    @PostMapping("/articles/update")
    public String update(ArticleDto dto){
        Article articleEntity = dto.toEntity();
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if(target != null){
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles";
    }

    @PostMapping("/articles/create")
    public String create(ArticleDto form) {
        // 1. DTO -> Entity
        Article articleEntity = form.toEntity();
        // 2. Entity -> Save Repository
        Article saved = articleRepository.save(articleEntity);
        log.info(saved.toString());
        // 3. return ViewPage
        return "redirect:/articles/" + saved.getId();
    }
}
