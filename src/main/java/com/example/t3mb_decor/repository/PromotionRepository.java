package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Discount,Long> {
    Discount findByName(String name);

    List<Discount> findByOrderByIdDesc();
}
