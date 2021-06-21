package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Orders;
import com.example.t3mb_decor.repository.CartRepository;
import com.example.t3mb_decor.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
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
        return orderRepository.findById(id).get();
    }

    @Override
    public void confirmOrder(long id) {
        Orders confirmOrder = getOrder(id);
        confirmOrder.setStatus(1);
        orderRepository.save(confirmOrder);
    }

    @Override
    public List<Orders> getAllOrderSoft() {
        return this.orderRepository.findByOrderByIdDesc();
    }

    @Override
    public List<Orders> getOrderSearch(String id, String name, String address,String date ,String total){
        long order_id = 0;
        long order_total = 0;
        String[] dated;
        if (!id.equals("")){
            order_id = Integer.parseInt(id);
            System.out.println("abc");
        }
        if (!total.equals("")){
            order_total = Integer.parseInt(total);
            System.out.println("cde");
        }

        List<Orders> ordersList = this.orderRepository.getOrderSearch(order_id,name,address,order_total);
        List<Orders> ordersList_final = new ArrayList<>();
        System.out.println(ordersList.size());
        //Handle for date
        for (int i =0; i< ordersList.size(); i++){
            dated = ordersList.get(i).getCreatedAt().toString().split("\\s+");
            if ( date.equals(dated[0]) ){
                ordersList_final.add(ordersList.get(i));
            }
        }
        if(ordersList_final.size() == 0 && date.equals("")){
            ordersList_final = ordersList;
        }
        return ordersList_final;
    }
    @Override
    public long countOrder() {
        return orderRepository.count();
    }

    @Override
    public long countNewOrder() {
        return orderRepository.countNewOrder();
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
