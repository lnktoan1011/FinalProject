package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryReponsitory extends JpaRepository<Category,Long> {

}
