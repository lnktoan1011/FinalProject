package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product getProduct(long id);
    Product save(Product product);
    List<Product> getAllProductSort();
}
