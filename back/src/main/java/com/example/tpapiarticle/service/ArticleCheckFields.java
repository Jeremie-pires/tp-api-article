package com.example.tpapiarticle.service;

import com.example.tpapiarticle.entity.Article;

public class ArticleCheckFields {
    public static boolean checkFields(Article article) {
        return article.title == null || article.author == null || article.desc == null || article.imgPath == null
                || article.title.trim().isEmpty() || article.author.trim().isEmpty() || article.desc.trim().isEmpty()
                || article.imgPath.trim().isEmpty();
    }
}