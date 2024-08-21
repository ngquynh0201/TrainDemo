package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.models.Product;
import com.example.models.ResponseObject;
import com.example.repositories.ProductRepository;

@RestController
@RequestMapping (path = "/api/v1/Product")
public class ProductController {
    // DI = Dependency Injection 
    @Autowired
    private ProductRepository repository;

    
    @GetMapping("")
    List <Product> getAllProducts(){
        return repository.findAll();//where is data
    }

    @GetMapping("/{id}")
    ResponseEntity <ResponseObject> findById(@PathVariable Long id){
        Optional<Product> foundProduct = repository.findById(id);
        return foundProduct.isPresent() ?
            ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Query product successfully",foundProduct)
            ):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("false","Cannot find product with id = "+id,"")
            );
    }
    @PostMapping("/insert")
        ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct){
            List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
            if(foundProducts.size()>0){
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                new ResponseObject("Flase", "Product name already taken","")
            );
            } 
           return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Product successfully",repository.save(newProduct))
            );
    }
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct,@PathVariable Long id){
        Product updateProduct = repository.findById(id)
                .map(product-> {
                    product.setProductName(newProduct.getProductName());
                    product.setYear(newProduct.getYear());
                    product.setPice(newProduct.getPice());
                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("ok", "update Product successfully", updateProduct)
        ); 
    }
        @DeleteMapping("/{id}")
        ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
            boolean exists = repository.existsById(id);
            if(!exists){
                return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Delete product successfully","")
                );
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("ok","Cannot find product to delete","")
                );
        }              
    }


