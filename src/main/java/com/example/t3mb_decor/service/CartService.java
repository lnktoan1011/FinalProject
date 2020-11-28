package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Brand;
import com.example.t3mb_decor.model.Cart;
import com.example.t3mb_decor.model.Product;

import java.util.List;

public interface CartService {
    List<Cart> getAllCart();
    void saveCart(Cart cart);
    Cart getCart(long id);
    void deleteCart(long id);
    void updateCart(Cart cart);
    Cart getExistCart(long userId, long productId);
    void deleteProductCart(long id);
    void deleteExistCart(long userID);
}
