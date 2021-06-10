package com.example.t3mb_decor.controller;

import com.example.t3mb_decor.VO.InfoPwd;
import com.example.t3mb_decor.VO.InfoPwd;
import com.example.t3mb_decor.VO.UserVO;
import com.example.t3mb_decor.model.Cart;
import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.User;

import com.example.t3mb_decor.repository.CategoryRepository;
import com.example.t3mb_decor.repository.UserRepository;
import com.example.t3mb_decor.service.CategoryService;
import com.example.t3mb_decor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class InfoController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryService categoryService;
    @ModelAttribute("listCategories")
    public List<Category> getList(){
        List<Category> listCate =  categoryService.getAllCategories();
        return listCate;
    }

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

//    @ModelAttribute("InfoUser")
//    @ResponseBody
//    public User showUserInfo(Authentication authentication){
//        User user = userService.getUserFindByEmail(authentication.getName());
//        return user;
//    }
//    @GetMapping
//    public String showProfile(){return "/content/profile";}


    @ModelAttribute("InfoPwd")
    public InfoPwd showPwd(){
        InfoPwd infoPwd = new InfoPwd();
        return infoPwd;
    }
    @GetMapping
    public String showUpdate(Authentication authentication, Model model){
        User user = userService.getUserFindByEmail(authentication.getName());
        model.addAttribute("InfoUser",user);
        return "content/profile";
    }
    @PostMapping("update")
    public String updateProfile(@ModelAttribute("InfoUser") @Valid User user, BindingResult bindingResult){


//        String userData=userRepository.findById(user.getId()).get().getEmail();
//        System.out.println("usPage: " +user.getEmail()+"id:"+user.getId());
//        System.out.println("usData: " +userService.getUser(user.getId()).getEmail()+"id:"+userService.getUser(user.getId()).getId());
//        System.out.println("x: "+user.getName());
//        System.out.println("y: " + userService.getUser(user.getId()).getName());

        if (!user.getEmail().equals(userService.getUser(user.getId()).getEmail())) {
            if (userService.checkEmail(user.getEmail()) ){
                bindingResult.addError(new FieldError("user","email",
                        "Email address already in use"));
            }
            if(bindingResult.hasErrors()){
                return "content/profile";
            }
        }

        if(bindingResult.hasErrors()){
            return "content/profile";
        }


        userService.saveProfile(user);
        return "redirect:/profile?successUpdate";
    }

    @PostMapping("updatePwd")
    public String updatePwd(@ModelAttribute("InfoPwd") @Valid InfoPwd infoPwd, BindingResult bindingResult,Model model){
//        boolean check =userService.checkOldPwd(infoPwd);
//        User user = userService.getUserFindByEmail(authentication.getName());
//        infoPwd.setId(user.getId());
        if (!userService.checkOldPwd(infoPwd) && infoPwd.getOldPwd() != "") {
            bindingResult.addError(new FieldError("infoPwd", "oldPwd",
                    "Password does not match"));
        }
        if(infoPwd.getPassword() != null && infoPwd.getRpassword() !=null){
            if (!infoPwd.getPassword().equals(infoPwd.getRpassword())){
                bindingResult.addError(new FieldError("infoPwd", "rpassword"
                        ,"Password must match"));
            }
        }
        if (bindingResult.hasErrors()){
            model.addAttribute("InfoUser",userService.getUser(infoPwd.getId()));
            return "content/profile";
        }

        userService.saveInfoPwd(infoPwd);
        return "redirect:/profile?successUpdatePwd";
    }
}
