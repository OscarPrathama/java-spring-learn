package com.coba.spring_restapi_1.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.coba.spring_restapi_1.models.entities.Supplier;
import com.coba.spring_restapi_1.models.repos.SupplierRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SupplierService {
    
    @Autowired
    SupplierRepo supplierRepo;

    public Supplier save(Supplier supplier){
        return supplierRepo.save(supplier);
    }

    public Iterable<Supplier> findAll(){
        return supplierRepo.findAll();
    }

    public Supplier findOne(Long id){
        Optional<Supplier> supplier = supplierRepo.findById(id);
        if(!supplier.isPresent()){
            return null;
        }
        return supplier.get();
    }

    public void removeOne(Long id){
        supplierRepo.deleteById(id);
    }

}
