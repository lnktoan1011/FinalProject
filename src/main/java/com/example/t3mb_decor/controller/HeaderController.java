package com.example.t3mb_decor.controller;


import com.example.t3mb_decor.model.Cart;
import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.Orders;
import com.example.t3mb_decor.model.User;
import com.example.t3mb_decor.service.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class HeaderController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductFileService productFileService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

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

    @GetMapping("/contact")
    public String showContact(){ return "/content/contact";}


    @GetMapping("/about")
    public String showAbout(){return "/content/about";}

    @GetMapping("/history")
    public String showHistory(Model model,  Authentication authentication){
        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        List<Orders> ordersList = orderService.getHistory(user.getId());
        model.addAttribute("history", ordersList);
        return "/content/pus-history";
    }
}
