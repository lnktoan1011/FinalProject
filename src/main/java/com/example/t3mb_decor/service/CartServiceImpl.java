package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Brand;
import com.example.t3mb_decor.model.Cart;
import com.example.t3mb_decor.model.Product;
import com.example.t3mb_decor.repository.BrandRepository;
import com.example.t3mb_decor.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements  CartService{

    @Autowired
    private CartRepository cartRepository;
    @Override
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public void saveCart(Cart cart) {
        this.cartRepository.save(cart);
    }

    @Override
    public Cart getCart(long id) {
        return this.cartRepository.findById(id).get();
    }

    @Override
    public void deleteCart(long id) {
        cartRepository.delete(this.getCart(id));
    }

    @Override
    public void updateCart(Cart cart) {
        long id = cart.getId();
        Cart cartUpdate = this.getCart(id);
        Date createDate = cartUpdate.getCreatedAt();

        cartUpdate.setCreatedAt(createDate);
        cartUpdate.setQuantity(cart.getQuantity());
        this.cartRepository.save(cartUpdate);
    }

    @Override
    public Cart getExistCart(long userId, long productId) {
        return this.cartRepository.getExistCart(userId, productId);
    }

    @Override
    public void deleteProductCart(long id) {
        cartRepository.deleteByProduct_cart(id);
    }

    @Override
    public void deleteExistCart(long userID) {
        cartRepository.deleteExistCart(userID);
    }

}
