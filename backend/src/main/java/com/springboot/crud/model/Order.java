package com.springboot.crud.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sales_order")
public class Order {
    public Long getId() {
		return id;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;
    private Double totalAmount;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;
}
