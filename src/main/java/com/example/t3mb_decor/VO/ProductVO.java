package com.example.t3mb_decor.VO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class ProductVO {

    @NotBlank(message = "Enter your name")
    private String name;
    @NotBlank(message = "Enter price")
    private int price;
    @NotBlank(message = "Enter description")
    private String description;
    @NotBlank(message = "Enter quantity")
    private int quantity;
    @NotBlank(message = "Enter material")
    private String material;
    @NotBlank(message = "Enter height")
    private int height;
    @NotBlank(message = "Enter length")
    private int length;
    @NotBlank(message = "Enter width")
    private int width;

    public ProductVO() {
    }

    public ProductVO(String name, int price, String description, int quantity, String material, int height, int length, int width) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.quantity = quantity;
        this.material = material;
        this.height = height;
        this.length = length;
        this.width = width;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
