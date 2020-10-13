package com.example.t3mb_decor.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @ModelAttribute("first")
    public String getActive1(){
        return ".catalog_click";
    }
    @ModelAttribute("second")
    public String getActive2(){
        return ".product_click";
    }

    @GetMapping
    public String viewProduct(){
        return "content/admin/product";
    }

    @GetMapping("/add")
    public String addProduct(){
        return "content/admin/product_add";
    }
}
