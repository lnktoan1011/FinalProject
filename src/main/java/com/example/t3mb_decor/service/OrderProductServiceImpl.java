package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Brand;
import com.example.t3mb_decor.model.OrderProduct;
import com.example.t3mb_decor.model.Orders;
import com.example.t3mb_decor.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements  OrderProductService{
    @Autowired
    OrderProductRepository orderProductRepository;

    @Override
    public List<OrderProduct> getAllOrderProduct() {
        return orderProductRepository.findAll();
    }

    @Override
    public void saveOrderProduct(OrderProduct orderProduct) {
        this.orderProductRepository.save(orderProduct);
    }


    @Override
    public OrderProduct getOrderProduct(long id) {
        return orderProductRepository.findById(id).get();
    }

    @Override
    public void deleteOrderProduct(long id) {

    }

    @Override
    public void updateOrderProduct(OrderProduct orderProduct) {

    }
}
