package com.lorenzolobrutto.blog.controller;

import com.lorenzolobrutto.blog.model.Article;
import com.lorenzolobrutto.blog.repository.ArticleRepository;
import com.lorenzolobrutto.blog.service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;  

@RestController
@RequestMapping("/api/articles")
@CrossOrigin // se il frontend è separato, serve ad abilitare le richieste CORS Cross-Origin Resource Sharing) da un dominio differente da quello in cui gira il server.
public class ArticleController {

  @Autowired
    private ArticleService articleService;

    public ArticleController(ArticleRepository articleRepository) {
    }

    @GetMapping()
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @PostMapping()
    public Article createArticle(@RequestBody Article article) {
        return articleService.saveArticle(article);
    }
}
