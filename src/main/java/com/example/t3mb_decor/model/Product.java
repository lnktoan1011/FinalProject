package com.example.t3mb_decor.model;

import com.sun.istack.NotNull;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Text;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "price",nullable = false)
    private int price;
    @Column(name = "description",columnDefinition = "TEXT",nullable = false)
    private String description;
    @Column(name = "quantity",nullable = false)
    private int quantity;
    @Column(name = "material",nullable = false)
    private String material;
    @Column(name = "image",columnDefinition = "TEXT",nullable = false)
    private String image;
    @Column(name = "height",nullable = false)
    private int height;
    @Column(name = "lenght",nullable = false)
    private int lenght;
    @Column(name = "width",nullable = false)
    private int width;
    @Column(name = "sale_status")
    @Value("0")
    private int sale_status;
    @Column(name = "status")
    @Value("0")
    private int status;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public Product() {
    }

    public Product(long id, String name, int price, String description, int quantity, String material, String image, int height, int lenght, int width, int sale_status, int status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.material = material;
        this.image = image;
        this.height = height;
        this.lenght = lenght;
        this.width = width;
        this.sale_status = sale_status;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }


    public int getSale_status() {
        return sale_status;
    }

    public void setSale_status(int sale_status) {
        this.sale_status = sale_status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
}
