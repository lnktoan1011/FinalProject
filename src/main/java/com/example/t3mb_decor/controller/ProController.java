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
import org.springframework.web.bind.annotation.*;

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

        List<Product> proNew = productService.getNewProduct();
        List<ProductFiles> productFilesListImg = new ArrayList<>();
        for (int i =0; i< proNew.size(); i++) {
            long productID = proNew.get(i).getId();
            List<ProductFiles> productFileslist1 = productFileService.getProductFilebyProductID(productID);
            productFilesListImg.add(productFileslist1.get(0));
        }
        model.addAttribute("listNewPro",proNew);
        model.addAttribute("listImg",productFilesListImg);


        Cart cart = new Cart();
        cart.setProduct_cart(product);
        cart.setQuantity(1);
        model.addAttribute("cart", cart);



        return "content/product";
    }
    @GetMapping("/search")
        public String search(){
            return "redirect:/collections";
        }
    @PostMapping("/search")
    public String search(@ModelAttribute("productSearch") Product productSearch, Authentication authentication,Model model){


       if (productSearch.getName().isEmpty()){
           return "redirect:/collections";

       }
        List<Product> productListbefore = productService.getAllProductSort();
        List<Product> productList = new ArrayList<>();
        for (int i =0; i< productListbefore.size(); i++) {
            if(productListbefore.get(i).getName().contains(productSearch.getName())){
                productList.add(productListbefore.get(i));
            }
        }

        List<ProductFiles> productFilesList = new ArrayList<>();
        for (int i =0; i< productList.size(); i++) {
            long productID = productList.get(i).getId();
            List<ProductFiles> productFileslist1 = productFileService.getProductFilebyProductID(productID);
            productFilesList.add(productFileslist1.get(0));
        }

        model.addAttribute("productSearch", productSearch);
        model.addAttribute("productList", productList);
        model.addAttribute("listImg", productFilesList);

        if (authentication != null){
            String emailName = authentication.getName();
            User user = userService.getUserFindByEmail(emailName);
            List<String> listWishList = new ArrayList<>();
            List<Product> listWishListProduct = user.getProduct_wishlist();
            for (int i =0; i< productList.size(); i++){
                if (listWishListProduct.contains(productList.get(i))){
                    listWishList.add("1");
                }
                else{
                    listWishList.add("0");
                }
            }
            model.addAttribute("user", user);
            model.addAttribute("listWishList", listWishList);
            return "content/list_product";
        }
        return "content/list_product";

    }
}
