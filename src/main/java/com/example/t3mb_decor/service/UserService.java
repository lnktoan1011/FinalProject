package com.example.t3mb_decor.service;

import com.example.t3mb_decor.VO.UserToAdminVO;
import com.example.t3mb_decor.VO.UserVO;
import com.example.t3mb_decor.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


public interface UserService extends UserDetailsService {
    User saveUserToAdmin(UserToAdminVO user);
    User saveUser(UserVO registration);
    Boolean checkEmail(String email);
}
