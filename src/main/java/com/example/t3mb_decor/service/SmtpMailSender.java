package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.OrderProduct;
import com.example.t3mb_decor.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;
    public void send(String to, Orders orders, String status) throws MessagingException {
        String subject;
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        if (status.equals("pending")){
            subject = "You have new order - ID:" + orders.getId();
        }
        else {
            subject = "Your order - ID:" + orders.getId() + " is Approved";
        }

        StringBuilder body = new StringBuilder("<p><b>Order ID: </b>" + orders.getId() + "." + "</p>");
        body.append("<p><b>Products:</b>" + "</p>");
        for(int i =0; i < orders.getOrder_product().size(); i++){
            body.append("<p> - " + orders.getOrder_product().get(i).getProduct_orders().getName() + ": ");
            body.append(orders.getOrder_product().get(i).getProduct_orders().getPrice() + " * ");
            body.append(orders.getOrder_product().get(i).getQuantity() + " = ");
            body.append(orders.getOrder_product().get(i).getQuantity() * orders.getOrder_product().get(i).getProduct_orders().getPrice() + "</p>");
        }
        body.append("<p><b>Subtotal: </b>" + orders.getSubTotal() +"</p>");
        if(orders.getDiscount() != null){
            body.append("<p><b>Discount: </b>" + orders.getDiscount().getPercent() + "</p>");
        }
        else{
            body.append("<p><b>Discount: </b>0" + "</p>");
        }
        body.append("<p><b>Total: </b>" + orders.getTotal() + "</p>");
        if (status.equals("approved")) {
            body.append("<p style = 'color:Tomato'>This order is approved and delivered as soon as possible</p>");
        }
        System.out.println(subject);
        System.out.println(body.toString());

        helper.setFrom("t3mbdecor@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(body.toString(), true);

        javaMailSender.send(message);
    }
}
