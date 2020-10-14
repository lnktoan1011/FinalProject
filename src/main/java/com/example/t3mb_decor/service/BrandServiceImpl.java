package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Brand;
import com.example.t3mb_decor.repository.BrandRepository;
import com.example.t3mb_decor.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandRepository brandRepository;
    @Override
    public List<Brand> getAllBrand() {
        return brandRepository.findAll();
    }

    @Override
    public void saveBrand(Brand brand) {
        this.brandRepository.save(brand);
    }

    @Override
    public Brand getBrand(long id) {
        return this.brandRepository.findById(id).get();
    }

    @Override
    public void deleteBrand(long id) {
        brandRepository.delete(this.getBrand(id));
    }

    @Override
    public void updateBrand(Brand brand) {
        long id = brand.getId();
        Brand brandUpdate = this.getBrand(id);
        Date createDate = brandUpdate.getCreatedAt();

        brandUpdate.setCreatedAt(createDate);
        brandUpdate.setName(brand.getName());
        this.brandRepository.save(brandUpdate);
    }
}
