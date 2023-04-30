package com.ytecomm.jwt.repository;

import com.ytecomm.jwt.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Integer> {


}
