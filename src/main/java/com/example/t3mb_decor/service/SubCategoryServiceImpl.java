package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.SubCategory;
import com.example.t3mb_decor.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubCategoryServiceImpl implements SubCategoryService{

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Override
    public List<SubCategory> getAllSubCategory() {
        return subCategoryRepository.findAll();
    }
}
