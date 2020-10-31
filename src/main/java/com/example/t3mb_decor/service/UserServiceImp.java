package com.example.t3mb_decor.service;

import com.example.t3mb_decor.VO.UserToAdminVO;
import com.example.t3mb_decor.VO.UserVO;
import com.example.t3mb_decor.model.Role;
import com.example.t3mb_decor.model.User;
import com.example.t3mb_decor.repository.RoleRepository;
import com.example.t3mb_decor.repository.UserRepository;
import com.example.t3mb_decor.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User saveUserToAdmin(UserToAdminVO user) {
        long v = Long.parseLong(user.getRole());
        Role role = roleRepository.findById(v).get();
        User us = new User(user.getName(),user.getEmail(),passwordEncoder.encode(user.getPassword())
                ,user.getAddress(),user.getPhone(),Arrays.asList(role));
        return userRepository.save(us);
    }

    @Override
    public User saveUser(UserVO registration) {
        long v = Long.parseLong("3");
        System.out.println(roleRepository.findById(v).get());
        User user = new User(registration.getName(),registration.getEmail(),passwordEncoder.encode(registration.getPassword()),
                registration.getAddress(),registration.getPhone(), Arrays.asList(roleRepository.findById(v).get()));
        return userRepository.save(user);
    }

    @Override
    public Boolean checkEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public void deleteUser(long id) {
        User u = this.getUser(id);
        u.setRoles(null);
        userRepository.delete(u);
    }

    @Override
    public User getUser(long id) {
        Optional<User> op = userRepository.findById(id);
        User us = null;
        if(op.isPresent()){
            us = op.get();
        }else{
            throw  new RuntimeException("User not found for id:: " +id);
        }
        return us;
    }

    @Override
    public void saveUserUpdate(User user) {
        User userUpdate = getUser(user.getId());
        userUpdate.setName(user.getName());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setAddress(user.getAddress());
        userUpdate.setPhone(user.getPhone());
        Date updateDate = user.getCreatedAt();
        userUpdate.setUpdatedAt(updateDate);
        System.out.println(user.getRoles());
        userUpdate.setRoles(user.getRoles());
        this.userRepository.save(userUpdate);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
//        return new MyUserDetails(user);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
