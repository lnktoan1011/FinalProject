package com.example.t3mb_decor.controller;


import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.repository.CategoryReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryReponsitory cate;

    @GetMapping("/category")
    public List<Category> viewCategory(){
        return cate.findAll();
    }
}
