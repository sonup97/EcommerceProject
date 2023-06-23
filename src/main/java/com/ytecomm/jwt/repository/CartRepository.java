package com.ytecomm.jwt.repository;

import com.ytecomm.jwt.entity.Cart;
import com.ytecomm.jwt.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer> {

    public List<Cart> findByUser(User user);
}
