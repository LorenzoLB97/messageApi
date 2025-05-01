package com.lorenzolobrutto.blog.controller;

import com.lorenzolobrutto.blog.model.Article;
import com.lorenzolobrutto.blog.repository.ArticleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;  

@RestController
@RequestMapping("/api/articles")
@CrossOrigin // se il frontend è separato, serve ad abilitare le richieste CORS Cross-Origin Resource Sharing) da un dominio differente da quello in cui gira il server.
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        if (article.getCreatedAt() == null) {
            article.setCreatedAt(java.time.LocalDateTime.now());
        }
        return articleRepository.save(article);
    }
}
