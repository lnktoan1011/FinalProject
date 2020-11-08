package com.example.t3mb_decor.VO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserVO {

    @NotBlank(message = "Enter your name")
    private String name;
    @NotBlank(message = "Enter your email")
    @Email(message = "Enter a valid email address")
    private String email;
    @NotBlank(message = "Enter your password")
    @Length(min = 6,message = "Passwords must be at least 6 characters")
    private String password;

    @NotBlank(message = "Enter your password")
    private String rpassword;
    @NotBlank(message = "Enter your phone")
    private String phone;
    @NotBlank(message = "Enter your address")
    private String address;

    public UserVO() {
    }

    public UserVO(String name, String email, String password, String phone, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public UserVO(@NotBlank(message = "Enter your name") String name, @NotBlank(message = "Enter your email") @Email(message = "Enter a valid email address") String email, @NotBlank(message = "Enter your phone") String phone, @NotBlank(message = "Enter your address") String address) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
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

    public String getRpassword() {
        return rpassword;
    }

    public void setRpassword(String rpassword) {
        this.rpassword = rpassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
