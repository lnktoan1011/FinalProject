package com.example.t3mb_decor.model;


import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @Column(name = "name",nullable = false)
        private String name;

        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_at")
        private Date createdAt;

        @UpdateTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "updated_at")
        private Date updatedAt;

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
//        @JoinColumn(name = "category_id")
        private List<SubCategory> sub = new ArrayList<>() ;

        public Category(String name) {
            this.name = name;
        }

        public Category() {
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

        public List<SubCategory> getSub() {
                return sub;
            }

        public void setSub(List<SubCategory> sub) {
            this.sub = sub;
        }
}
