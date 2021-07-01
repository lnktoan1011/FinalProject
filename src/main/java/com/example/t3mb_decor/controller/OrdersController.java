package com.example.t3mb_decor.controller;

import com.example.t3mb_decor.VO.ListCartVO;
import com.example.t3mb_decor.model.*;
import com.example.t3mb_decor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("order")
@Controller
public class OrdersController {
    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    PromotionService promotionService;
    @Autowired
    OrderProductService orderProductService;
    @Autowired
    ProductFileService productFileService;
    @Autowired
    SmtpMailSender smtpMailSender;

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

    //      List of categories
    @ModelAttribute("listCategories")
    public List<Category> getList(){
        List<Category> listCate =  categoryService.getAllCategories();
        return listCate;
    }
    @ModelAttribute("listCart")
    public ListCartVO getListCart(Authentication authentication){
        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        List<Cart> listCart = user.getListCart();
        return new ListCartVO(listCart);
    }
    @ModelAttribute("listImg")
    public List<ProductFiles> productFiles(Authentication authentication){

        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        List<Product> productList = new ArrayList<>();
        List<Cart> cartList = user.getListCart();
        for (int i =0; i< cartList.size(); i++){
            productList.add(cartList.get(i).getProduct_cart());
        }
        List<ProductFiles> productFilesList = new ArrayList<>();
        for (int i =0; i< productList.size(); i++){
            long productID = productList.get(i).getId();
            List<ProductFiles> productFileslist1 = productFileService.getProductFilebyProductID(productID);
            productFilesList.add(productFileslist1.get(0));
        }
        return productFilesList;
    }
    @GetMapping
    public String reDirectToCart(){
        return "redirect:/cart";
    }

    @PostMapping
    public String AddToOrder(@ModelAttribute("order") Orders order, Authentication authentication, Model model) throws MessagingException {
        String emailName = authentication.getName();
        User user = userService.getUserFindByEmail(emailName);
        String valueDiscount = order.getDiscount().getName();
        if(order.getSubTotal() == 0){
            model.addAttribute("errorProduct", "Please add product to Cart");
            model.addAttribute("order", order);
            return "content/cart";
        }
        if (!valueDiscount.isEmpty() && order.getDiscount().getId() == 0){
            Discount discount = promotionService.getProbyName(valueDiscount);
            if(discount != null){
                if (discount.getStatus() == 1){
                    order.setDiscount(discount);
                    model.addAttribute("diss", discount);
                    int subTotal = order.getSubTotal();
                    float percent = (float)discount.getPercent() / 100;
                    order.setTotal((int)(subTotal - subTotal * percent));
                }
                else{
                    model.addAttribute("error", "Promotion is used");
                }
            }
            else{
                model.addAttribute("error", "Invalid Promotion");
            }
            model.addAttribute("order", order);
            return "content/cart";
        }
        if (!order.getDiscount().getName().isEmpty()){
            Discount discount = promotionService.getPro(order.getDiscount().getId());
            discount.setStatus(0);
            promotionService.savePro(discount);
            order.setDiscount(discount);
        }
        else{
            order.setDiscount(null);
        }
        List<Cart> cartList = user.getListCart();
        for(int i = 0; i < cartList.size(); i++){
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct_orders(cartList.get(i).getProduct_cart());
            orderProduct.setQuantity(cartList.get(i).getQuantity());
            orderProduct.setOrders(order);
//            orderProductService.saveOrderProduct(orderProduct);
            order.getOrder_product().add(orderProduct);
        }
        orderService.saveOrder(order);
        cartService.deleteExistCart(user.getId());
        smtpMailSender.send(emailName,order);
        return "redirect:/history";
    }


}
