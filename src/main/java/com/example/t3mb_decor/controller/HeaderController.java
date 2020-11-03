package com.example.t3mb_decor.controller;


import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HeaderController {
    @Autowired
    CategoryService categoryService;

    //      List of categories
    @ModelAttribute("listCategories")
    public List<Category> getList(){
        List<Category> listCate =  categoryService.getAllCategories();
        return listCate;
    }

    @GetMapping("/contact")
    public String showContact(){ return "/content/contact";}


    @GetMapping("/about")
    public String showAbout(){return "/content/about";}

    @GetMapping("/cart")
    public String showCart(){return "/content/cart";}

    @GetMapping("/profile")
    public String showProfile(){return "/content/profile";}
    @GetMapping("/history")
    public String showHistory(){return "/content/pus-history";}
}
