package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.Brand;
import com.example.t3mb_decor.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
}
