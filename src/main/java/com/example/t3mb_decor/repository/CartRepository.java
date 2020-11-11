package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.Cart;
import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.ProductFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {


    @Query(value = "SELECT * from carts where user_id = :userId and product_id = :productId", nativeQuery = true)
    Cart getExistCart(@Param("userId") long userId, @Param("productId") long productId ) ;

    @Modifying
    @Query(value = "DELETE from carts where user_id = :userId", nativeQuery = true)
    @Transactional
    void deleteExistCart(@Param("userId") long userId) ;


}
