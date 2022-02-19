package com.coba.spring_restapi_1.services;

import java.util.Optional;

import javax.transaction.TransactionScoped;

import com.coba.spring_restapi_1.models.entities.Category;
import com.coba.spring_restapi_1.models.repos.CategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@TransactionScoped
public class CategoryService {
    
    @Autowired
    CategoryRepo categoryRepo;

    /**
     * create or update
    */
    public Category save(Category category){
        return categoryRepo.save(category);
    }

    /**
     * get all
    */
    public Iterable<Category> findAll(){
        return categoryRepo.findAll();
    }

    public Category findOne(Long id){
        Optional<Category> category = categoryRepo.findById(id);
        if(!category.isPresent()){
            return null;
        }
        return category.get();
    }

    public void removeOne(Long id){
        categoryRepo.deleteById(id);
    }

}
