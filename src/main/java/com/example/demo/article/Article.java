package com.example.demo.article;

import lombok.Data;

@Data
public class Article {
    private int id;
    private String author;
    private String title;
    private String content;

    public Article(int id, String author, String title, String content){
        this.id = id; this.author = author; this.title = title; this.content = content;
    }
    public Article(){}
}
