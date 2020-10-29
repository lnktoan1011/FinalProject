package com.example.t3mb_decor.controller;

import com.example.t3mb_decor.model.*;
import com.example.t3mb_decor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    //      List of products
    @ModelAttribute("productList")
    public List<Product> productList(){
        return productService.getAllProduct();
    }


    //      List of images
    @ModelAttribute("listImg")
    public List<ProductFiles> productFiles(){
        List<Product> productList = productService.getAllProduct();
        List<ProductFiles> productFilesList = new ArrayList<>();

        for (int i =0; i< productList.size(); i++){
            long productID = productList.get(i).getId();
            List<ProductFiles> productFileslist1 = productFileService.getProductFilebyProductID(productID);
            productFilesList.add(productFileslist1.get(0));
        }
        return productFilesList;
    }


    @GetMapping
    public String viewProduct(Model model){

        return "content/list_product";
    }


}
