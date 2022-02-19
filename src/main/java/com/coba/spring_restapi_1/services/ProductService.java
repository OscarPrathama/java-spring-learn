package com.coba.spring_restapi_1.services;

import com.coba.spring_restapi_1.models.entities.Product;
import com.coba.spring_restapi_1.models.entities.Supplier;
import com.coba.spring_restapi_1.models.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    /**
     * Create or Update
     * */
    public Product save(Product product){
        return productRepo.save(product);
    }

    public Product findOne(Long id){
        return productRepo.findById(id).get();
    }

    public Iterable<Product> findAll(){
        return productRepo.findAll();
    }

    public void removeOne(Long id){
        productRepo.deleteById(id);
    }

    public List<Product> findByName(String name){
        return productRepo.findByNameContains(name);
    }

    public void addSupplier(Supplier supplier, Long productId){
        Product product = findOne(productId);

        if(product == null){
            throw new RuntimeException("Product with ID : "+productId+" not found");
        }
        product.getSuppliers().add(supplier);
        save(product);
    }

}
