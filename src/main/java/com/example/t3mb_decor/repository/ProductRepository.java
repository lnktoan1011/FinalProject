package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.Category;
import com.example.t3mb_decor.model.Product;
import com.example.t3mb_decor.model.ProductFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByOrderByIdDesc();

    @Query(value = "SELECT * from products where name = :name  ", nativeQuery = true)
    List<Product> searchProduct(@Param("name") String name);

    @Query(value = "SELECT * FROM products WHERE subcate_id= :id and status = 0 ORDER BY id DESC",nativeQuery = true)
    List<Product> findBySubId(@Param("id") long id);


    @Query(value = "SELECT * FROM products WHERE status = 0 ORDER BY id DESC LIMIT 5",nativeQuery = true)
    List<Product> findByIdSortNew();

    @Query(value = "SELECT * from products where status = 0 ORDER BY id DESC", nativeQuery = true)
    List<Product> getValidProduct();

    @Query(value = "SELECT prd.* from products as prd" +
                   " WHERE ( UPPER(prd.name) like CASE WHEN :name = '' THEN UPPER(prd.name) ELSE UPPER('%'||:name||'%') END)" +
                   " AND ( prd.price >= CASE WHEN :price_low = 0 THEN  prd.price ELSE :price_low END)" +
                   " AND ( prd.price <= CASE WHEN :price_high = 0 THEN  prd.price ELSE :price_high END)" +
                   " AND ( prd.brand_id = CASE WHEN :brand = 0 THEN prd.brand_id ELSE :brand END)" +
                   " AND prd.status = 0 ORDER BY prd.id DESC", nativeQuery = true)
    List<Product> getProductSearchMain(@Param("name") String name,
                                       @Param("price_low") int price_low,
                                       @Param("price_high") int price_high,
                                       @Param("brand") long brand );

    @Query(value =  "SELECT prd.* from products as prd" +
                    " WHERE  ( UPPER(prd.name) LIKE CASE WHEN :name = '' THEN UPPER(prd.name) ELSE UPPER('%'||:name||'%') END)" +
                    " AND   ( prd.price = CASE WHEN :product_price = 0 THEN  prd.price ELSE :product_price END)" +
                    " AND   ( prd.quantity = CASE WHEN :product_quantity = 0 THEN prd.quantity ELSE :product_quantity END)" +
                    "AND   ( prd.brand_id = CASE WHEN :brand = 0 THEN  prd.brand_id ELSE :brand END)" +
                    "AND   ( prd.color_id = CASE WHEN :color = 0 THEN  prd.color_id ELSE :color END)" +
                    "AND   ( prd.subcate_id = CASE WHEN :cate = 0 THEN  prd.subcate_id ELSE :cate END)" +
                    "AND prd.status = 0 ORDER BY prd.id DESC"
                    ,nativeQuery = true)
    List<Product> getProductSearch(@Param("name") String name,
                                   @Param("product_price") int product_price,
                                   @Param("product_quantity") int product_quantity,
                                   @Param("brand") long brand,
                                   @Param("color") long color,
                                   @Param("cate") long cate);

}
