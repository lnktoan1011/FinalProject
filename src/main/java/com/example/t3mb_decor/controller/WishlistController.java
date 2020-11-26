package com.example.t3mb_decor.controller;

import com.example.t3mb_decor.model.*;
import com.example.t3mb_decor.service.CategoryService;
import com.example.t3mb_decor.service.ProductService;
import com.example.t3mb_decor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class WishlistController {
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;
    //      List of categories
    @ModelAttribute("listCategories")
    public List<Category> getList(){
        List<Category> listCate =  categoryService.getAllCategories();
        return listCate;
    }
    @GetMapping("/wishlist")
    public String Wishlist(Authentication authentication, Model model){
        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        model.addAttribute("wishList", user.getProduct_wishlist());
        return "content/wishlist";
    }
    @GetMapping("/wishlist/add/{id}")
    public String addToWishlist(@PathVariable("id") long id, Authentication authentication){
        Product product = productService.getProduct(id);
        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        user.getProduct_wishlist().add(product);
        userService.saveProfile(user);
        return "redirect:/collections";
    }
    @GetMapping("/wishlist/delete/{id}")
    public String deleteWishlist(@PathVariable("id") long id, Authentication authentication){
        Product product = productService.getProduct(id);
        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        user.getProduct_wishlist().remove(product);
        userService.saveProfile(user);
        return "redirect:/collections";
    }
}
