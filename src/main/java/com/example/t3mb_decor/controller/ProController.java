package com.example.t3mb_decor.controller;

import com.example.t3mb_decor.model.Product;
import com.example.t3mb_decor.model.ProductFiles;
import com.example.t3mb_decor.service.ProductFileService;
import com.example.t3mb_decor.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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



    @GetMapping("product/{id}")
    public String viewCollection(@PathVariable("id") long id, Model model){
        Product product = productService.getProduct(id);
        List<ProductFiles> productFilesList = productFileService.getProductFilebyProductID(id);
        model.addAttribute("product", product);
        model.addAttribute("listImage", productFilesList);
        return "content/product";
    }
}
