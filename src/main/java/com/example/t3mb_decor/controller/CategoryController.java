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
        return "index";
    }
    @GetMapping("add")
    public String viewCategory(Model model){
        model.addAttribute("cate", new Category());
        return "bin";
    }
    @PostMapping("add")
    public String saveCategory(@ModelAttribute("cate") Category category){
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
        categoryService.saveCategory(category);
        return "redirect:";
    }

//    @PostMapping(path = "{id}")
//    public Category editCategory(@PathVariable("id") long id, @RequestBody Category categoryUpdate){
//        categoryService.updateCategory(id, categoryUpdate);
//        return categoryUpdate;
//    }
}
