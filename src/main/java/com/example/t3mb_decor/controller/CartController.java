package com.example.t3mb_decor.controller;


import com.example.t3mb_decor.VO.ListCartVO;
import com.example.t3mb_decor.model.*;
import com.example.t3mb_decor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ListCartVO getListCart(Authentication authentication){
        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        List<Cart> listCart = user.getListCart();
        return new ListCartVO(listCart);
    }

    @ModelAttribute("listImg")
    public List<ProductFiles> productFiles(Authentication authentication){

        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        List<Product> productList = new ArrayList<>();
        List<Cart> cartList = user.getListCart();
        for (int i =0; i< cartList.size(); i++){
            productList.add(cartList.get(i).getProduct_cart());
        }
        List<ProductFiles> productFilesList = new ArrayList<>();
        for (int i =0; i< productList.size(); i++){
            long productID = productList.get(i).getId();
            List<ProductFiles> productFileslist1 = productFileService.getProductFilebyProductID(productID);
            productFilesList.add(productFileslist1.get(0));
        }
        return productFilesList;
    }

    @GetMapping("/{id}")
    public String DeleteProduct(@PathVariable("id") long id){
        cartService.deleteProductCart(id);
        return "redirect:/cart";
    }


    @GetMapping
    public String Cart(){
        return "content/cart";
    }

    @PostMapping("/update")
    public String UpdateCart(@ModelAttribute("listCart") ListCartVO listCartVO, Authentication authentication, Model model, RedirectAttributes redirectAttributes){
        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        user.setListCart(listCartVO.getlCarts());
        userService.saveProfile(user);
        redirectAttributes.addFlashAttribute("updateCart","Cart is updated");
        return "redirect:/cart";

    }

    @PostMapping
    public String AddToCart(@ModelAttribute("cart") Cart cart, Authentication authentication, RedirectAttributes redirectAttributes){

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
        redirectAttributes.addFlashAttribute("success","Added to card successfully");
        return "redirect:/product/"+cart.getProduct_cart().getId();
    }
    @ModelAttribute("order")
    public Orders showCart(Authentication authentication){

        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);

        int total = 0;
        Orders orders = new Orders();
        List<Cart> cartList = user.getListCart();

        for(int i = 0; i < cartList.size(); i++){
            total = total + cartList.get(i).getQuantity() * cartList.get(i).getProduct_cart().getPrice();
        }
        orders.setUser(user);
        orders.setSubTotal(total);
        orders.setTotal(total);
        return orders;
    }

}
