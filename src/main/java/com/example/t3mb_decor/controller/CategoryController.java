package com.example.t3mb_decor.controller;


import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.Customer;
import com.example.t3mb_decor.model.SubCategory;
import com.example.t3mb_decor.repository.CategoryRepository;
import com.example.t3mb_decor.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository cate;
    @Autowired
    private SubCategoryRepository subCategoryRepository;


    @GetMapping("/category")
    public List<Category> viewCategory(){
        System.out.println("hello");
        return cate.findAll();
    }
    @PostMapping("/category")
    public Category saveEmployee(Category category){

        category = cate.findById((long) 4).get();
        SubCategory subCategory = new SubCategory();
        subCategory.setName("Medium Glass");
        category.getSub().add(subCategory);
        cate.save(category);
        return category;
    }
}
