package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Product;
import com.example.t3mb_decor.model.ProductFiles;
import com.example.t3mb_decor.repository.ProductFileRepository;
import com.example.t3mb_decor.repository.ProductRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UploadPathService uploadPathService;
    @Autowired
    private ProductFileRepository productFileRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return this.productRepository.findById(id).get();
    }

    @Override
    public List<Product> searchProduct(String name) {
        return productRepository.searchProduct(name);
    }

    @Override
    public Product save(Product product) {

        Product productSave = productRepository.save(product);
        if(productSave != null && product.getFiles() != null && product.getFiles().size() > 0){
            for(MultipartFile file: product.getFiles()){
                if(!file.getOriginalFilename().isEmpty())
                {
                    String filename = file.getOriginalFilename();
                    String modifiedName = FilenameUtils.getBaseName(filename) + "_" + System.currentTimeMillis() + "." + FilenameUtils.getExtension(filename);
                    File storeFile = uploadPathService.getFilePath(modifiedName, "images");
                    if(storeFile != null){
                        try {
                            FileUtils.writeByteArrayToFile(storeFile, file.getBytes());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    ProductFiles files = new ProductFiles();
                    files.setFileExtention(FilenameUtils.getExtension(filename));
                    files.setFilename(filename);
                    files.setModifiedFileName(modifiedName);
                    files.setProduct(productSave);
                    productFileRepository.save(files);
                }
            }
            return productSave;
        }
        return null;
    }

    @Override
    public List<Product> getAllProductSort() {
        List<Product> list = this.productRepository.findByOrderByIdDesc();
        return list;
    }
}
