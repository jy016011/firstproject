package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}
