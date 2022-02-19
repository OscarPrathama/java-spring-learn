package com.coba.spring_restapi_1.models.repos;

import com.coba.spring_restapi_1.models.entities.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<Category, Long>{
    
}
