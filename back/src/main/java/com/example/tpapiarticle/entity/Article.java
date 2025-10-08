package com.example.tpapiarticle.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "Articles")
public class Article {

    @MongoId(FieldType.OBJECT_ID)
    public String _id;
    public String title;
    public String desc;
    public String author;
    public String imgPath;
}
