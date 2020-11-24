package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.Cart;
import com.example.t3mb_decor.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
    @Query(value = "SELECT * from orders where user_id = :userId", nativeQuery = true)
    List<Orders> getHistory(@Param("userId") long userId) ;

    List<Orders> findByOrderByIdDesc();

    @Query(value = "SELECT count(id) FROM orders where status=0", nativeQuery = true)
    long countNewOrder();

}
