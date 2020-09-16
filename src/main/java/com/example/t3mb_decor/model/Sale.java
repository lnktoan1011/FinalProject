package com.example.t3mb_decor.model;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "percent",nullable = false)
    private int percent;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id",referencedColumnName = "id",columnDefinition = "bigint default 0")
    private List<Product> product_sale = new ArrayList<>() ;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Date updatedAt;

    public Sale() {
    }

    public Sale(long id, int percent, List<Product> product_sale, Date createdAt, Date updatedAt) {
        this.id = id;
        this.percent = percent;
        this.product_sale = product_sale;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public List<Product> getProduct_sale() {
        return product_sale;
    }

    public void setProduct_sale(List<Product> product_sale) {
        this.product_sale = product_sale;
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
