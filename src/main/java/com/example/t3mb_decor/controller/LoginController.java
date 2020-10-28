package com.example.t3mb_decor.controller;
import com.example.t3mb_decor.VO.UserVO;
import com.example.t3mb_decor.service.UserService;
import groovyjarjarpicocli.CommandLine;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor((true));
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @ModelAttribute("register")
    public UserVO customerRegistration(){
        return new UserVO();
    }
    @GetMapping
    public String showLogin(Model model){
        model.addAttribute("registration",false);
        return "content/login/login"; }
//    @GetMapping("registers")
//    public String showRegistrationForm(Model model){
//        model.addAttribute("registration",true);
//        return "/content/login/login";
//    }
    @PostMapping("registers")
    public String registrationCus(@ModelAttribute("register") @Valid UserVO cus, BindingResult bindingResult,Model model ){
        if (userService.checkEmail(cus.getEmail())){
            bindingResult.addError(new FieldError("cus","email","Email address already in use"));
        }
        if (cus.getPassword() != null && cus.getRpassword() != null){
            if (!cus.getPassword().equals(cus.getRpassword())){
                bindingResult.addError(new FieldError("cus","rpassword","Password must match"));
            }
        }
        if (bindingResult.hasErrors()){
            model.addAttribute("registration",true);
//            model.addAttribute("register",new UserVO());
            return "content/login/login";
        }
        userService.saveUser(cus);
        return "redirect:/login?success";
    }
}
