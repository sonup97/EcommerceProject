package com.ytecomm.jwt.controller;

import com.ytecomm.jwt.entity.OrderInput;
import com.ytecomm.jwt.service.OrderDetailService;
import com.ytecomm.jwt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('User')")
    @PostMapping({"/placeOrder/isCartCheckout"})
    public void placeOrder(@PathVariable(name = "isCartCheckout") boolean isCartCheckout,
            @RequestBody OrderInput orderInput){
        orderDetailService.placeOrder(orderInput,isCartCheckout);
    }
}
