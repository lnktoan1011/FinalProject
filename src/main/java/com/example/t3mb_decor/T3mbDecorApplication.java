package com.example.t3mb_decor;

import com.example.t3mb_decor.model.Role;
import com.example.t3mb_decor.repository.RoleRepository;
import com.example.t3mb_decor.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.persistence.Access;
import javax.swing.*;

@SpringBootApplication
public class T3mbDecorApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(T3mbDecorApplication.class);
    }
    public static void main(String[] args) { SpringApplication.run(T3mbDecorApplication.class, args); }


}
