package com.br.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.backend.model.CategoryModel;
import com.br.backend.repositories.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryModel> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryModel> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public CategoryModel saveCategory(CategoryModel category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
