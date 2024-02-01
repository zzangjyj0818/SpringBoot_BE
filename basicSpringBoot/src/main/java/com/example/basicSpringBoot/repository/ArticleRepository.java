package com.example.basicSpringBoot.repository;

import com.example.basicSpringBoot.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
