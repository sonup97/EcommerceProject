package com.ytecomm.jwt.controller;

import com.ytecomm.jwt.entity.OrderDetail;
import com.ytecomm.jwt.entity.OrderInput;
import com.ytecomm.jwt.entity.TransactionDetails;
import com.ytecomm.jwt.service.EmailService;
import com.ytecomm.jwt.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderDetailController {

    private final EmailService emailService;

    @Autowired
    public OrderDetailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @Autowired
    private OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('User')")
    @PostMapping({"/placeOrder/{isSingleProductCheckout}"})
    public void placeOrder(@PathVariable(name = "isSingleProductCheckout") boolean isSingleProductCheckout,
            @RequestBody OrderInput orderInput){
        orderDetailService.placeOrder(orderInput,isSingleProductCheckout);
    }
    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getOrderDetails"})
    public List<OrderDetail> getOrderDetails(){
       return orderDetailService.getOrderDetails();
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/getAllOrderDetails/{status}"})
    public List<OrderDetail> getAllOrderDetails(@PathVariable(name = "status") String status){
        return orderDetailService.getAllOrderDetails(status);
    }
    @PreAuthorize("hasRole('Admin')")
    @GetMapping({"/markOrderAsDelivered/{orderId}"})
    public void markOrderAsDelivered(@PathVariable (name = "orderId") Integer orderId){
            orderDetailService.markOrderAsDelivered(orderId);
    }
    @PreAuthorize("hasRole('User')")
    @GetMapping({"/createTransaction/{amount}"})
    public TransactionDetails createTransaction(@PathVariable(name = "amount") Double amount){
          return  orderDetailService.createTransaction(amount);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/send-notification")
    public String sendNotification() {
        String to = "sachinppandit97@gmail.com"; // Replace with the recipient's email
        String subject = "Notification from Spring Boot";
        String text = "Hello, this is a notification email from Spring Boot.";

        emailService.sendNotificationEmail(to, subject, text);
        return "Notification email sent!";
    }
}
