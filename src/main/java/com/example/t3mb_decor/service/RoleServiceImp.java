package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Role;
import com.example.t3mb_decor.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }
//    @Override
//    public void insert() {
//        if(roleRepository.findAll() == null){
//            this.roleRepository.save(new Role("ROLE_ADMIN"));
//            this.roleRepository.save(new Role("ROLE_SALER"));
//            this.roleRepository.save(new Role("ROLE_CUSTOMER"));
//
//
////            this.roleRepository.insertRole("ROLE_SALER",new Date(), new Date());
////            this.roleRepository.insertRole("ROLE_CUSTOMER",new Date(), new Date());
//        }
////        this.roleRepository.insertRole(new Role("ROLE_ADMIN"));
//    }
}
