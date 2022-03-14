package com.example.demo.article;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final Map<Integer, Article> allArticles;
    private final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    public ArticleService(){
        allArticles = new HashMap<Integer, Article>();
        allArticles.put(1, new Article(1, "김원빈", "첫번째 게시글!", "Hello world!"));
        allArticles.put(2, new Article(2, "박예람", "안녕", "안녕하세용"));
    }

    public Article getById(int id){
        logger.info("service get by id : " + id);
        return allArticles.get(id);
    }

    public List<Article> getAll(){
        Set<Integer> key = allArticles.keySet();
        List<Article> list = (List<Article>) new LinkedList<Article>();
        key.forEach((Integer k) -> {
            list.add(allArticles.get(k));
        });

        return list;
    }

    public Article changeArticle(Article newArt){
        return allArticles.replace(newArt.getId(), newArt);
    }

    public Article putNewArticle(Article newArt){
        return allArticles.put(newArt.getId(), newArt);
    }

    public Article deleteArticleById(int id){
        return allArticles.remove(id);
    }
}
