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

    public ResponseDTO<Article> deleteArticleById(String _id) {
        Article article = articleRepository.findArticleBy_id(_id);
        ResponseDTO<Article> articleResponseDTO = new ResponseDTO<>();
        if (article != null) {
            articleResponseDTO.code = 200;
            articleResponseDTO.message = "L'article est supprimé";
            articleRepository.delete(article);
        } else {
            articleResponseDTO.code = 404;
            articleResponseDTO.message = "L'article n'est pas trouvé";
        }
        return articleResponseDTO;
    }

    public Article modifyArticleById(String _id, Article article) {
        Article existingArticle = articleRepository.findArticleBy_id(_id);
        if (existingArticle != null) {
            existingArticle.title = article.title;
            existingArticle.author = article.author;
            existingArticle.desc = article.desc;
            existingArticle.imgPath = article.imgPath;
            return articleRepository.save(existingArticle);
        } else {
            return addArticle(article);
        }
    }

    public Article addArticle(Article article) {
            Article new_article = new Article();
            new_article.title = article.title;
            new_article.author = article.author;
            new_article.desc = article.desc;
            new_article.imgPath = article.imgPath;
            return articleRepository.save(new_article);
        }
}