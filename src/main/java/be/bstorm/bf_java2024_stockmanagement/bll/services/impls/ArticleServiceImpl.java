package be.bstorm.bf_java2024_stockmanagement.bll.services.impls;

import be.bstorm.bf_java2024_stockmanagement.bll.services.ArticleService;
import be.bstorm.bf_java2024_stockmanagement.dal.repositories.ArticleRepository;
import be.bstorm.bf_java2024_stockmanagement.dal.repositories.StockRepository;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final StockRepository stockRepository;

    @Override
    public Article save(Article article, MultipartFile image) {
        if(articleRepository.existsByDesignation(article.getDesignation())){
            throw new IllegalArgumentException("Designation already exists");
        }
        article.setId(UUID.randomUUID());

        if(!image.isEmpty()) {
            String imageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path imagePath = Path.of(System.getProperty("user.dir"), "src", "main", "resources", "static", "images", imageName);
            try {
                Files.write(imagePath,image.getBytes());
                article.setPicture(imageName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Article newArticle = articleRepository.save(article);
        stockRepository.save(new Stock(
                UUID.randomUUID(),
                0,
                newArticle
        ));
        return newArticle;
    }
}
