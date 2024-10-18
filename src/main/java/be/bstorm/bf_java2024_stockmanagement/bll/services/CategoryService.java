package be.bstorm.bf_java2024_stockmanagement.bll.services;

import be.bstorm.bf_java2024_stockmanagement.dl.entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> findAll();
    Category findById(UUID id);
}
