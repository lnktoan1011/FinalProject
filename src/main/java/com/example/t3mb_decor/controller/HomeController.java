package com.example.t3mb_decor.controller;

import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/home")
@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;

    //      List of categories
    @ModelAttribute("listCategories")
    public List<Category> getList(){
        List<Category> listCate =  categoryService.getAllCategories();
        return listCate;
    }

    @GetMapping
    public String home(){
        return "/content/home";
    }
//    @GetMapping
//    public String home(){
//        return "/fragments/main/header";
//    }
}
