package com.example.t3mb_decor.controller.admin;

import com.example.t3mb_decor.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admins/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ModelAttribute("first")
    public String getActive1(){
        return ".mysale_click";
    }
    @ModelAttribute("second")
    public String getActive2(){
        return ".order_click";
    }

    @GetMapping
    public String viewOrder(Model model){
        model.addAttribute("listOrder", orderService.getAllOrder());
        return "content/admin/order";
    }

}
