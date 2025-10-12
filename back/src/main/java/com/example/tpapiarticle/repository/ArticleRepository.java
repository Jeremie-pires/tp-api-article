package com.example.tpapiarticle.repository;

import com.example.tpapiarticle.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
    Article findArticleByTitle(String title);
    Article findArticleBy_id(String _id);
}
