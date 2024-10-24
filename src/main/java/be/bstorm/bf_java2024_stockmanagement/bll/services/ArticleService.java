package be.bstorm.bf_java2024_stockmanagement.bll.services;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ArticleService {

    List<Article> findAll();
    Article findById(UUID id);
    Article save(Article article, MultipartFile image);
    void update(Article article, MultipartFile image);
    void delete(UUID id);
}
