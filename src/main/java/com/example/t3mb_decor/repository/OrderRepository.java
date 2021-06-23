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
    @Query(value = "SELECT * from orders where user_id = :userId ORDER BY id DESC", nativeQuery = true)
    List<Orders> getHistory(@Param("userId") long userId) ;

    List<Orders> findByOrderByIdDesc();

    @Query(value = "SELECT count(id) FROM orders where status=0", nativeQuery = true)
    long countNewOrder();

    @Query(value =  "SELECT os.* from orders as os " +
                    "INNER JOIN users as us" +
                    " on os.user_id = us.id" +
                    " WHERE ( os.id = CASE WHEN :id = 0 THEN os.id ELSE :id END)" +
                    " AND   ( UPPER(us.name) LIKE CASE WHEN :name = '' THEN UPPER(us.name) ELSE UPPER('%'||:name||'%') END)" +
                    " AND   ( UPPER(us.address) LIKE CASE WHEN :address = '' THEN UPPER(us.address) ELSE UPPER('%'||:address||'%') END)" +
                    " AND   ( os.total = CASE WHEN :total = 0 THEN os.total ELSE :total END)" +
                    "ORDER BY os.id DESC"
                    ,nativeQuery = true)
    List<Orders> getOrderSearch(@Param("id") long id,
                                @Param("name") String name,
                                @Param("address") String address,
                                @Param("total") long total);
}
