package com.example.t3mb_decor.controller;

import com.example.t3mb_decor.model.*;
import com.example.t3mb_decor.service.CategoryService;
import com.example.t3mb_decor.service.ProductFileService;
import com.example.t3mb_decor.service.ProductService;
import com.example.t3mb_decor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ProController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductFileService productFileService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;

    //      Total Product in Cart
    @ModelAttribute("TotalProduct")
    public int totalProduct(Authentication authentication){
        int total = 0;
        if (authentication != null){
            String emailName = authentication.getName();
            User user = userService.getUserFindByEmail(emailName);
            List<Cart> listCart = user.getListCart();
            for(int i = 0; i < listCart.size(); i++){
                total = total + listCart.get(i).getQuantity();
            }

        }
        return total;
    }

    //      List of categories
    @ModelAttribute("listCategories")
    public List<Category> getList(){
        List<Category> listCate =  categoryService.getAllCategories();
        return listCate;
    }


    @GetMapping("product/{id}")
    public String viewCollection(@PathVariable("id") long id, Model model){
        Product product = productService.getProduct(id);
        List<ProductFiles> productFilesList = productFileService.getProductFilebyProductID(id);
        model.addAttribute("product", product);
        model.addAttribute("listImage", productFilesList);
        Cart cart = new Cart();
        cart.setProduct_cart(product);
        cart.setQuantity(1);
        model.addAttribute("cart", cart);
        return "content/product";
    }
}
