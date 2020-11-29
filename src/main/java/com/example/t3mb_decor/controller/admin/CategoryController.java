package com.example.t3mb_decor.controller.admin;


import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.SubCategory;
import com.example.t3mb_decor.service.CategoryService;
import com.example.t3mb_decor.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Date;
import java.util.List;
@RequestMapping("admins/category")
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;

    @ModelAttribute("first")
    public String getActive1(){
        return ".catalog_click";
    }
    @ModelAttribute("second")
    public String getActive2(){
        return ".category_click";
    }

    @ModelAttribute("listCategories")
    public List<Category> getList(){
        List<Category> listCate =  categoryService.getAllCategories();
        return listCate;
    }

    @GetMapping
    public String viewAllCategory(Model model){
        model.addAttribute( "flag", 1);
        model.addAttribute("cate", new Category());
        return "content/admin/categories";
    }
    @GetMapping("delete/{id}")
     public String deleteCategory(@PathVariable("id") long id){
        categoryService.deleteCategory(id);
        return "redirect:/admins/category";
    }
    @GetMapping("delete")
    public String deleteCategory(){
        categoryService.deleteAllOfCategory();
        return "redirect:/admins/category";
    }
    @GetMapping("/deleteSub/{id}")
    public String deleteSub(@PathVariable("id") long id){
        long cateID = subCategoryService.getSubCategory(id).getCategory().getId();
        subCategoryService.deleteSubCategory(id);
        return "redirect:/admins/category/update/" + cateID ;
    }
    @GetMapping("add")
    public String viewNewCategory(Model model){
        return "redirect:/admins/category";
    }
    @PostMapping("add")
    public String saveCategory(@ModelAttribute("cate") Category category, RedirectAttributes redirectAttributes){
        String id;

        //Save cate create date.Because when update the cate create date not save.
        if(category.getId() != 0) {
            categoryService.updateCategory(category);
            redirectAttributes.addFlashAttribute("success","Category is updated successfully");
            return "redirect:/admins/category/update/" + category.getId() ;
        }
        categoryService.saveCategory(category);
        redirectAttributes.addFlashAttribute("success","Category is saved successfully");
        return "redirect:/admins/category" ;
    }
    @GetMapping("update/{id}")
    public String viewUpdateCategory(@PathVariable("id") long id, Model model){
        Category categoryUpdate = categoryService.getCategory(id);
        model.addAttribute("cate", categoryUpdate);
        model.addAttribute("showCate", categoryUpdate);
        return "content/admin/categories";
    }

    @GetMapping("add-sub/{id}")
    public String viewNewSubCategory(@PathVariable("id") long id, Model model){
        model.addAttribute("subCate", new SubCategory());
        model.addAttribute("cate_id", id);
        model.addAttribute( "flag", 1);
        Category categoryUpdate = categoryService.getCategory(id);
        model.addAttribute("showCate", categoryUpdate);
        return "content/admin/categories";
    }
    @PostMapping("add-sub/{id}")
    public String addSubCategory(@PathVariable("id") long id,@ModelAttribute("subCate") SubCategory sub, RedirectAttributes redirectAttributes){
        Category category = categoryService.getCategory(id) ;
        sub.setCategory(category);
        subCategoryService.saveSubCategory(sub);
        redirectAttributes.addFlashAttribute("success","Sub-Category is saved successfully");
        return "redirect:/admins/category/add-sub/" + id ;
    }

    @GetMapping("update-sub/{id}")
    public String viewUpdateSubCategory(@PathVariable("id") long id, Model model){

        SubCategory subCategoryUpdate = subCategoryService.getSubCategory(id);
        Category categoryUpdate = subCategoryUpdate.getCategory();
        model.addAttribute("showCate", categoryUpdate);
        model.addAttribute("subCateUpdate", subCategoryUpdate);
        return "content/admin/categories";
    }
    @PostMapping("update-sub/{id}")
    public String UpdateSubCategory(@PathVariable("id") long id, @ModelAttribute("subCateUpdate") SubCategory sub, RedirectAttributes redirectAttributes){
        subCategoryService.updateSubCategory(sub);
        redirectAttributes.addFlashAttribute("success","Sub-Category is updated successfully");
        return "redirect:/admins/category/update-sub/" + id ;
    }
}
