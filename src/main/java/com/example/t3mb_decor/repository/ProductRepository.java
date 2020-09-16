package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
