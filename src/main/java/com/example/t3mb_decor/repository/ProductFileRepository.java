package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.ProductFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFileRepository extends JpaRepository<ProductFiles, Long> {

    @Query(value = "SELECT * from product_files where product_id = :id", nativeQuery = true)
    List<ProductFiles> getProductFilebyProductID(@Param("id") long id);


}
