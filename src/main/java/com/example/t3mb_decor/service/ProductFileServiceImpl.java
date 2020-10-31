package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.ProductFiles;
import com.example.t3mb_decor.repository.ProductFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductFileServiceImpl implements ProductFileService {
    @Autowired
    ProductFileRepository productFileRepository;

    @Override
    public List<ProductFiles> getAllProductFiles() {
        return productFileRepository.findAll();
    }

    @Override
    public List<ProductFiles> getProductFilebyProductID(long id) {
        return productFileRepository.getProductFilebyProductID(id);
    }
}
