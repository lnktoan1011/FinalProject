package com.example.t3mb_decor.controller;


import com.example.t3mb_decor.model.*;
import com.example.t3mb_decor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @ModelAttribute("listCart")
    public List<Cart> getListCart(Authentication authentication){
        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        List<Cart> listCart = user.getListCart();
        return listCart;
    }


    @GetMapping
    public String showCart(Authentication authentication, Model model){

        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);

        List<Product> products = new ArrayList<>();
        int total = 0;
        List<Cart> cartList = user.getListCart();
        for(int i = 0; i < cartList.size(); i++){
            total = total + cartList.get(i).getQuantity() * cartList.get(i).getProduct_cart().getPrice();
            products.add(cartList.get(i).getProduct_cart());
        }
        Orders orders = new Orders();
        orders.setUser(user);
        orders.setOrder_product(products);
        orders.setSubTotal(total);
        orders.setTotal(total);
        model.addAttribute("order", orders);
        return "/content/cart";
    }

    @PostMapping
    public String AddToCart(@ModelAttribute("cart") Cart cart, Authentication authentication){

        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        cart.setUser_cart(user);
        Cart updateCart = cartService.getExistCart(cart.getUser_cart().getId(), cart.getProduct_cart().getId());
        if(updateCart != null)
        {
            int exQuantity = updateCart.getQuantity();
            updateCart.setQuantity( exQuantity + cart.getQuantity());
            cartService.saveCart(updateCart);
        }
        else{
            cartService.saveCart(cart);
        }

        return "redirect:/cart";
    }

}
