package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.SubCategory;
import com.example.t3mb_decor.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class SubCategoryServiceImpl implements SubCategoryService{

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Override
    public List<SubCategory> getAllSubCategory() {
        return subCategoryRepository.findAll();
    }

    @Override
    public void saveSubCategory(SubCategory subCategory) {
        this.subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory getSubCategory(long id) {
        return this.subCategoryRepository.findById(id).get();
    }

    @Override
    public void deleteSubCategory(long id) {
        subCategoryRepository.delete(this.getSubCategory(id));
    }

    @Override
    public void updateSubCategory(long id, SubCategory subCategory) {
        SubCategory subCategoryUpdate = this.getSubCategory(id);
        Date createDate = subCategoryUpdate.getCreatedAt();

        subCategoryUpdate.setCreatedAt(createDate);
        subCategoryUpdate.setName(subCategory.getName());
        this.subCategoryRepository.save(subCategoryUpdate);
    }
}
