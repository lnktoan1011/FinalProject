package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color,Long> {
}
