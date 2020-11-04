package com.example.t3mb_decor.controller;

import com.example.t3mb_decor.model.User;

import com.example.t3mb_decor.repository.UserRepository;
import com.example.t3mb_decor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/profile")
public class InfoController {
    @Autowired
    UserService userService;

    @ModelAttribute("InfoUser")
    @ResponseBody
    public User showInfoUser(Authentication authentication){
        User user = userService.getUserFindByEmail(authentication.getName());
        return user;
    }
    @GetMapping
    public String showProfile(){return "/content/profile";}
}
