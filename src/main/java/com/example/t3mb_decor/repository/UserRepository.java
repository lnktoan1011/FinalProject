package com.example.t3mb_decor.repository;

import com.example.t3mb_decor.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    @Query(value = "SELECT * FROM users WHERE email != :email", nativeQuery = true)
    List<User> getList(@Param("email") String email);


    @Modifying
    @Query(value = "DELETE FROM user_roles WHERE user_id = :id", nativeQuery = true)
    @Transactional
    void deleteRoles(@Param("id") long id);


}
