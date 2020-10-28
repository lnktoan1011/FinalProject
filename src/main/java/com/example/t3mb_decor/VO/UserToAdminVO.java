package com.example.t3mb_decor.VO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserToAdminVO {

    @NotBlank(message = "Enter your name")
    private String name;
    @NotBlank(message = "Enter your email")
    @Email(message = "Enter a valid email address")
    private String email;
    @NotBlank(message = "Enter your password")
    private String password;
    @NotBlank(message = "Enter your password")
    private String cpassword;
    @NotBlank(message = "Enter your address")
    private String address;
    @NotBlank(message = "Enter your phone")
    private String phone;
    @NotBlank(message = "Enter your role")
    private String role;

    public UserToAdminVO() {
    }

    public UserToAdminVO(String name, String email, String password, String cpassword, String address, String phone, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.cpassword = cpassword;
        this.address = address;
        this.phone = phone;
        this.role = role;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpassword() {
        return cpassword;
    }

    public void setCpassword(String cpassword) {
        this.cpassword = cpassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
