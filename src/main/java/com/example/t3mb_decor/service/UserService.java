package com.example.t3mb_decor.service;

import com.example.t3mb_decor.VO.InfoPwd;
import com.example.t3mb_decor.VO.UserToAdminVO;
import com.example.t3mb_decor.VO.UserVO;
import com.example.t3mb_decor.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends UserDetailsService {
    List<User> getAllUser(String email);
    User saveUserToAdmin(UserToAdminVO user);
    User saveUser(UserVO registration);
    Boolean checkEmail(String email);
    void deleteUser(long id);
    User getUser(long id);
    void saveUserUpdate(User user);
    User getUserFindByEmail(String email);
    void saveProfile(User user);
    void saveInfoPwd(InfoPwd infoPwd);
    Boolean checkOldPwd(InfoPwd infoPwd);
    long countUser();
}
