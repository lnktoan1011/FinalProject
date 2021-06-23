package com.example.t3mb_decor.service;

import com.example.t3mb_decor.model.Orders;
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

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
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
    @Autowired
    private ServletContext context;

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
    public Product update(Product product) {
        Product productSave = productRepository.save(product);
        if(product!= null && product.getRemoveImage() != null && product.getRemoveImage().size() > 0){
            productFileRepository.deleteImageByProduct(product.getId(), product.getRemoveImage());
            for(String img:product.getRemoveImage()){
                File dbFile = new File(context.getRealPath("/images/"+File.separator+img));
                if(dbFile.exists()){
                    dbFile.delete();
                }
            }
        }
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
    public void delete(Long id) {
        Product product = this.getProduct(id);
        product.setStatus(1);
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProductSort() {
        List<Product> list = this.productRepository.getValidProduct();
        return list;
    }

    @Override
    public List<Product> getProductBySubId(long id) {
        return this.productRepository.findBySubId(id);
    }

    @Override
    public List<Product> getNewProduct() {
        return productRepository.findByIdSortNew();
    }

    @Override
    public List<Product> getValidProduct() {
        return productRepository.getValidProduct();
    }

    @Override
    public List<Product> getProductSearchMain(String name,int price_low,int price_high ,long brand){
        System.out.println(name);
        List<Product> productList = this.productRepository.getProductSearchMain(name, price_low, price_high, brand);
        return productList;
    }

    @Override
    public List<Product>  getProductSearch(String name,String price,String quantity){
        int product_price = 0;
        int product_quantity = 0;
        if (!price.equals("")){
            product_price = Integer.parseInt(price);
        }
        if (!quantity.equals("")){
            product_quantity = Integer.parseInt(price);
        }

        List<Product> productList = this.productRepository.getProductSearch(name,product_price,product_quantity);
        return productList;
    }
}
