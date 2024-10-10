package com.springboot.crud.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderItem {
    public Long getId() {
		return id;
	}
	public Product getProduct() {
		return product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private Product product;
    private Integer quantity;
    private Double price;
}
