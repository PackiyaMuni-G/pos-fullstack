package com.springboot.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.crud.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
