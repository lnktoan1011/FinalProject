package com.example.t3mb_decor.controller.admin;

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
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ColorService colorService;
    @Autowired
    BrandService brandService;
    @Autowired
    SubCategoryService subCategoryService;
    @Autowired
    ProductFileService productFileService;

    @ModelAttribute("first")
    public String getActive1(){
        return ".catalog_click";
    }
    @ModelAttribute("second")
    public String getActive2(){
        return ".product_click";
    }

    //      List of products
    @ModelAttribute("productList")
    public List<Product> productList(){
        return productService.getAllProduct();
    }

    //      List of colors
    @ModelAttribute("listColor")
    public List<Color> colorList(){
        return colorService.getAllColor();
    }

    //      List of brands
    @ModelAttribute("listBrand")
    public List<Brand> brandList(){
        return brandService.getAllBrand();
    }

    //      List of subcate
    @ModelAttribute("listSubcate")
    public List<SubCategory> subCateList(){
        return subCategoryService.getAllSubCategory();
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

//      add new product
        model.addAttribute("product", new Product());
//      List of files
        model.addAttribute("productfile", new ArrayList<ProductFiles>());
//      Check already add or not
        model.addAttribute("isAdd", true);
        return "content/admin/product";
    }

    @PostMapping("/save")
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes, Model model){
        Product saveProduct = productService.save(product);
        if (saveProduct != null){
            redirectAttributes.addFlashAttribute("success","Product is saved successfully");
            return "redirect:/product";
        }
        else{
            model.addAttribute("error", "Product is not save, please try again");
//      List of products
            model.addAttribute("product", product);
            return "content/admin/product";
        }

    }

    @PostMapping("/edit/{productID}")
    public String editUser(@ModelAttribute Product product, RedirectAttributes redirectAttributes, Model model){
        Product saveProduct = productService.save(product);
        if (saveProduct != null){
            redirectAttributes.addFlashAttribute("success","Product is saved successfully");
            return "redirect:/product";
        }
        else{
            model.addAttribute("error", "Product is not save, please try again");
//      List of products
            model.addAttribute("product", product);
            return "content/admin/product";
        }

    }
}
