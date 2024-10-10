package com.springboot.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.crud.model.Product;
import com.springboot.crud.repository.ProductRepository;

@Service
@Transactional
public class ProductService {
	  @Autowired
	    private ProductRepository productRepository;
	    
	    public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }
	    
	    public Product createProduct(Product product) {
	        return productRepository.save(product);
	    }
	    
	    public Optional<Product> getProductById(Long id) {
	        return productRepository.findById(id);
	    }
	    
	    public Product updateProduct(Long id, Product product) {
	        return productRepository.findById(id)
	            .map(existingProduct -> {
	                existingProduct.setName(product.getName());
	                existingProduct.setDescription(product.getDescription());
	                existingProduct.setPrice(product.getPrice());
	                existingProduct.setStockQuantity(product.getStockQuantity());
	                // Update other fields as needed
	                return productRepository.save(existingProduct);
	            })
	            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
	    }
	    public void deleteProduct(Long id) {
	        productRepository.deleteById(id);
	    }
	    public boolean updateProductStock(Long id, Integer quantity) {
	        return productRepository.findById(id)
	            .map(product -> {
	                if (product.getStockQuantity() >= quantity) {
	                    product.setStockQuantity(product.getStockQuantity() - quantity);
	                    productRepository.save(product);
	                    return true;
	                }
	                return false;
	            })
	            .orElse(false);
	    }

		
	    
}