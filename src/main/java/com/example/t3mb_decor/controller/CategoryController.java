package com.example.t3mb_decor.controller;


import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.Customer;
import com.example.t3mb_decor.model.SubCategory;
import com.example.t3mb_decor.repository.CategoryRepository;
import com.example.t3mb_decor.repository.SubCategoryRepository;
import com.example.t3mb_decor.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RequestMapping("/category")
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String viewAllCategory(Model model){
        List<Category> listCate =  categoryService.getAllCategories();
        model.addAttribute( "listCategories",listCate);
        model.addAttribute( "flag","1");
        return "index";
    }
    @GetMapping("delete/{id}")
     public String deleteCategory(@PathVariable("id") long id){
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
    @GetMapping("add")
    public String viewNewCategory(Model model){
        List<Category> listCate =  categoryService.getAllCategories();
        model.addAttribute( "listCategories",listCate);
        model.addAttribute("cate", new Category());
        return "index";
    }
    @PostMapping("add")
    public String saveCategory(@ModelAttribute("cate") Category category){
        String id;
//        category = cate.findById((long) 1).get();
//        List<SubCategory> subCategory;
//        subCategory = category.getSub();
//        for (SubCategory value : subCategory) {
//            if (value.getName().equals("Short-Table")) {
//                value.setName("Short Table");
//            }
//            if (value.getName().equals("Long-Table")) {
//                value.setName("Long Table");
//            }
//            if (value.getName().equals("Medium-Table")) {
//                value.setName("Medium Table");
//            }
//        }
        //category.setSub(subCategory);
        //cate.save(category);

        //Save cate create date.Because when update the cate create date not save.
        if(category.getId() != 0) {
            Date createDate = categoryService.getCategory(category.getId()).getCreatedAt();
            List<SubCategory> subCategoryList = categoryService.getCategory(category.getId()).getSub();
            category.setCreatedAt(createDate);
            category.setSub(subCategoryList);
            categoryService.saveCategory(category);
            return "redirect:/category/update/" + category.getId() ;
        }
        categoryService.saveCategory(category);
        return "redirect:" ;
    }
    @GetMapping("update/{id}")
    public String viewUpdateCategory(@PathVariable("id") long id, Model model){
        List<Category> listCate =  categoryService.getAllCategories();
        model.addAttribute( "listCategories",listCate);
        Category categoryUpdate = categoryService.getCategory(id);
        model.addAttribute("cate", categoryUpdate);
        return "index";
    }

    @GetMapping("add-sub/{id}")
    public String viewNewSubCategory(@PathVariable("id") long id, Model model){
        List<Category> listCate =  categoryService.getAllCategories();
        model.addAttribute( "listCategories",listCate);
        model.addAttribute("subCate", new SubCategory());
        model.addAttribute("cate_id", id);
        return "index";
    }
    @PostMapping("add-sub/{id}")
    public String addSubCategory(@PathVariable("id") long id,@ModelAttribute("subCate") SubCategory sub){
        Category category = categoryService.getCategory(id) ;
        category.getSub().add(sub);
        categoryService.saveCategory(category);
        return "redirect:/category";
    }


}
