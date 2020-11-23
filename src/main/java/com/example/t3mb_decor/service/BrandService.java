package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrand();
    void saveBrand(Brand brand);
    Brand getBrand(long id);
    void deleteBrand(long id);
    void updateBrand(Brand brand);
    List<Brand> getAllBrandSort();
}
