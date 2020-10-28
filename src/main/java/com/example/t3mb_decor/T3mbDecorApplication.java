package com.example.t3mb_decor;

import com.example.t3mb_decor.model.Role;
import com.example.t3mb_decor.repository.RoleRepository;
import com.example.t3mb_decor.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.Access;

@SpringBootApplication
public class T3mbDecorApplication {
    public static void main(String[] args) { SpringApplication.run(T3mbDecorApplication.class, args); }


}
