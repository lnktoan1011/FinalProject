package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void saveCategory(Category category) {
        this.categoryRepository.save(category);
    }

    @Override
    public Category getCategory(long id) {
        return this.categoryRepository.findById(id).get();
    }

    @Override
    public void deleteCategory(long id) {
        Category categoryDelete = this.getCategory(id);
        this.categoryRepository.delete(categoryDelete);
    }

    @Override
    public void updateCategory(long id, Category category) {
        Category categoryUpdate = this.getCategory(id);
        categoryUpdate.setName(category.getName());
        this.categoryRepository.save(categoryUpdate);
    }
}
