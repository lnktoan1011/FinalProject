package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void saveCategory(Category category);
    Category getCategory(long id);
    void deleteCategory(long id);
    void deleteAllOfCategory();
    void updateCategory(Category category);
}
