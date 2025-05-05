package com.lorenzolobrutto.blog.repository;


import com.lorenzolobrutto.blog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
