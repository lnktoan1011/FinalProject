package com.example.t3mb_decor.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/403")
public class Page403Controller {

    @GetMapping
    public String getPage(){ return "content/login/403"; }

}
