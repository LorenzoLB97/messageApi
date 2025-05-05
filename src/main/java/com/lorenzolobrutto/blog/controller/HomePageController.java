package com.lorenzolobrutto.blog.controller;

import com.lorenzolobrutto.blog.model.Article;
import com.lorenzolobrutto.blog.repository.ArticleRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class HomePageController {

    private final ArticleRepository articleRepository;

    public HomePageController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping()
    public String home() {
        return "index.html"; // solo il nome se stai in static, oppure "home" se in templates
    }

     @GetMapping("/getAllArticles")
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
