package com.ytecomm.jwt.service;

import com.ytecomm.jwt.entity.Product;
import com.ytecomm.jwt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addNewProduct(Product product){
//        Product p = productRepository.save(product);
//        return p;
        return productRepository.save(product);

    }
}
