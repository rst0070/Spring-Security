package com.example.demo.article;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private ArticleService service;
    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    public ArticleController(ArticleService service){
        this.service = service;
    }

    @GetMapping("/{id}")
    public Article getById(@PathVariable("id") int id){
        logger.info("GET request: getById : "+id);
        return service.getById(id);
    }

    @GetMapping()
    public List<Article> getAll(){
        return service.getAll();
    }

    @PostMapping("/{id}")
    public Article changeArticleById(@PathVariable("id") int id, Article newArt){
        return service.changeArticle(newArt);
    }

    @PutMapping()
    public Article putNewArticle(Article newArt){
        return service.putNewArticle(newArt);
    }

    @DeleteMapping("/{id}")
    public Article deleteArticleById(@PathVariable("id") int id){
        return service.deleteArticleById(id);
    }
}
