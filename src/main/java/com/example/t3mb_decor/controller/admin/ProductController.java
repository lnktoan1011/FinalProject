package com.example.t3mb_decor.controller.admin;

import com.example.t3mb_decor.model.*;
import com.example.t3mb_decor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admins/product")
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
        List<Product> list = productService.getAllProductSort();
        return list;
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
        List<Product> productList = productService.getAllProductSort();
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
        model.addAttribute("back",false);
        return "content/admin/product";
    }

    @GetMapping("/searchProduct")
    public String search(@Param("name") String name,
                         @Param("price") String price,
                         @Param("quantity") String quantity,
                         @Param("category") String category,
                         @Param("color") String color,
                         @Param("brand") String brand,
                         Model model){
        List<Product> productList  = productService.getProductSearch(name,price,quantity);
        model.addAttribute("productList",productList);
        model.addAttribute("name", name);
        model.addAttribute("price", price);
        model.addAttribute("quantity", quantity);

//      add new product
        model.addAttribute("product", new Product());
//      List of files
        model.addAttribute("productfile", new ArrayList<ProductFiles>());
//      Check already add or not
        model.addAttribute("isAdd", true);
        model.addAttribute("back",false);
        return "content/admin/product";
    }

    @PostMapping("/save")
    public String addProduct(@ModelAttribute @Valid Product product, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("getTable",true);
            model.addAttribute("isAdd", true);
            return "/content/admin/product";
        }

        Product saveProduct = productService.save(product);
        if (saveProduct != null){
            redirectAttributes.addFlashAttribute("success","Product is saved successfully");
            return "redirect:/loading";
        }

        return "content/admin/product";

    }
    @GetMapping("/{productID}")
    public String editProduct(@PathVariable Long productID, Model model){
        Product product = productService.getProduct(productID);
        List<ProductFiles> productImages = productFileService.getProductFilebyProductID(productID);

        model.addAttribute("product", product);
        model.addAttribute("productImages", productImages);
        model.addAttribute("isAdd", false);
        model.addAttribute("getTable",true);
        model.addAttribute("back",true);
        return "content/admin/product";


    }
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute @Valid Product product, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("getTable",true);
            model.addAttribute("isAdd", true);
            return "/content/admin/product";
        }

        Product saveProduct = productService.update(product);
        if (saveProduct != null){
            redirectAttributes.addFlashAttribute("success","Product is update successfully");
            return "redirect:/admins/product";
        }

        return "content/admin/product";

    }
    @GetMapping("/delete/{productID}")
    public String deleteProduct(@PathVariable Long productID, RedirectAttributes redirectAttributes){
        productService.delete(productID);
        redirectAttributes.addFlashAttribute("success","Product is deleted successfully");
        return "redirect:/admins/product";


    }
}
