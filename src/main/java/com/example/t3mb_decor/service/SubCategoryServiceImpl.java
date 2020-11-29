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
        SubCategory subCategory = this.getSubCategory(id);
        subCategory.setCategory(null);
        subCategoryRepository.save(subCategory);
        SubCategory subCategoryDelete = this.getSubCategory(id);
        subCategoryRepository.delete(subCategoryDelete);
    }

    @Override
    public void updateSubCategory(SubCategory subCategory) {
        long id = subCategory.getId();
        SubCategory subCategoryUpdate = this.getSubCategory(id);
        Date createDate = subCategoryUpdate.getCreatedAt();

        subCategoryUpdate.setCreatedAt(createDate);
        subCategoryUpdate.setName(subCategory.getName());
        this.subCategoryRepository.save(subCategoryUpdate);
    }

    @Override
    public List<Long> getSubIdByCate(long id) {
        return subCategoryRepository.findByIdFromCateId(id);
    }
}
