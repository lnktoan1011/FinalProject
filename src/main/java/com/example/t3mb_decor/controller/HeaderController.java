package com.example.t3mb_decor.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeaderController {
    @GetMapping("/contact")
    public String showContact(){ return "/content/contact";}

    @GetMapping("/about")
    public String showAbout(){return "/content/about";}

    @GetMapping("/cart")
    public String showCart(){return "/content/cart";}

//    @GetMapping("/profile")
//    public String showProfile(){return "/content/profile";}
    @GetMapping("/history")
    public String showHistory(){return "/content/pus-history";}
}
