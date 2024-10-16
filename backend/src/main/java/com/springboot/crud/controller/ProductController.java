package com.springboot.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.model.Product;
import com.springboot.crud.service.ProductService;

@RequestMapping("/products")
@RestController
@CrossOrigin(origins = "http://localhost:3001")
public class ProductController {
	@Autowired
 private ProductService productService;
 @GetMapping
 public List<Product> getAllProducts() {
     return productService.getAllProducts();
 }
 
 @GetMapping("/{id}")
 public ResponseEntity<Product> getProductById(@PathVariable Long id) {
     return productService.getProductById(id)
         .map(ResponseEntity::ok)
         .orElse(ResponseEntity.notFound().build());
 }
 
 @PostMapping
 public Product createProduct(@RequestBody Product product) {
     return productService.createProduct(product);
 }
 
 @PutMapping("/{id}")
 public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
     try {
         Product updatedProduct = productService.updateProduct(id, product);
         return ResponseEntity.ok(updatedProduct);
     } catch (RuntimeException e) {
         return ResponseEntity.notFound().build();
     }
 }
 
 @DeleteMapping("/{id}")
 public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
     try {
         productService.deleteProduct(id);
         return ResponseEntity.ok().build();
     } catch (RuntimeException e) {
         return ResponseEntity.notFound().build();
     }
 }
}

