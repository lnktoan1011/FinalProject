package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Brand;
import com.example.t3mb_decor.model.Orders;

import java.util.List;

public interface OrderService {
    List<Orders> getAllOrder();
    void saveOrder(Orders order);
    Orders getOrder(long id);
    void deleteOrder(long id);
    void updateOrder(Orders order);
    List<Orders> getHistory(long userId);
    void confirmOrder(long id);
    List<Orders> getAllOrderSoft();
    List<Orders> getOrderSearch(String id, String name, String address, String total);
    long countOrder();
    long countNewOrder();

}
