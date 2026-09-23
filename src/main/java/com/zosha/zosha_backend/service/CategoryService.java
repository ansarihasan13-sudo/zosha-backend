package com.zosha.zosha_backend.service;

import com.zosha.zosha_backend.entity.Category;
import com.zosha.zosha_backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // CREATE
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // GET ALL
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // GET BY ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // UPDATE
    public Category updateCategory(Long id, Category category) {

        Category existingCategory =
                categoryRepository.findById(id).orElse(null);

        if (existingCategory != null) {

            existingCategory.setName(category.getName());
            existingCategory.setDescription(category.getDescription());
            existingCategory.setActive(category.isActive());

            return categoryRepository.save(existingCategory);
        }

        return null;
    }

    // DELETE
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}