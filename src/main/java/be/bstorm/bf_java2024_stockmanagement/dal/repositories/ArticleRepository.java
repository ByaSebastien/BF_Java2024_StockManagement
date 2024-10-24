package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ArticleRepository extends JpaRepository<Article, UUID> {

    @Query("select count(a) > 0 from Article a where a.designation ilike :designation")
    boolean existsByDesignation(String designation);

    @Query("select count(a) > 0 from Article a where a.id != :id and a.designation = :designation")
    boolean existsInOtherArticleByDesignation(UUID id, String designation);

    @Query("select a from Article a where a.isDeleted = false")
    List<Article> findAllActive();

//    @Query("select a from Article a where a.id = :id and a.isDeleted = false")
//    Optional<Article> findActiveById(UUID id);
}
