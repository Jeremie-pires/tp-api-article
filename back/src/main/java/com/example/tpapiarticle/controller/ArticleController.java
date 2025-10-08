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
        return articleService.showArticleList();
    }


    @GetMapping("/articles/{_id}")
    public ResponseDTO displayArticleById(@PathVariable("_id") String _id) {
        return articleService.showArticleById(_id);
    }

    @DeleteMapping("/articles/{_id}")
    public ResponseDTO deleteArticleById(@PathVariable("_id") String _id) {
        return articleService.deleteArticleById(_id);
    }

    @PostMapping("/articles/save/{_id}")
    public Article modifyArticleById(@PathVariable("_id") String _id, @RequestBody Article article) {
        return articleService.modifyArticleById(_id, article);
    }
}