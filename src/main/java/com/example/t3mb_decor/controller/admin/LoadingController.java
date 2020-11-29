package com.example.t3mb_decor.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/loading")
public class LoadingController {

    @GetMapping
    public String getPage(){ return "content/admin/loading"; }

}
