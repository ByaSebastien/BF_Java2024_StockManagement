package be.bstorm.bf_java2024_stockmanagement.dal.repositories;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
