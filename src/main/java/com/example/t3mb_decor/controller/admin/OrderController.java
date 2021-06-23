package com.example.t3mb_decor.controller.admin;

import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.Orders;
import com.example.t3mb_decor.model.Product;
import com.example.t3mb_decor.model.ProductFiles;
import com.example.t3mb_decor.service.OrderService;
import com.example.t3mb_decor.service.ProductFileService;
import com.example.t3mb_decor.service.UserService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("admins/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductFileService productFileService;
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
        List<Orders> listOrders =  orderService.getAllOrderSoft();
        return listOrders;
    }
    @ModelAttribute("newOrder")
    public long getNewOrder(){
        return orderService.countNewOrder();
    }
    @ModelAttribute("TotalOrder")
    public long getTotalOrder(){
        return orderService.countOrder();
    }
    @ModelAttribute("TotalCus")
    public long getTotalCus(){
        return userService.countUser();
    }
    @GetMapping
    public String viewOrder(Model model){
        model.addAttribute("status", "All");
        return "content/admin/order";
    }

    @GetMapping("/searchOrder")
    public String search(@Param("id") String id,
                         @Param("name") String name,
                         @Param("address") String address,
                         @Param("date") String date,
                         @Param("total") String total,
                         @Param("status") String status,
                         Model model){

        List<Orders> listOrders = orderService.getOrderSearch(id,name,address,date,total,status);
        model.addAttribute("listOrder",listOrders);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("address", address);
        model.addAttribute("date", date);
        model.addAttribute("total", total);
        model.addAttribute("status", status);
        model.addAttribute("display", "display");
        return "content/admin/order";
    }
    @GetMapping("/{id}")
    public String viewOrderDetail(@PathVariable("id") long id, Model model){
        Orders orderDetail = orderService.getOrder(id);
        List<Product> productList = new ArrayList<>();
        for (int i =0; i< orderDetail.getOrder_product().size(); i++){
            productList.add(orderDetail.getOrder_product().get(i).getProduct_orders());
        }
        List<ProductFiles> productFilesList = new ArrayList<>();
        for (int i =0; i< productList.size(); i++){
            long productID = productList.get(i).getId();
            List<ProductFiles> productFileslist1 = productFileService.getProductFilebyProductID(productID);
            productFilesList.add(productFileslist1.get(0));
        }
        model.addAttribute("listImg",productFilesList );
        model.addAttribute("orderDetail", orderDetail);
        return "content/admin/order_detail";
    }

    @GetMapping("/confirm/{id}")
    public String confirmOrderDetail(@PathVariable("id") long id){
       orderService.confirmOrder(id);
        return "redirect:/admins/order/" + id ;
    }

}
