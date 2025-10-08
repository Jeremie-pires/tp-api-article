package com.example.tpapiarticle.controller;

import com.example.tpapiarticle.entity.Article;
import com.example.tpapiarticle.service.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.example.tpapiarticle.service.ArticleService;

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
}
