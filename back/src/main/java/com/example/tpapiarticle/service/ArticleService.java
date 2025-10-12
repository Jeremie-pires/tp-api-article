package com.example.tpapiarticle.service;

import com.example.tpapiarticle.entity.Article;
import com.example.tpapiarticle.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    boolean isValidUrl(String url) {
        try {
            new java.net.URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public ResponseDTO<List<Article>> getArticleList() {
        List<Article> articles = articleRepository.findAll();
        ResponseDTO<List<Article>> articleResponseDTO = new ResponseDTO<>();
        articleResponseDTO.code = 200;
        articleResponseDTO.message = "La liste des articles a été récupérée avec succès";
        articleResponseDTO.data = articles;

        return articleResponseDTO;
    }

    public ResponseDTO<Article> getArticleById(String _id) {
        Article article = articleRepository.findArticleBy_id(_id);
        ResponseDTO<Article> articleResponseDTO = new ResponseDTO<>();
        if (article != null) {
            articleResponseDTO.code = 200;
            articleResponseDTO.message = "Article récupéré avec succès";
            articleResponseDTO.data = article;
        } else {
            articleResponseDTO.code = 702;
            articleResponseDTO.message = "Impossible de récupérer un article avec l’ID {_id}";
            articleResponseDTO.data = null;
        }

        return articleResponseDTO;
    }

    public ResponseDTO<Article> deleteArticleById(String _id) {
        Article article = articleRepository.findArticleBy_id(_id);
        ResponseDTO<Article> articleResponseDTO = new ResponseDTO<>();
        if (article != null) {
            articleResponseDTO.code = 200;
            articleResponseDTO.message = "L'article est supprimé";
            articleResponseDTO.data = article;
            articleRepository.delete(article);
        } else {
            articleResponseDTO.code = 702;
            articleResponseDTO.message = "Impossible de supprimer un article dont l’ID n’existe pas";
            articleResponseDTO.data = null;
        }

        return articleResponseDTO;
    }

    public ResponseDTO<Article> modifyArticleById(String _id, Article article) {
        Article existingArticle = articleRepository.findArticleBy_id(_id);
        Article existingArticleWithSameTitle = articleRepository.findArticleByTitle(article.title);
        ResponseDTO<Article> articleResponseDTO = new ResponseDTO<>();

        if (ArticleCheckFields.checkFields(article) || !isValidUrl(article.imgPath)) {
            Article articleMissingFields = new Article();
            if (article.title == null || article.title.trim().isEmpty()) {
                articleMissingFields.title = "Le titre est manquant";
            }
            if (article.author == null || article.author.trim().isEmpty()) {
                articleMissingFields.author = "L'auteur est manquant";
            }
            if (article.desc == null || article.desc.trim().isEmpty()) {
                articleMissingFields.desc = "La description est manquante";
            }
            if (article.imgPath == null || article.imgPath.trim().isEmpty()) {
                articleMissingFields.imgPath = "Le chemin de l'image est manquant";
            }
            if (!isValidUrl(article.imgPath)) {
                articleMissingFields.imgPath = "Le chemin de l'image n'est pas une url valide";
            }
            articleResponseDTO.code = 710;
            articleResponseDTO.message = "Contrôle de surface non valide";
            articleResponseDTO.data = articleMissingFields;

        } else if (existingArticleWithSameTitle != null && !existingArticleWithSameTitle._id.equals(_id)) {
            articleResponseDTO.code = 701;
            articleResponseDTO.message = "Impossible de modifier un article si un autre possède ce titre";
            articleResponseDTO.data = null;

        } else if (existingArticle != null) {
            existingArticle.title = article.title;
            existingArticle.author = article.author;
            existingArticle.desc = article.desc;
            existingArticle.imgPath = article.imgPath;

            articleResponseDTO.code = 200;
            articleResponseDTO.message = "Articlé modifié avec succès";
            articleResponseDTO.data = existingArticle;
            articleRepository.save(existingArticle);
        }

        return articleResponseDTO;
    }

    public ResponseDTO<Article> addArticle(Article article) {
        Article existingArticleWithSameTitle = articleRepository.findArticleByTitle(article.title);
        ResponseDTO<Article> articleResponseDTO = new ResponseDTO<>();

        if (ArticleCheckFields.checkFields(article) || !isValidUrl(article.imgPath)) {
            Article articleMissingFields = new Article();
            if (article.title == null || article.title.trim().isEmpty()) {
                articleMissingFields.title = "Le titre est manquant";
            }
            if (article.author == null || article.author.trim().isEmpty()) {
                articleMissingFields.author = "L'auteur est manquant";
            }
            if (article.desc == null || article.desc.trim().isEmpty()) {
                articleMissingFields.desc = "La description est manquante";
            }
            if (article.imgPath == null || article.imgPath.trim().isEmpty()) {
                articleMissingFields.imgPath = "Le chemin de l'image est manquant";
            }
            if (!isValidUrl(article.imgPath)) {
                articleMissingFields.imgPath = "Le chemin de l'image n'est pas une url valide";
            }
            articleResponseDTO.code = 710;
            articleResponseDTO.message = "Contrôle de surface non valide";
            articleResponseDTO.data = articleMissingFields;

        } else if (existingArticleWithSameTitle != null) {
            articleResponseDTO.code = 701;
            articleResponseDTO.message = "Impossible de modifier un article si un autre possède ce titre";
            articleResponseDTO.data = null;

        } else {
            Article newArticle = new Article();
            newArticle.title = article.title;
            newArticle.author = article.author;
            newArticle.desc = article.desc;
            newArticle.imgPath = article.imgPath;
            
            articleResponseDTO.code = 200;
            articleResponseDTO.message = "Article ajouté avec succès";
            articleResponseDTO.data = articleRepository.save(newArticle);
        }

        return articleResponseDTO;
    }
}