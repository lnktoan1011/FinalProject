package com.example.t3mb_decor.controller.admin;

import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.Orders;
import com.example.t3mb_decor.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    //      List of categories
    @ModelAttribute("listOrder")
    public List<Orders> getList(){
        List<Orders> listOrders =  orderService.getAllOrder();
        return listOrders;
    }

    @GetMapping
    public String viewOrder(){
        return "content/admin/order";
    }
    @GetMapping("/{id}")
    public String viewOrderDetail(@PathVariable("id") long id, Model model){
        Orders orderDetail = orderService.getOrder(id);
        model.addAttribute("orderDetail", orderDetail);
        return "content/admin/order";
    }

    @GetMapping("/confirm/{id}")
    public String confirmOrderDetail(@PathVariable("id") long id){
       orderService.confirmOrder(id);
        return "redirect:/admins/order/" + id ;
    }

}
