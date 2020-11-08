package com.example.t3mb_decor.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",nullable = false)
    @NotBlank(message = "Enter your name")
    private String name;

    @Column(name = "email",nullable = false)
    @NotBlank(message = "Enter your email")
    @Email(message = "Enter a valid email address")
    private String email;

    @Column(name = "password",nullable = false)
    @NotNull
    private String password;

    @Column(name = "address")
    @NotBlank(message = "Enter your address")
    private String address;

    @Column(name = "phone",nullable = false)
    @NotBlank(message = "Enter your phone")
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id")})
    //,referencedColumnName = "id"
    private List<Role> roles;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "wishlists",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> product_wishlist = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
//    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private List<Orders> order_customer = new ArrayList<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;


    public User() {
    }

    public User(String name, String email, String password, String address, String phone, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public List<Product> getProduct_wishlist() {
        return product_wishlist;
    }

    public void setProduct_wishlist(List<Product> product_wishlist) {
        this.product_wishlist = product_wishlist;
    }

    public List<Orders> getOrder_customer() {
        return order_customer;
    }

    public void setOrder_customer(List<Orders> order_customer) {
        this.order_customer = order_customer;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
