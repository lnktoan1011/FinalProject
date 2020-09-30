package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.SubCategory;

import java.util.List;

public interface SubCategoryService {
    List<SubCategory> getAllSubCategory();
    void saveSubCategory(SubCategory subCategory);
    SubCategory getSubCategory(long id);
    void deleteSubCategory(long id);
    void updateSubCategory(long id,SubCategory subCategory);
}
