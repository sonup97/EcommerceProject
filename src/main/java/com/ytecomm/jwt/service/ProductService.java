package com.ytecomm.jwt.service;

import com.ytecomm.jwt.configuration.JwtRequestFilter;
import com.ytecomm.jwt.entity.Cart;
import com.ytecomm.jwt.entity.Product;
import com.ytecomm.jwt.entity.User;
import com.ytecomm.jwt.repository.CartRepository;
import com.ytecomm.jwt.repository.ProductRepository;
import com.ytecomm.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

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
        if(isSingleProductCheckout && productId != 0){
            //We are going to buy single product
            List<Product> list = new ArrayList<>();
            Product product = productRepository.findById(productId).get();
            list.add(product);
            return list;
        }
        else {
            //We are going to checkout entire cart
            String username = JwtRequestFilter.CURRENT_USER;
            User user = userRepository.findById(username).get();
            List<Cart> carts = cartRepository.findByUser(user);

            return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());


        }

//        return new ArrayList<>();
    }

}
