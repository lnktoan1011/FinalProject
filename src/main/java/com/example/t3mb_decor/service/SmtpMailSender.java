package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.OrderProduct;
import com.example.t3mb_decor.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Component
public class SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;
    public void send(String to,
                     Orders orders) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        helper = new MimeMessageHelper(message, true);

        String subject = "You have new order - ID:" + orders.getId() ;
        StringBuilder body = new StringBuilder("Order ID:" + orders.getId() + "." + "\n");
        body.append("Products:" + "\n");
        for(int i =0; i < orders.getOrder_product().size(); i++){
            body.append(orders.getOrder_product().get(i).getProduct_orders().getName() + ": ");
            body.append(orders.getOrder_product().get(i).getProduct_orders().getPrice() + " * ");
            body.append(orders.getOrder_product().get(i).getQuantity() + "\n");
        }
        body.append("Subtotal: " + orders.getSubTotal() +"\n");
        if(orders.getDiscount() != null){
            body.append("Discount: " + orders.getDiscount().getPercent() + "\n");

        }
        else{
            body.append("Discount: 0" + "\n");
        }
        body.append("Total: " + orders.getTotal() + "\n");

        System.out.println(subject);
        System.out.println(body.toString());

        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(body.toString(),true);

        javaMailSender.send(message);
    }
}
