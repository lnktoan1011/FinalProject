package com.example.t3mb_decor.controller;

import com.example.t3mb_decor.model.*;
import com.example.t3mb_decor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    BrandService brandService;
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
    public String search(@Param("productSearch") String productSearch,
                         @Param("priceSearch") String priceSearch,
                         @Param("brandSearch") String brandSearch, Authentication authentication,Model model){
        int price_low = 0;
        int price_high = 0;
        long brand = 0;
        String[] split;
        split = priceSearch.split("-");
        String name = productSearch;

        if (!priceSearch.equals("All"))
        {
            price_low = Integer.parseInt(split[0]);
            price_high = Integer.parseInt(split[1]);
        }
        if (!brandSearch.equals("All")){
            brand = Integer.parseInt(brandSearch);
        }

        List<Product> productList = productService.getProductSearchMain(name,price_low, price_high, brand);


        List<ProductFiles> productFilesList = new ArrayList<>();
        for (int i =0; i< productList.size(); i++) {
            long productID = productList.get(i).getId();
            List<ProductFiles> productFileslist1 = productFileService.getProductFilebyProductID(productID);
            productFilesList.add(productFileslist1.get(0));
        }

        model.addAttribute("productList", productList);
        model.addAttribute("listImg", productFilesList);
        model.addAttribute("listBrand", brandService.getAllBrand());
        model.addAttribute("productSearch", productSearch);
        model.addAttribute("priceSearch", priceSearch);
        model.addAttribute("brandSearch", brandSearch);
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
