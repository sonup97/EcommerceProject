package com.ytecomm.jwt.service;

import com.ytecomm.jwt.configuration.JwtRequestFilter;
import com.ytecomm.jwt.entity.*;
import com.ytecomm.jwt.repository.OrderDetailRepository;
import com.ytecomm.jwt.repository.ProductRepository;
import com.ytecomm.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    private static final String ORDER_PLACED = "Placed";

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    public void placeOrder(OrderInput orderInput){
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for(OrderProductQuantity o : productQuantityList){
            Product product = productRepository.findById(o.getProductId()).get();
            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userRepository.findById(currentUser).get();


            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getProductDiscountedPrice() * o.getQuantity(),
                    product,
                    user
            );
            orderDetailRepository.save(orderDetail);
        }

    }
}
