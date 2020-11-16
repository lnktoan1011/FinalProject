package com.example.t3mb_decor.VO;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class InfoPwd {
    private long id;
    @NotBlank(message = "Enter your old password")
    private String oldPwd;
    @NotBlank(message = "Enter your password")
    @Length(min = 6,message = "Password must be at least characters")
    private String password;
    @NotBlank(message = "Enter your password")
    private String rpassword;

    public InfoPwd() {
    }

    public InfoPwd(long id, @NotBlank(message = "Enter your old password") String oldPwd, @NotBlank(message = "Enter your password") @Length(min = 6, message = "Password must be at least characters") String password, @NotBlank(message = "Enter your password") String rpassword) {
        this.id = id;
        this.oldPwd = oldPwd;
        this.password = password;
        this.rpassword = rpassword;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }
}
