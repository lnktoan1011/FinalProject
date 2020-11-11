package com.example.t3mb_decor.controller;

import com.example.t3mb_decor.VO.UserVO;
import com.example.t3mb_decor.model.Cart;
import com.example.t3mb_decor.model.User;

import com.example.t3mb_decor.repository.UserRepository;
import com.example.t3mb_decor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/profile")
public class InfoController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

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

    @ModelAttribute("InfoUser")
    @ResponseBody
    public User showUserInfo(Authentication authentication){
        User user = userService.getUserFindByEmail(authentication.getName());
        return user;
    }
    @GetMapping
    public String showProfile(){return "/content/profile";}

    @PostMapping("update")
    public String updateProfile(@ModelAttribute("InfoUser") @Valid User user, BindingResult bindingResult){

//        String userDAta=userService.getUser(user.getId()).getEmail();
//        String userData=userRepository.findById(user.getId()).get().getEmail();
//        String userPage=( user.getEmail());

        if (userService.checkEmail(user.getEmail())){
            bindingResult.addError(new FieldError("user","email",
                    "Email address already in use"));
        }
        if(bindingResult.hasErrors()){
            return "/content/profile";
        }


        userService.saveProfile(user);
        return "redirect:/profile?successUpdate";
    }
}
