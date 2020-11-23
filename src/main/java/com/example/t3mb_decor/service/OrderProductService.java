package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Brand;
import com.example.t3mb_decor.model.OrderProduct;

import java.util.List;

public interface OrderProductService {
    List<OrderProduct> getAllOrderProduct();
    void saveOrderProduct(OrderProduct orderProduct);
    OrderProduct getOrderProduct(long id);
    void deleteOrderProduct(long id);
    void updateOrderProduct(OrderProduct orderProduct);
}
