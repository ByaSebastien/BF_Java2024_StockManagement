package be.bstorm.bf_java2024_stockmanagement.bll.services.impls;

import be.bstorm.bf_java2024_stockmanagement.bll.services.ArticleService;
import be.bstorm.bf_java2024_stockmanagement.dal.repositories.ArticleRepository;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article findById(UUID id) {
        return articleRepository.findById(id).orElseThrow();
    }

    @Override
    public Article save(Article article, MultipartFile image) {
        if(articleRepository.existsByDesignation(article.getDesignation())){
            throw new IllegalArgumentException("Designation already exists");
        }
        article.setId(UUID.randomUUID());

        if(!image.isEmpty()) {
            String imageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Path imagePath = Path.of(System.getProperty("user.dir"), "images", imageName);
            try {
                Files.write(imagePath,image.getBytes());
                article.setPicture(imageName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return articleRepository.save(article);
    }
}
