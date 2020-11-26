package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

    @Query(value = "SELECT id FROM sub_categories WHERE cate_id= :cate_ids",nativeQuery = true)
    List<Long> findByIdFromCateId(@Param("cate_ids") long id);
}