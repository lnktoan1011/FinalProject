package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Orders;
import com.example.t3mb_decor.repository.CartRepository;
import com.example.t3mb_decor.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Orders> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public void saveOrder(Orders order) {
        this.orderRepository.save(order);
    }

    @Override
    public Orders getOrder(long id) {
        return null;
    }

    @Override
    public void deleteOrder(long id) {

    }

    @Override
    public void updateOrder(Orders order) {

    }

    @Override
    public List<Orders> getHistory(long userId) {
        return orderRepository.getHistory(userId);
    }
}
