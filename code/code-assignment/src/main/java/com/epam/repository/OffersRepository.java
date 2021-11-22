package com.epam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.model.Offers;

public interface OffersRepository extends JpaRepository<Offers, Long> {
}
