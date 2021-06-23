package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();
    Product getProduct(long id);
    List<Product> searchProduct(String name);
    List<Product> getProductSearch(String name,String price,String quantity,String brand,String color,String category);
    List<Product> getProductSearchMain(String name,int price_low,int price_high ,long brand);
    Product save(Product product);
    Product update(Product product);
    void delete(Long id);
    List<Product> getAllProductSort();
    List<Product> getProductBySubId(long id);
    List<Product> getNewProduct();
    List<Product> getValidProduct();
}
