package com.coba.spring_restapi_1.models.repos;

import com.coba.spring_restapi_1.models.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Long> {

    List<Product> findByNameContains(String name);

}
