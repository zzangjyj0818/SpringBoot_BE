package com.example.ndStudyProject.repository;

import com.example.ndStudyProject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    ArrayList<Article> findAll();
}
