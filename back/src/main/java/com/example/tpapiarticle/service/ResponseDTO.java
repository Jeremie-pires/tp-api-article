package com.example.tpapiarticle.service;

import com.example.tpapiarticle.entity.Article;

import java.util.List;

public class ResponseDTO<T> {
    public int code;
    public String message;
    public T data;

}
