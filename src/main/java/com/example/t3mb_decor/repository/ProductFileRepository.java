package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.ProductFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductFileRepository extends JpaRepository<ProductFiles, Long> {

    @Query(value = "SELECT * from product_files where product_id = :id", nativeQuery = true)
    List<ProductFiles> getProductFilebyProductID(@Param("id") long id);

    @Modifying
    @Query(value = "DELETE FROM product_files WHERE product_id = :id and modified_file_name in :removeImage", nativeQuery = true)
    @Transactional
    void deleteImageByProduct(@Param("id") long id,@Param("removeImage") List<String> removeImage);

}
