package com.example.t3mb_decor.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @GetMapping
    public String viewtest(){
        return "content/admin/customers";
    }
}
