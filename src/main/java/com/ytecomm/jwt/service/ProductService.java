package com.ytecomm.jwt.service;

import com.ytecomm.jwt.entity.Product;
import com.ytecomm.jwt.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product addNewProduct(Product product){
        return productRepository.save(product);

    }
    public List<Product> getAllProducts(int pageNumber, String searchKey){
        Pageable pageable = PageRequest.of(pageNumber,10);
        if(searchKey.equals("")){
            return (List<Product>)productRepository.findAll(pageable);
        }else {
           return (List<Product>) productRepository.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
                    searchKey,searchKey,pageable
            );
        }

    }

    public void deleteProductDetails(Integer productId){
        productRepository.deleteById(productId);
    }

    public Product getProductDetailsById(Integer productId){
       return productRepository.findById(productId).get();
    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId){
        if(isSingleProductCheckout){
            //We are going to buy single product
            List<Product> list = new ArrayList<>();
            Product product = productRepository.findById(productId).get();
            list.add(product);
            return list;
        }
        else {
            //We are going to checkout entire cart
        }

        return new ArrayList<>();
    }

}
