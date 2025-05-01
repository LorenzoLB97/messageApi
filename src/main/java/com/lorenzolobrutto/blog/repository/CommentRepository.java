package com.lorenzolobrutto.blog.repository;

import com.lorenzolobrutto.blog.model.Comment;
import com.lorenzolobrutto.blog.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByArticle(Article article);
}
