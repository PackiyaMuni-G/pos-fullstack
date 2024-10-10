package com.springboot.crud.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.crud.model.Order;
import com.springboot.crud.model.OrderItem;
import com.springboot.crud.model.Product;
import com.springboot.crud.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductService productService;
    
    public Order createOrder(Order order) {
        // Validate and process order items
        for (OrderItem item : order.getOrderItems()) {
            Product product = productService.getProductById(item.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
                
            // Update stock
            if (!productService.updateProductStock(product.getId(), item.getQuantity())) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }
            
            // Set the current price
            item.setPrice(product.getPrice());
        }
        
        // Calculate total amount
        double totalAmount = order.getOrderItems().stream()
            .mapToDouble(item -> item.getPrice() * item.getQuantity())
            .sum();
            
        order.setTotalAmount(totalAmount);
        order.setOrderDate(LocalDateTime.now());
        
        return orderRepository.save(order);
    }
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }
}
