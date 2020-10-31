package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Role;
import com.example.t3mb_decor.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoleServiceImp implements RoleService{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

}
