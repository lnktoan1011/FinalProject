package com.example.t3mb_decor.controller.admin;

import com.example.t3mb_decor.VO.UserToAdminVO;
import com.example.t3mb_decor.model.Role;
import com.example.t3mb_decor.model.User;
import com.example.t3mb_decor.service.RoleService;
import com.example.t3mb_decor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("admins/user")
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @ModelAttribute("first")
    public String getActive1(){
        return ".mysale_click";
    }
    @ModelAttribute("second")
    public String getActive2(){
        return ".customer_click";
    }

    @ModelAttribute("listUsers")
    @ResponseBody
    public List<User> getList(Authentication authentication){

        List<User> list = userService.getAllUser(authentication.getName());
        return list;
    }

    @ModelAttribute("listRole")
    public List<Role> getListRole(){
        return roleService.getAllRole();
    }

    @ModelAttribute("user")
    public UserToAdminVO showUser(){return new UserToAdminVO();}

    @ModelAttribute("getTable")
    public boolean getTable(){
        return false;}
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

    @GetMapping
    public String viewtest(){
        return "/content/admin/users";
    }

    @PostMapping("add")
    public String addUser(@ModelAttribute("user") @Valid UserToAdminVO user, BindingResult bindingResult, Model model){
        if (userService.checkEmail(user.getEmail())){
            bindingResult.addError(new FieldError("user","email",
                    "Email address already in use"));
        }
        if(user.getPassword() != null && user.getCpassword() !=null){
            if (!user.getPassword().equals(user.getCpassword())){
                bindingResult.addError(new FieldError("user", "cpassword"
                        ,"Password must match"));
            }
        }
        if (bindingResult.hasErrors()){
            model.addAttribute("getTable",true);
            model.addAttribute("emptyError",true);
            return "/content/admin/users";
        }
        userService.saveUserToAdmin(user);
        return "redirect:/admins/user?success";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admins/user";
    }
    @GetMapping("/update/{id}")
    public String showUpdate(@PathVariable("id") long id,Model model){
        User user = userService.getUser(id);
        model.addAttribute("getTable",true);
        model.addAttribute("userUpdate",user);
        return "/content/admin/users";
    }
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("userUpdate") User user, BindingResult bindingResult,Model model){
        if (!user.getEmail().equals(userService.getUser(user.getId()).getEmail())) {
            if (userService.checkEmail(user.getEmail())) {
                bindingResult.addError(new FieldError("user", "email",
                        "Email address already in use"));
            }
            if (bindingResult.hasErrors()){
                model.addAttribute("getTable",true);
                return "/content/admin/users";
            }
        }
        userService.saveUserUpdate(user);
        return "redirect:/admins/user?successUpdate";
    }
}
