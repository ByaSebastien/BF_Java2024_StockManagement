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

/**
 * <p>Implementation of the {@link ArticleService} interface that provides CRUD operations for {@link Article} entities.</p>
 * <p>This service includes business logic related to articles, such as image storage, validation checks, and retrieval of active articles only.</p>
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    /**
     * <p>Retrieves a list of all active articles from the repository.</p>
     *
     * @return a {@link List} of active {@link Article} objects.
     */
    @Override
    public List<Article> findAll() {
        return articleRepository.findAllActive();
    }

    /**
     * <p>Retrieves an article by its unique identifier (UUID).</p>
     * <p>If the article is marked as deleted, a {@link RuntimeException} is thrown.</p>
     *
     * @param id the UUID of the article to retrieve.
     * @return the {@link Article} if found and active.
     * @throws RuntimeException if the article is marked as deleted.
     */
    @Override
    public Article findById(UUID id) {
        Article article = articleRepository.findById(id).orElseThrow();
        if(article.isDeleted()){
            throw new RuntimeException("Article deleted");
        }
        return article;
    }

    /**
     * <p>Saves a new article entity with a unique identifier. If an image is provided, it is stored on the server.</p>
     * <p>Validates that the article designation is unique before saving.</p>
     *
     * @param article the {@link Article} entity to save.
     * @param image a {@link MultipartFile} representing the article's image (optional).
     * @return the saved {@link Article}.
     * @throws IllegalArgumentException if an article with the same designation already exists.
     */
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

    /**
     * <p>Updates an existing article entity with new values. If an image is provided, it replaces the existing one.</p>
     * <p>Validates that no other article with the same designation exists before updating.</p>
     *
     * @param article the {@link Article} entity with updated values.
     * @param image a {@link MultipartFile} representing the new article's image (optional).
     * @throws IllegalArgumentException if another article with the same designation already exists.
     */
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

    /**
     * <p>Marks an article as deleted by its ID.</p>
     *
     * @param id the UUID of the article to delete.
     * @throws IllegalArgumentException if the article does not exist.
     */
    @Transactional
    @Override
    public void delete(UUID id) {
        if(!articleRepository.existsById(id)) {
            throw new IllegalArgumentException("Article does not exist");
        }
        articleRepository.deleteById(id);
    }

    /**
     * <p>Stores the provided image on the server and returns the image name for future retrieval.</p>
     *
     * @param image a {@link MultipartFile} representing the image to be saved.
     * @return the saved image name as a {@link String}.
     * @throws RuntimeException if an error occurs during the file save operation.
     */
    private String saveImage(MultipartFile image) {

        String imageName = UUID.randomUUID() + "_" + image.getOriginalFilename();
        Path imagePath = Path.of(System.getProperty("user.dir"), "images", imageName);
        try {
            Files.write(imagePath, image.getBytes());
            return imageName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
