package com.example.t3mb_decor.controller.admin;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.t3mb_decor.VO.UserToAdminVO;
import com.example.t3mb_decor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;
    @ModelAttribute("first")
    public String getActive1(){
        return ".mysale_click";
    }
    @ModelAttribute("second")
    public String getActive2(){
        return ".customer_click";
    }

    @ModelAttribute("user")
    public UserToAdminVO showUser(){return new UserToAdminVO();}

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
            model.addAttribute("emptyError",true);
            return "/content/admin/users";
        }
        userService.saveUserToAdmin(user);
        return "redirect:/user?success";
    }

}
