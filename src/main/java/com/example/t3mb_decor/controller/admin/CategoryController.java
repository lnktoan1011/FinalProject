package com.example.t3mb_decor.controller.admin;


import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.SubCategory;
import com.example.t3mb_decor.service.CategoryService;
import com.example.t3mb_decor.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;
@RequestMapping("/category")
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
//        List<Category> listCate =  categoryService.getAllCategories();
//        model.addAttribute( "listCategories",listCate);
        model.addAttribute( "flag",1);
        return "content/admin/categories";
    }
    @GetMapping("delete/{id}")
     public String deleteCategory(@PathVariable("id") long id){
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }
    @GetMapping("delete")
    public String deleteCategory(){
        categoryService.deleteAllOfCategory();
        return "redirect:/category";
    }
    @GetMapping("/deleteSub/{id}")
    public String deleteSub(@PathVariable("id") long id){
        subCategoryService.deleteSubCategory(id);
        return "redirect:/category";
    }
    @GetMapping("add")
    public String viewNewCategory(Model model){
//        List<Category> listCate =  categoryService.getAllCategories();
//        model.addAttribute( "listCategories",listCate);
        model.addAttribute("cate", new Category());
        return "content/admin/categories";
    }
    @PostMapping("add")
    public String saveCategory(@ModelAttribute("cate") Category category){
        String id;

        //Save cate create date.Because when update the cate create date not save.
        if(category.getId() != 0) {
            categoryService.updateCategory(category.getId(),category);
            return "redirect:/category/update/" + category.getId() ;
        }
        categoryService.saveCategory(category);
        return "redirect:" ;
    }
    @GetMapping("update/{id}")
    public String viewUpdateCategory(@PathVariable("id") long id, Model model){
//        List<Category> listCate =  categoryService.getAllCategories();
//        model.addAttribute( "listCategories",listCate);
        Category categoryUpdate = categoryService.getCategory(id);
        model.addAttribute("cate", categoryUpdate);
        model.addAttribute("addsub_cate_id", id);
        return "content/admin/categories";
    }

    @GetMapping("add-sub/{id}")
    public String viewNewSubCategory(@PathVariable("id") long id, Model model){
//        List<Category> listCate =  categoryService.getAllCategories();
//        model.addAttribute( "listCategories",listCate);
        model.addAttribute("subCate", new SubCategory());
        model.addAttribute("cate_id", id);
        return "content/admin/categories";
    }
    @PostMapping("add-sub/{id}")
    public String addSubCategory(@PathVariable("id") long id,@ModelAttribute("subCate") SubCategory sub){
        Category category = categoryService.getCategory(id) ;
        category.getSub().add(sub);

        categoryService.saveCategory(category);
        return "redirect:/category";
    }

    @GetMapping("update-sub/{id}")
    public String viewUpdateSubCategory(@PathVariable("id") long id, Model model){
//        List<Category> listCate =  categoryService.getAllCategories();
//        model.addAttribute( "listCategories",listCate);
        SubCategory subCategoryUpdate = subCategoryService.getSubCategory(id);
        model.addAttribute("subCateUpdate", subCategoryUpdate);
        model.addAttribute("subCate_id", id);
        model.addAttribute( "flag", 2);
        return "content/admin/categories";
    }
    @PostMapping("update-sub/{id}")
    public String UpdateSubCategory(@PathVariable("id") long id, @ModelAttribute("subCateUpdate") SubCategory sub){
        subCategoryService.updateSubCategory(id,sub);
        return "redirect:/category/update-sub/" + id ;
    }
}
