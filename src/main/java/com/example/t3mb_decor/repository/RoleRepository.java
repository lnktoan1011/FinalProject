package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
//    @Query(value = "insert into roles (name) VALUES (:name)", nativeQuery = true)
//    @Transactional
//    void insertRole(@Param("name") String name);
}
