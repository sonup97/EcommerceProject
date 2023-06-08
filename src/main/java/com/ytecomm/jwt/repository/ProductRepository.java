package com.ytecomm.jwt.repository;

import com.ytecomm.jwt.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product,Integer> {


    public List<Product> findAll(Pageable pageable);
}
