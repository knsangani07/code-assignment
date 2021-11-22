package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.OrderDetail;

public interface OrderRepository extends JpaRepository<OrderDetail, Long> {
}
