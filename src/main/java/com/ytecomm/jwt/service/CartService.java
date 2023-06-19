package com.ytecomm.jwt.service;

import com.ytecomm.jwt.configuration.JwtRequestFilter;
import com.ytecomm.jwt.entity.Cart;
import com.ytecomm.jwt.entity.Product;
import com.ytecomm.jwt.entity.User;
import com.ytecomm.jwt.repository.CartRepository;
import com.ytecomm.jwt.repository.ProductRepository;
import com.ytecomm.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart addToCart(Integer productId){
        Product product = productRepository.findById(productId).get();
        String username = JwtRequestFilter.CURRENT_USER;

        User user = null;
        if(username != null) {
            user = userRepository.findById(username).get();
        }
        if(product != null && user != null){
            Cart cart = new Cart(product,user);
            return cartRepository.save(cart);
        }
        return null;
    }
}
