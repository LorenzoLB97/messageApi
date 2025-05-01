package com.lorenzolobrutto.blog.controller;

import com.lorenzolobrutto.blog.model.Article;
import com.lorenzolobrutto.blog.model.Comment;
import com.lorenzolobrutto.blog.repository.ArticleRepository;
import com.lorenzolobrutto.blog.repository.CommentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin
public class CommentController {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    public CommentController(CommentRepository commentRepository, ArticleRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }

    @GetMapping("/article/{articleId}")
    public List<Comment> getCommentsByArticle(@PathVariable Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        return commentRepository.findByArticle(article);
    }

    @PostMapping("/article/{articleId}")
    public Comment addCommentToArticle(@PathVariable Long articleId, @RequestBody Comment comment) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        comment.setArticle(article);
        return commentRepository.save(comment);
    }
}
