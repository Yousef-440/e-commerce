package com.ProjectApp.Ecom.service;

import com.ProjectApp.Ecom.model.Product;
import com.ProjectApp.Ecom.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(int id){
        return productRepo.findById(id).orElse(null);
    }

    public Product addProductOrUpdate(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());//تعيين الصورة
        product.setImageType(image.getContentType());//الحصول على نوع المحتوى
        product.setImageData(image.getBytes());//لحفظ الصورة
        return productRepo.save(product);
    }

    public void deleteProductById(int id) {
        productRepo.deleteById(id);
    }

    public List<Product> searchProducts(String keyword) {
        return productRepo.searchProducts(keyword);
    }
}
