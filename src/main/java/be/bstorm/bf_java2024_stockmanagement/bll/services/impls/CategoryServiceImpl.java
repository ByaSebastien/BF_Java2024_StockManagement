package be.bstorm.bf_java2024_stockmanagement.bll.services.impls;

import be.bstorm.bf_java2024_stockmanagement.bll.services.CategoryService;
import be.bstorm.bf_java2024_stockmanagement.dal.repositories.CategoryRepository;
import be.bstorm.bf_java2024_stockmanagement.dl.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(UUID id) {
        return categoryRepository.findById(id).orElseThrow();
    }
}
