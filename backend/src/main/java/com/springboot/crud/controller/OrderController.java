package com.springboot.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.crud.model.Order;
import com.springboot.crud.service.OrderService;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:3001")
public class OrderController {
	@Autowired
 private OrderService orderService;
 
	   @GetMapping
	    public List<Order> getAllOrders() {
	        return orderService.getAllOrders();
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
	        try {
	            Order order = orderService.getOrderById(id);
	            return ResponseEntity.ok(order);
	        } catch (RuntimeException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	    @PostMapping
	    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
	        try {
	            Order createdOrder = orderService.createOrder(order);
	            return ResponseEntity.ok(createdOrder);
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().build();
	        }
	    }
	}

