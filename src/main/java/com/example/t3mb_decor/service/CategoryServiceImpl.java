package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.SubCategory;
import com.example.t3mb_decor.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public void deleteAllOfCategory() {
        this.categoryRepository.deleteAll();
    }

    @Override
    public void updateCategory(Category category) {
        long id = category.getId();
        Category categoryUpdate = this.getCategory(id);
        Date createDate = categoryUpdate.getCreatedAt();
        List<SubCategory> subCategoryList = categoryUpdate.getSub();

        categoryUpdate.setName(category.getName());
        category.setCreatedAt(createDate);
        category.setSub(subCategoryList);
        this.categoryRepository.save(categoryUpdate);
    }
}
