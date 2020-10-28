package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Product;
import com.example.t3mb_decor.model.ProductFiles;

import java.util.List;

public interface ProductFileService {
    List<ProductFiles> getAllProductFiles();
    List<ProductFiles> getProductFilebyProductID(long id);
}
