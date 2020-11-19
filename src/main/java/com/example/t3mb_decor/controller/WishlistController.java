package com.example.t3mb_decor.controller;

import com.example.t3mb_decor.model.Cart;
import com.example.t3mb_decor.model.Orders;
import com.example.t3mb_decor.model.Product;
import com.example.t3mb_decor.model.User;
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
    @GetMapping("/wishlist/{id}")
    public String showHistory(@PathVariable("id") long id, Authentication authentication){
        Product product = productService.getProduct(id);
        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        user.getProduct_wishlist().add(product);
        userService.saveProfile(user);
        return "redirect:/collections";
    }
}
