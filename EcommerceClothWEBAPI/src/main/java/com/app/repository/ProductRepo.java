package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
