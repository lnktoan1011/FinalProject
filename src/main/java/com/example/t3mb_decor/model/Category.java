package com.example.t3mb_decor.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @Column(name = "name")
        private String name;

        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "created_at")
        private Date createdAt;

        @CreationTimestamp
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "update_at")
        private Date updateAt;

        @OneToMany(targetEntity = Category.class,cascade = CascadeType.ALL)
        @JoinColumn(name = "categoryId", referencedColumnName = "id")
        private List<SubCategory> sub = new ArrayList<>();

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

        public Date getUpdateAt() {
            return updateAt;
        }

        public void setUpdateAt(Date updateAt) {
            this.updateAt = updateAt;
        }

        public List<SubCategory> getSub() {
            return sub;
        }

        public void setSub(List<SubCategory> sub) {
            this.sub = sub;
        }
}
