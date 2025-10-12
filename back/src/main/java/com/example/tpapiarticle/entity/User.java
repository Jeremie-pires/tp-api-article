package com.example.tpapiarticle.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "Users")
public class User {

    @MongoId(FieldType.OBJECT_ID)
    public String _id;
    public String email;
    public String password;
    public String pseudo;
    public String cityCode;
    public String city;
    public String phone;
}