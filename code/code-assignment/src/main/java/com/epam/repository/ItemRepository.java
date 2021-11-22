package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
