package com.coba.spring_restapi_1.controllers;

import com.coba.spring_restapi_1.dto.ResponseData;
import com.coba.spring_restapi_1.models.entities.Product;
import com.coba.spring_restapi_1.models.entities.Supplier;
import com.coba.spring_restapi_1.models.repos.ProductRepo;
import com.coba.spring_restapi_1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepo productRepo;

    /**
     * List of products
     * */
    @GetMapping
    Iterable<Product> findAll(){
        return productService.findAll();
    }

    /**
     * Create
     * Use @Valid for valid first before input, and use Errors for throw errors if there's error
     *
     * */
    @PostMapping
    ResponseEntity<ResponseData<Product>> create(@Valid @RequestBody Product product, Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);

    }

    /**
     * show product
     * */
    @GetMapping("/view/{id}")
    public Product findOne( @PathVariable("id") Long id){
        Optional<Product> product = productRepo.findById(id);
        if (!product.isPresent()){
            return null;
        }
        return product.get();
    }

    /**
     * Update product
     *
     * ResponseEntity<ResponseData<Product>> : berarti adalah respon entity yang mengenkapsulasi response data
     * */
    @PutMapping("/update")
    ResponseEntity<ResponseData<Product>> update(@Valid @RequestBody Product product, Errors errors){

        ResponseData<Product> responseData = new ResponseData<>();

        if (errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));
        return ResponseEntity.ok(responseData);

    }

    /**
     * Delete product
     * */
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        productService.removeOne(id);
        return "Deleted...";
    }

    @PostMapping("/{id}")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){
        productService.addSupplier(supplier, productId);
    }

}
