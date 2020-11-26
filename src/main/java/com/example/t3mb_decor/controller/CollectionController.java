package com.example.t3mb_decor.controller;

import com.example.t3mb_decor.model.*;
import com.example.t3mb_decor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/collections")
public class CollectionController {
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

    //      List of products
    @ModelAttribute("productList")
    public List<Product> productList(){
        return productService.getAllProductSort();
    }


    @GetMapping
    public String viewCollection(Model model, Authentication authentication){
        model.addAttribute("productSearch", new Product());
        List<Product> productList = productService.getAllProductSort();
        List<ProductFiles> productFilesList = new ArrayList<>();
        for (int i =0; i< productList.size(); i++) {
            long productID = productList.get(i).getId();
            List<ProductFiles> productFileslist1 = productFileService.getProductFilebyProductID(productID);
            productFilesList.add(productFileslist1.get(0));
        }
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

    @GetMapping("/product")
    public String viewProduct(Model model){

        return "content/product";
    }



}
