package com.example.tpapiarticle.service;

import com.example.tpapiarticle.entity.Article;
import com.example.tpapiarticle.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public ResponseDTO<List<Article>> showArticleList() {
        List<Article> articles = articleRepository.findAll();
        ResponseDTO<List<Article>> articleResponseDTO = new ResponseDTO<>();
        articleResponseDTO.code = 200;
        articleResponseDTO.message = "La liste des articles est chargée";
        articleResponseDTO.data = articles;

        return articleResponseDTO;
    }

    public ResponseDTO<Article> showArticleById(String _id) {
        Article article = articleRepository.findArticleBy_id(_id);
        ResponseDTO<Article> articleResponseDTO = new ResponseDTO<>();
        articleResponseDTO.code = 200;
        articleResponseDTO.message = "Chargement de l'article";
        articleResponseDTO.data = article;

        return articleResponseDTO;
    }
}
