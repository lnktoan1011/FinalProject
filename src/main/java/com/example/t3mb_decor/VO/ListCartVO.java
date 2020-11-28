package com.example.t3mb_decor.VO;

import com.example.t3mb_decor.model.Cart;

import java.util.ArrayList;
import java.util.List;

public class ListCartVO {
    private List<Cart> lCarts;

    public ListCartVO(List<Cart> lCarts) {
        this.lCarts = lCarts;
    }

    public void addCart(Cart cart) {
        this.lCarts.add(cart);
    }

    public List<Cart> getlCarts() {
        return lCarts;
    }

    public void setlCarts(List<Cart> lCarts) {
        this.lCarts = lCarts;
    }
}
