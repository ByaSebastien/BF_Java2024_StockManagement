package be.bstorm.bf_java2024_stockmanagement.bll.services;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

/**
 * <p>Implementation of the {@link ArticleService} interface that provides CRUD operations for {@link Article} entities.</p>
 * <p>This service includes business logic related to articles, such as image storage, validation checks, and retrieval of active articles only.</p>
 */
public interface ArticleService {

    /**
     * <p>Retrieves a list of all active articles from the repository.</p>
     *
     * @return a {@link List} of active {@link Article} objects.
     */
    List<Article> findAll();
    /**
     * <p>Retrieves an article by its unique identifier (UUID).</p>
     * <p>If the article is marked as deleted, a {@link RuntimeException} is thrown.</p>
     *
     * @param id the UUID of the article to retrieve.
     * @return the {@link Article} if found and active.
     * @throws RuntimeException if the article is marked as deleted.
     */
    Article findById(UUID id);
    /**
     * <p>Saves a new article entity with a unique identifier. If an image is provided, it is stored on the server.</p>
     * <p>Validates that the article designation is unique before saving.</p>
     *
     * @param article the {@link Article} entity to save.
     * @param image a {@link MultipartFile} representing the article's image (optional).
     * @return the saved {@link Article}.
     * @throws IllegalArgumentException if an article with the same designation already exists.
     */
    Article save(Article article, MultipartFile image);
    /**
     * <p>Updates an existing article entity with new values. If an image is provided, it replaces the existing one.</p>
     * <p>Validates that no other article with the same designation exists before updating.</p>
     *
     * @param article the {@link Article} entity with updated values.
     * @param image a {@link MultipartFile} representing the new article's image (optional).
     * @throws IllegalArgumentException if another article with the same designation already exists.
     */
    void update(Article article, MultipartFile image);
    /**
     * <p>Marks an article as deleted by its ID.</p>
     *
     * @param id the UUID of the article to delete.
     * @throws IllegalArgumentException if the article does not exist.
     */
    void delete(UUID id);
}
