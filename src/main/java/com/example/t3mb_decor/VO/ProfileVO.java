package com.example.t3mb_decor.VO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ProfileVO {

    @NotBlank(message = "Enter your name")
    private String name;
    @NotBlank(message = "Enter your email")
    @Email(message = "Enter a valid email address")
    private String email;
    @NotBlank(message = "Enter your phone")
    private String phone;
    @NotBlank(message = "Enter your address")
    private String address;

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
