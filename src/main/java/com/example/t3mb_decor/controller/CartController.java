package com.example.t3mb_decor.controller;


import com.example.t3mb_decor.model.*;
import com.example.t3mb_decor.service.CartService;
import com.example.t3mb_decor.service.CategoryService;
import com.example.t3mb_decor.service.ProductFileService;
import com.example.t3mb_decor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("cart")
@Controller
public class CartController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductFileService productFileService;
    @Autowired
    CategoryService categoryService;

    @Autowired
    CartService cartService;

    //      List of categories
    @ModelAttribute("listCategories")
    public List<Category> getList(){
        List<Category> listCate =  categoryService.getAllCategories();
        return listCate;
    }


    @GetMapping("/{id}")
    public String showCart(@PathVariable("id") long id, @ModelAttribute("cart") Cart cart, Model model){

        List<Product> productList = productService.getAllProduct();
        List<ProductFiles> productFilesList = productFileService.getAllProductFiles();
        model.addAttribute("listPro", productList);
        model.addAttribute("listImage", productFilesList);
        return "/content/cart";
    }

    @PostMapping
    @ResponseBody
    public String AddToCart(@ModelAttribute("cart") Cart cart, Model model, Authentication authentication){

        String email = authentication.getName();
        //cartService.saveCart(cart);
        model.addAttribute("cart", cart);
        return "/content/cart";
    }

}
