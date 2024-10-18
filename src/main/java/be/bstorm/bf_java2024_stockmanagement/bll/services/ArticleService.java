package be.bstorm.bf_java2024_stockmanagement.bll.services;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import org.springframework.web.multipart.MultipartFile;

public interface ArticleService {

    Article save(Article article, MultipartFile image);
}
