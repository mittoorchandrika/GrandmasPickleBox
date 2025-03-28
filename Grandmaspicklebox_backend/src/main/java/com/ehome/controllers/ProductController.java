package com.ehome.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ehome.entities.Product;
import com.ehome.services.ProductService;

@RestController
@RequestMapping(value = "/api/auth/products")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to connect
public class ProductController {

    @Autowired
    private ProductService service;

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        List<Product> products = service.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product product = service.findById(id);
        return ResponseEntity.ok().body(product);
    }

    // ✅ New API: Add product (POST request)
    @PostMapping("/admin")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = service.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
}