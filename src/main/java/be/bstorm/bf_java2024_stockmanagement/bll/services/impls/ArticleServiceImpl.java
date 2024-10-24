package be.bstorm.bf_java2024_stockmanagement.bll.services.impls;

import be.bstorm.bf_java2024_stockmanagement.bll.services.ArticleService;
import be.bstorm.bf_java2024_stockmanagement.dal.repositories.ArticleRepository;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import jakarta.transaction.Transactional;
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
        return articleRepository.findAllActive();
    }

    @Override
    public Article findById(UUID id) {
        Article article = articleRepository.findById(id).orElseThrow();
        if(article.isDeleted()){
            throw new RuntimeException("Article deleted");
        }
        return article;
    }

    @Override
    public Article save(Article article, MultipartFile image) {
        if(articleRepository.existsByDesignation(article.getDesignation())){
            throw new IllegalArgumentException("Designation already exists");
        }
        article.setId(UUID.randomUUID());

        if(!image.isEmpty()) {
            article.setPicture(saveImage(image));
        }
        return articleRepository.save(article);
    }

    @Override
    public void update(Article article, MultipartFile image) {

        Article existingArticle = articleRepository.findById(article.getId()).orElseThrow();

        if(articleRepository.existsInOtherArticleByDesignation(article.getId(),article.getDesignation())){
            throw new IllegalArgumentException("Designation already exists");
        }

        existingArticle.setDesignation(article.getDesignation());
        existingArticle.setUnitPriceExcludingTax(article.getUnitPriceExcludingTax());
        existingArticle.setVat(article.getVat());
        existingArticle.setCategory(article.getCategory());

        if(!image.isEmpty()) {
            existingArticle.setPicture(saveImage(image));
        }

        articleRepository.save(existingArticle);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        if(!articleRepository.existsById(id)) {
            throw new IllegalArgumentException("Article does not exist");
        }
        articleRepository.deleteById(id);
    }

    private String saveImage(MultipartFile image) {

        String imageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path imagePath = Path.of(System.getProperty("user.dir"), "images", imageName);
        try {
            Files.write(imagePath,image.getBytes());
            return imageName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
