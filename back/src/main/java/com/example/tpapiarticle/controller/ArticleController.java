package com.example.tpapiarticle.controller;

import com.example.tpapiarticle.entity.Article;
import com.example.tpapiarticle.service.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.tpapiarticle.service.ArticleService;
import com.example.tpapiarticle.repository.ArticleRepository;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/articles")
    public ResponseDTO displayArticleList() {
        return articleService.getArticleList();
    }

    @GetMapping("/articles/{_id}")
    public ResponseDTO displayArticleById(@PathVariable("_id") String _id) {
        return articleService.getArticleById(_id);
    }

    @DeleteMapping("/articles/{_id}")
    public ResponseDTO deleteArticleById(@PathVariable("_id") String _id) {
        return articleService.deleteArticleById(_id);
    }

    @PutMapping("/articles/{_id}")
    public ResponseDTO modifyArticleById(@PathVariable("_id") String _id, @RequestBody Article article) {
        return articleService.modifyArticleById(_id, article);
    }

    @PostMapping("/articles")
    public ResponseDTO addArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }
}